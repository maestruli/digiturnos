package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.logging.*;

public class PacientesDaoImplBase extends PostgresqlBase implements PacientesDaoBase {

    private Integer limit;
    private Integer offset;

    protected static final String SQL_SELECT = "SELECT pacientes.idpaciente, pacientes.historiaclinica, pacientes.dni, pacientes.nombre, pacientes.sexo, pacientes.fechanacimiento, pacientes.domicilio, pacientes.telefono, pacientes.celular, pacientes.email, pacientes.observaciones FROM pacientes ";
    protected static final String SQL_MAX_ID = "SELECT MAX(idpaciente) FROM pacientes";
    protected static final String SQL_INSERT = "INSERT INTO pacientes (historiaclinica, dni, nombre, sexo, fechanacimiento, domicilio, telefono, celular, email, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String SQL_INSERT_WITH_ID = "INSERT INTO pacientes (idpaciente, historiaclinica, dni, nombre, sexo, fechanacimiento, domicilio, telefono, celular, email, observaciones) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String SQL_UPDATE = "UPDATE pacientes SET idpaciente = ?, historiaclinica = ?, dni = ?, nombre = ?, sexo = ?, fechanacimiento = ?, domicilio = ?, telefono = ?, celular = ?, email = ?, observaciones = ? WHERE pacientes.idpaciente = ?";
    protected static final String SQL_DELETE = "DELETE FROM pacientes WHERE pacientes.idpaciente = ?";

    protected static final String ORDER_BY = " ORDER BY ";

    protected String[][] orderByColumns = null;

    public void setOrderByColumn(String column) {
        String[][] columns = new String[1][2];
        columns[0][0] = column;
        columns[0][1] = " ASC ";
        setOrderByColumns(columns);
    }

    public void setOrderByColumn(String column, boolean descending) {
        String[][] columns = new String[1][2];
        columns[0][0] = column;
        columns[0][1] = (descending) ? " DESC " : " ASC ";
        setOrderByColumns(columns);
    }
    public void setOrderByColumns(String[][] columns) {
        this.orderByColumns = columns;
    }

    public String[][] getOrderByColumns() {
        return this.orderByColumns;
    }

    public String getOrderByClause() {
        if (orderByColumns == null || orderByColumns.length <= 0)
            return "";
        StringBuffer sb = new StringBuffer(ORDER_BY);
        boolean first = true;
        for (int i=0; i<orderByColumns.length; i++) {
            if (!first) {
                first = false;
                sb.append(", ");
            }
            sb.append(orderByColumns[i][0]);
            sb.append(orderByColumns[i][1]);
        }
        sb.append(" ");
        return sb.toString();
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public  Pacientes[] findAll() throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT;
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += "offset " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public PacientesPK insert(Pacientes dto) throws PacientesDaoException {
        return insert(dto, null);
    }

    public  PacientesPK insert(Pacientes dto, Integer idpaciente) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Pacientes idto = null;
        PacientesPK pk = null;

        try {
            String sql = SQL_INSERT;
            if (!(idpaciente == null ))
                sql = SQL_INSERT_WITH_ID;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            if (!(idpaciente == null ))
                ps.setInt(paramCount++,  dto.getIdpaciente());
            if (dto.getHistoriaclinica() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getHistoriaclinica());
            if (dto.getDni() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getDni());
            ps.setString(paramCount++,  dto.getNombre());
            ps.setString(paramCount++,  dto.getSexo());
            ps.setDate(paramCount++,  dto.getFechanacimiento());
            ps.setString(paramCount++,  dto.getDomicilio());
            ps.setString(paramCount++,  dto.getTelefono());
            ps.setString(paramCount++,  dto.getCelular());
            ps.setString(paramCount++,  dto.getEmail());
            ps.setString(paramCount++,  dto.getObservaciones());
            ps.executeUpdate();
            log.trace("SQL: " + sql);
            if (idpaciente == null ) {
                int cIdpaciente = -1;
                String sqlIdpaciente = "select currval('pacientes_idpaciente_seq')";
                PreparedStatement psIdpaciente = con.prepareStatement(sqlIdpaciente);
                ResultSet rsIdpaciente = psIdpaciente.executeQuery();
                while (rsIdpaciente.next())
                    cIdpaciente = rsIdpaciente.getInt(1);
                dto.setIdpaciente(cIdpaciente);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.rollback();
                    con.setAutoCommit(true);
                    con.close();
                }
            } catch (Exception e) {
            }
                    return dto.createPK();
        }

    }

    public  int update(PacientesPK pk, Pacientes dto) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (dto.getIdpaciente() == null)
                ps.setNull(1, 4);
            else
                ps.setInt(1,  dto.getIdpaciente());
            if (dto.getHistoriaclinica() == null)
                ps.setNull(2, 4);
            else
                ps.setInt(2,  dto.getHistoriaclinica());
            if (dto.getDni() == null)
                ps.setNull(3, 4);
            else
                ps.setInt(3,  dto.getDni());
            if (dto.getNombre() == null)
                ps.setNull(4, 12);
            else
                ps.setString(4,  dto.getNombre());
            if (dto.getSexo() == null)
                ps.setNull(5, 1);
            else
                ps.setString(5,  dto.getSexo());
            if (dto.getFechanacimiento() == null)
                ps.setNull(6, 91);
            else
                ps.setDate(6,  dto.getFechanacimiento());
            if (dto.getDomicilio() == null)
                ps.setNull(7, 12);
            else
                ps.setString(7,  dto.getDomicilio());
            if (dto.getTelefono() == null)
                ps.setNull(8, 1);
            else
                ps.setString(8,  dto.getTelefono());
            if (dto.getCelular() == null)
                ps.setNull(9, 1);
            else
                ps.setString(9,  dto.getCelular());
            if (dto.getEmail() == null)
                ps.setNull(10, 12);
            else
                ps.setString(10,  dto.getEmail());
            if (dto.getObservaciones() == null)
                ps.setNull(11, 12);
            else
                ps.setString(11,  dto.getObservaciones());
            ps.setInt(12,  pk.getIdpaciente());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                    if (ps != null)
                        ps.close();
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
            }
        }
        return numRows;
    }

    public  int delete(PacientesPK pk) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  pk.getIdpaciente());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                    if (ps != null)
                        ps.close();
                    if (con != null) {
                        con.close();
                    }
                } catch (Exception e) {
            }
        }
        return numRows;
    }

    public  Pacientes findWhereOIDEquals(long oid) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE OID = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setLong(1, oid);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes findByPrimaryKey(PacientesPK pk) throws PacientesDaoException {
        return findByPrimaryKey(pk.getIdpaciente());
    }

    public  Pacientes findByPrimaryKey(Integer idpaciente) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.idpaciente = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }


    public  Pacientes[] findWhereIdpacienteEquals(Integer idpaciente) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.idpaciente = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereHistoriaclinicaEquals(Integer historiaclinica) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.historiaclinica = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, historiaclinica);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereDniEquals(Integer dni) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.dni = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereNombreEquals(String nombre) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.nombre = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereSexoEquals(String sexo) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.sexo = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sexo);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.fechanacimiento = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechanacimiento);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereDomicilioEquals(String domicilio) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.domicilio = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, domicilio);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereTelefonoEquals(String telefono) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.telefono = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, telefono);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereCelularEquals(String celular) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.celular = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, celular);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereEmailEquals(String email) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.email = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Pacientes[] findWhereObservacionesEquals(String observaciones) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE pacientes.observaciones = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, observaciones);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public  Object[][] findBySelect(String sql, Object[] sqlParams) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            con = getConnection();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            ps = con.prepareStatement(sql);
            if (sqlParams != null) {
                for (int i=0; i<sqlParams.length; i++) {
                    ps.setObject((i+1), sqlParams[i]);
                }
            }
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            ArrayList rows = new ArrayList();
            ArrayList cols = new ArrayList();
            int colCount = rs.getMetaData().getColumnCount();
            while (rs.next()) {
                for(int i=0; i<colCount; i++)
                    cols.add(rs.getObject(i+1));
                rows.add(cols.toArray(new Object[colCount]));
                cols = new ArrayList();
            }
            Object[][] ra = (Object[][]) rows.toArray(new Object[rows.size()][colCount]);
            return ra;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }


    public Pacientes[] findByWhere(String where, Object[] sqlParams) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE " + where;
                sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (sqlParams != null) {
                for (int i=0; i<sqlParams.length; i++) {
                    ps.setObject((i+1), sqlParams[i]);
                }
            }
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }


    public int countAll() throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countByPrimaryKey(PacientesPK pk) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE idpaciente = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk.getIdpaciente());
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countByPrimaryKey(Integer idpaciente) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE idpaciente = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereIdpacienteEquals(Integer idpaciente) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE idpaciente = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idpaciente);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereHistoriaclinicaEquals(Integer historiaclinica) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE historiaclinica = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, historiaclinica);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereDniEquals(Integer dni) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE dni = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereNombreEquals(String nombre) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE nombre = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereSexoEquals(String sexo) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE sexo = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sexo);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE fechanacimiento = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechanacimiento);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereDomicilioEquals(String domicilio) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE domicilio = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, domicilio);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereTelefonoEquals(String telefono) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE telefono = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, telefono);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereCelularEquals(String celular) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE celular = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, celular);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereEmailEquals(String email) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE email = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countWhereObservacionesEquals(String observaciones) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes WHERE observaciones = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, observaciones);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    public int countByWhere(String where, Object[] sqlParams) throws PacientesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idpaciente) from pacientes ";
            if (where != null)  
               sql += " WHERE " + where;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (sqlParams != null) {
               for (int i=0; i<sqlParams.length; i++) { 
                   ps.setObject((i+1), sqlParams[i]); 
                }
            } 
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new PacientesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new PacientesDaoException("Exception: " + e.getMessage(), e);
        } finally {
            try {
                if (rs != null)
                    rs.close();
                if (ps != null)
                    ps.close();
                if (con != null) {
                    con.close();
                }
            } catch (Exception e) {
            }
        }

    }

    protected  Pacientes[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Pacientes dto = new Pacientes();
            populateDto(dto, rs);
            results.add(dto);
        }
        Pacientes retValue[] = new Pacientes[results.size()];
        results.toArray(retValue);
        return retValue;
    }


    protected  Pacientes fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Pacientes dto = new Pacientes();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    protected static void populateDto(Pacientes dto, ResultSet rs) throws SQLException {
        try {
            dto.setIdpaciente(rs.getInt(COLUMN_POSITION_IDPACIENTE));
            if (rs.wasNull())
                dto.setIdpaciente(null);
        } catch (Exception e) {}
        try {
            dto.setHistoriaclinica(rs.getInt(COLUMN_POSITION_HISTORIACLINICA));
            if (rs.wasNull())
                dto.setHistoriaclinica(null);
        } catch (Exception e) {}
        try {
            dto.setDni(rs.getInt(COLUMN_POSITION_DNI));
            if (rs.wasNull())
                dto.setDni(null);
        } catch (Exception e) {}
        try {
            dto.setNombre(rs.getString(COLUMN_POSITION_NOMBRE));
            if (rs.wasNull())
                dto.setNombre(null);
        } catch (Exception e) {}
        try {
            dto.setSexo(rs.getString(COLUMN_POSITION_SEXO));
            if (rs.wasNull())
                dto.setSexo(null);
        } catch (Exception e) {}
        try {
            dto.setFechanacimiento(rs.getDate(COLUMN_POSITION_FECHANACIMIENTO));
            if (rs.wasNull())
                dto.setFechanacimiento(null);
        } catch (Exception e) {}
        try {
            dto.setDomicilio(rs.getString(COLUMN_POSITION_DOMICILIO));
            if (rs.wasNull())
                dto.setDomicilio(null);
        } catch (Exception e) {}
        try {
            dto.setTelefono(rs.getString(COLUMN_POSITION_TELEFONO));
            if (rs.wasNull())
                dto.setTelefono(null);
        } catch (Exception e) {}
        try {
            dto.setCelular(rs.getString(COLUMN_POSITION_CELULAR));
            if (rs.wasNull())
                dto.setCelular(null);
        } catch (Exception e) {}
        try {
            dto.setEmail(rs.getString(COLUMN_POSITION_EMAIL));
            if (rs.wasNull())
                dto.setEmail(null);
        } catch (Exception e) {}
        try {
            dto.setObservaciones(rs.getString(COLUMN_POSITION_OBSERVACIONES));
            if (rs.wasNull())
                dto.setObservaciones(null);
        } catch (Exception e) {}
    }


}

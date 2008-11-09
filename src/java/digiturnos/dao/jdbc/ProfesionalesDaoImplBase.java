package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.logging.*;

public class ProfesionalesDaoImplBase extends PostgresqlBase implements ProfesionalesDaoBase {

    private Integer limit;
    private Integer offset;

    protected static final String SQL_SELECT = "SELECT profesionales.idprofesional, profesionales.dni, profesionales.nombre, profesionales.sexo, profesionales.fechanacimiento, profesionales.domicilio, profesionales.telefono, profesionales.celular, profesionales.email, profesionales.observaciones, profesionales.idespecialidad FROM profesionales ";
    protected static final String SQL_MAX_ID = "SELECT MAX(idprofesional) FROM profesionales";
    protected static final String SQL_INSERT = "INSERT INTO profesionales (dni, nombre, sexo, fechanacimiento, domicilio, telefono, celular, email, observaciones, idespecialidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String SQL_INSERT_WITH_ID = "INSERT INTO profesionales (idprofesional, dni, nombre, sexo, fechanacimiento, domicilio, telefono, celular, email, observaciones, idespecialidad) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
    protected static final String SQL_UPDATE = "UPDATE profesionales SET idprofesional = ?, dni = ?, nombre = ?, sexo = ?, fechanacimiento = ?, domicilio = ?, telefono = ?, celular = ?, email = ?, observaciones = ?, idespecialidad = ? WHERE profesionales.idprofesional = ?";
    protected static final String SQL_DELETE = "DELETE FROM profesionales WHERE profesionales.idprofesional = ?";

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

    public  Profesionales[] findAll() throws ProfesionalesDaoException {
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public ProfesionalesPK insert(Profesionales dto) throws ProfesionalesDaoException {
        return insert(dto, null);
    }

    public  ProfesionalesPK insert(Profesionales dto, Integer idprofesional) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Profesionales idto = null;
        ProfesionalesPK pk = null;

        try {
            String sql = SQL_INSERT;
            if (!(idprofesional == null ))
                sql = SQL_INSERT_WITH_ID;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            if (!(idprofesional == null ))
                ps.setInt(paramCount++,  dto.getIdprofesional());
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
            if (dto.getIdespecialidad() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getIdespecialidad());
            ps.executeUpdate();
            log.trace("SQL: " + sql);
            if (idprofesional == null ) {
                int cIdprofesional = -1;
                String sqlIdprofesional = "select currval('profesionales_idprofesional_seq')";
                PreparedStatement psIdprofesional = con.prepareStatement(sqlIdprofesional);
                ResultSet rsIdprofesional = psIdprofesional.executeQuery();
                while (rsIdprofesional.next())
                    cIdprofesional = rsIdprofesional.getInt(1);
                dto.setIdprofesional(cIdprofesional);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  int update(ProfesionalesPK pk, Profesionales dto) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (dto.getIdprofesional() == null)
                ps.setNull(1, 4);
            else
                ps.setInt(1,  dto.getIdprofesional());
            if (dto.getDni() == null)
                ps.setNull(2, 4);
            else
                ps.setInt(2,  dto.getDni());
            if (dto.getNombre() == null)
                ps.setNull(3, 12);
            else
                ps.setString(3,  dto.getNombre());
            if (dto.getSexo() == null)
                ps.setNull(4, 1);
            else
                ps.setString(4,  dto.getSexo());
            if (dto.getFechanacimiento() == null)
                ps.setNull(5, 91);
            else
                ps.setDate(5,  dto.getFechanacimiento());
            if (dto.getDomicilio() == null)
                ps.setNull(6, 12);
            else
                ps.setString(6,  dto.getDomicilio());
            if (dto.getTelefono() == null)
                ps.setNull(7, 1);
            else
                ps.setString(7,  dto.getTelefono());
            if (dto.getCelular() == null)
                ps.setNull(8, 1);
            else
                ps.setString(8,  dto.getCelular());
            if (dto.getEmail() == null)
                ps.setNull(9, 12);
            else
                ps.setString(9,  dto.getEmail());
            if (dto.getObservaciones() == null)
                ps.setNull(10, 12);
            else
                ps.setString(10,  dto.getObservaciones());
            if (dto.getIdespecialidad() == null)
                ps.setNull(11, 4);
            else
                ps.setInt(11,  dto.getIdespecialidad());
            ps.setInt(12,  pk.getIdprofesional());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  int delete(ProfesionalesPK pk) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  pk.getIdprofesional());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales findWhereOIDEquals(long oid) throws ProfesionalesDaoException {
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales findByPrimaryKey(ProfesionalesPK pk) throws ProfesionalesDaoException {
        return findByPrimaryKey(pk.getIdprofesional());
    }

    public  Profesionales findByPrimaryKey(Integer idprofesional) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.idprofesional = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprofesional);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findByEspecialidades(EspecialidadesPK pk) throws ProfesionalesDaoException {
        return findByEspecialidades(pk.getIdespecialidad());
    }


    public  Profesionales[] findByEspecialidades(Integer idespecialidad) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE idespecialidad = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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


    public  Profesionales[] findWhereIdprofesionalEquals(Integer idprofesional) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.idprofesional = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprofesional);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereDniEquals(Integer dni) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.dni = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereNombreEquals(String nombre) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.nombre = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereSexoEquals(String sexo) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.sexo = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.fechanacimiento = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereDomicilioEquals(String domicilio) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.domicilio = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereTelefonoEquals(String telefono) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.telefono = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereCelularEquals(String celular) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.celular = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereEmailEquals(String email) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.email = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereObservacionesEquals(String observaciones) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.observaciones = ?";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Profesionales[] findWhereIdespecialidadEquals(Integer idespecialidad) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE profesionales.idespecialidad = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public  Object[][] findBySelect(String sql, Object[] sqlParams) throws ProfesionalesDaoException {
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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


    public Profesionales[] findByWhere(String where, Object[] sqlParams) throws ProfesionalesDaoException {
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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


    public int countAll() throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(ProfesionalesPK pk) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE idprofesional = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk.getIdprofesional());
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(Integer idprofesional) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE idprofesional = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprofesional);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdprofesionalEquals(Integer idprofesional) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE idprofesional = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idprofesional);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereDniEquals(Integer dni) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE dni = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereNombreEquals(String nombre) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE nombre = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereSexoEquals(String sexo) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE sexo = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, sexo);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE fechanacimiento = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setDate(1, fechanacimiento);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereDomicilioEquals(String domicilio) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE domicilio = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, domicilio);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereTelefonoEquals(String telefono) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE telefono = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, telefono);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereCelularEquals(String celular) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE celular = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, celular);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereEmailEquals(String email) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE email = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereObservacionesEquals(String observaciones) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE observaciones = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, observaciones);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdespecialidadEquals(Integer idespecialidad) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales WHERE idespecialidad = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByWhere(String where, Object[] sqlParams) throws ProfesionalesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idprofesional) from profesionales ";
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
            throw new ProfesionalesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new ProfesionalesDaoException("Exception: " + e.getMessage(), e);
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

    protected  Profesionales[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Profesionales dto = new Profesionales();
            populateDto(dto, rs);
            results.add(dto);
        }
        Profesionales retValue[] = new Profesionales[results.size()];
        results.toArray(retValue);
        return retValue;
    }


    protected  Profesionales fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Profesionales dto = new Profesionales();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    protected static void populateDto(Profesionales dto, ResultSet rs) throws SQLException {
        try {
            dto.setIdprofesional(rs.getInt(COLUMN_POSITION_IDPROFESIONAL));
            if (rs.wasNull())
                dto.setIdprofesional(null);
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
        try {
            dto.setIdespecialidad(rs.getInt(COLUMN_POSITION_IDESPECIALIDAD));
            if (rs.wasNull())
                dto.setIdespecialidad(null);
        } catch (Exception e) {}
    }


}

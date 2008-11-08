package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;
import org.apache.commons.logging.*;

public class EspecialidadesDaoImplBase extends PostgresqlBase implements EspecialidadesDaoBase {

    private Integer limit;
    private Integer offset;

    protected static final String SQL_SELECT = "SELECT especialidades.idespecialidad, especialidades.especialidad, especialidades.idservicio, (SELECT servicio FROM Servicios WHERE IDservicio=especialidades.IDservicio) FROM especialidades ";
    protected static final String SQL_MAX_ID = "SELECT MAX(idespecialidad) FROM especialidades";
    protected static final String SQL_INSERT = "INSERT INTO especialidades (especialidad, idservicio) VALUES (?, ?)";
    protected static final String SQL_INSERT_WITH_ID = "INSERT INTO especialidades (idespecialidad, especialidad, idservicio) VALUES (?, ?, ?)";
    protected static final String SQL_UPDATE = "UPDATE especialidades SET idespecialidad = ?, especialidad = ?, idservicio = ? WHERE especialidades.idespecialidad = ?";
    protected static final String SQL_DELETE = "DELETE FROM especialidades WHERE especialidades.idespecialidad = ?";

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

    public  Especialidades[] findAll() throws EspecialidadesDaoException {
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public EspecialidadesPK insert(Especialidades dto) throws EspecialidadesDaoException {
        return insert(dto, null);
    }

    public  EspecialidadesPK insert(Especialidades dto, Integer idespecialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Especialidades idto = null;
        EspecialidadesPK pk = null;

        try {
            String sql = SQL_INSERT;
            if (!(idespecialidad == null ))
                sql = SQL_INSERT_WITH_ID;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            if (!(idespecialidad == null ))
                ps.setInt(paramCount++,  dto.getIdespecialidad());
            ps.setString(paramCount++,  dto.getEspecialidad());
            if (dto.getIdservicio() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getIdservicio());
            ps.executeUpdate();
            log.trace("SQL: " + sql);
            if (idespecialidad == null ) {
                int cIdespecialidad = -1;
                String sqlIdespecialidad = "select currval('especialidades_idespecialidad_seq')";
                PreparedStatement psIdespecialidad = con.prepareStatement(sqlIdespecialidad);
                ResultSet rsIdespecialidad = psIdespecialidad.executeQuery();
                while (rsIdespecialidad.next())
                    cIdespecialidad = rsIdespecialidad.getInt(1);
                dto.setIdespecialidad(cIdespecialidad);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  int update(EspecialidadesPK pk, Especialidades dto) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (dto.getIdespecialidad() == null)
                ps.setNull(1, 4);
            else
                ps.setInt(1,  dto.getIdespecialidad());
            if (dto.getEspecialidad() == null)
                ps.setNull(2, 12);
            else
                ps.setString(2,  dto.getEspecialidad());
            if (dto.getIdservicio() == null)
                ps.setNull(3, 4);
            else
                ps.setInt(3,  dto.getIdservicio());
            ps.setInt(4,  pk.getIdespecialidad());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  int delete(EspecialidadesPK pk) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  pk.getIdespecialidad());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Especialidades findWhereOIDEquals(long oid) throws EspecialidadesDaoException {
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Especialidades findByPrimaryKey(EspecialidadesPK pk) throws EspecialidadesDaoException {
        return findByPrimaryKey(pk.getIdespecialidad());
    }

    public  Especialidades findByPrimaryKey(Integer idespecialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE especialidades.idespecialidad = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Especialidades[] findByServicios(ServiciosPK pk) throws EspecialidadesDaoException {
        return findByServicios(pk.getIdservicio());
    }


    public  Especialidades[] findByServicios(Integer idservicio) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE idservicio = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idservicio);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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


    public  Especialidades[] findWhereIdespecialidadEquals(Integer idespecialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE especialidades.idespecialidad = ?";
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Especialidades[] findWhereEspecialidadEquals(String especialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE especialidades.especialidad = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, especialidad);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Especialidades[] findWhereIdservicioEquals(Integer idservicio) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE especialidades.idservicio = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idservicio);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public  Object[][] findBySelect(String sql, Object[] sqlParams) throws EspecialidadesDaoException {
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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


    public Especialidades[] findByWhere(String where, Object[] sqlParams) throws EspecialidadesDaoException {
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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


    public int countAll() throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(EspecialidadesPK pk) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades WHERE idespecialidad = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk.getIdespecialidad());
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(Integer idespecialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades WHERE idespecialidad = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdespecialidadEquals(Integer idespecialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades WHERE idespecialidad = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idespecialidad);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereEspecialidadEquals(String especialidad) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades WHERE especialidad = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, especialidad);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdservicioEquals(Integer idservicio) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades WHERE idservicio = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idservicio);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    public int countByWhere(String where, Object[] sqlParams) throws EspecialidadesDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idespecialidad) from especialidades ";
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
            throw new EspecialidadesDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new EspecialidadesDaoException("Exception: " + e.getMessage(), e);
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

    protected  Especialidades[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Especialidades dto = new Especialidades();
            populateDto(dto, rs);
            results.add(dto);
        }
        Especialidades retValue[] = new Especialidades[results.size()];
        results.toArray(retValue);
        return retValue;
    }


    protected  Especialidades fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Especialidades dto = new Especialidades();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    protected static void populateDto(Especialidades dto, ResultSet rs) throws SQLException {
        try {
            dto.setIdespecialidad(rs.getInt(COLUMN_POSITION_IDESPECIALIDAD));
            if (rs.wasNull())
                dto.setIdespecialidad(null);
        } catch (Exception e) {}
        try {
            dto.setEspecialidad(rs.getString(COLUMN_POSITION_ESPECIALIDAD));
            if (rs.wasNull())
                dto.setEspecialidad(null);
        } catch (Exception e) {}
        try {
            dto.setIdservicio(rs.getInt(COLUMN_POSITION_IDSERVICIO));
            if (rs.wasNull())
                dto.setIdservicio(null);
        } catch (Exception e) {}
        try {
            dto.setServicio(rs.getString(COLUMN_POSITION_SERVICIO));
            if (rs.wasNull())
                dto.setServicio(null);
        } catch (Exception e) {}
    }


}

package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;

public class TiposUsuarioDaoImplBase extends PostgresqlBase implements TiposUsuarioDaoBase {

    private Integer limit;
    private Integer offset;

    protected static final String SQL_SELECT = "SELECT tipos_usuario.idtipousuario, tipos_usuario.tipo FROM tipos_usuario ";
    protected static final String SQL_MAX_ID = "SELECT MAX(idtipousuario) FROM tipos_usuario";
    protected static final String SQL_INSERT = "INSERT INTO tipos_usuario (tipo) VALUES (?)";
    protected static final String SQL_INSERT_WITH_ID = "INSERT INTO tipos_usuario (idtipousuario, tipo) VALUES (?, ?)";
    protected static final String SQL_UPDATE = "UPDATE tipos_usuario SET idtipousuario = ?, tipo = ? WHERE tipos_usuario.idtipousuario = ?";
    protected static final String SQL_DELETE = "DELETE FROM tipos_usuario WHERE tipos_usuario.idtipousuario = ?";

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

    public  TiposUsuario[] findAll() throws TiposUsuarioDaoException {
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
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public TiposUsuarioPK insert(TiposUsuario dto) throws TiposUsuarioDaoException {
        return insert(dto, null);
    }

    public  TiposUsuarioPK insert(TiposUsuario dto, Integer idtipousuario) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        TiposUsuario idto = null;
        TiposUsuarioPK pk = null;

        try {
            String sql = SQL_INSERT;
            if (!(idtipousuario == null ))
                sql = SQL_INSERT_WITH_ID;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            if (!(idtipousuario == null ))
                ps.setInt(paramCount++,  dto.getIdtipousuario());
            ps.setString(paramCount++,  dto.getTipo());
            ps.executeUpdate();
            log.trace("SQL: " + sql);
            if (idtipousuario == null ) {
                int cIdtipousuario = -1;
                String sqlIdtipousuario = "select currval('tipos_usuario_idtipousuario_seq')";
                PreparedStatement psIdtipousuario = con.prepareStatement(sqlIdtipousuario);
                ResultSet rsIdtipousuario = psIdtipousuario.executeQuery();
                while (rsIdtipousuario.next())
                    cIdtipousuario = rsIdtipousuario.getInt(1);
                dto.setIdtipousuario(cIdtipousuario);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  int update(TiposUsuarioPK pk, TiposUsuario dto) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (dto.getIdtipousuario() == null)
                ps.setNull(1, 4);
            else
                ps.setInt(1,  dto.getIdtipousuario());
            if (dto.getTipo() == null)
                ps.setNull(2, 12);
            else
                ps.setString(2,  dto.getTipo());
            ps.setInt(3,  pk.getIdtipousuario());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  int delete(TiposUsuarioPK pk) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  pk.getIdtipousuario());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  TiposUsuario findWhereOIDEquals(long oid) throws TiposUsuarioDaoException {
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
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  TiposUsuario findByPrimaryKey(TiposUsuarioPK pk) throws TiposUsuarioDaoException {
        return findByPrimaryKey(pk.getIdtipousuario());
    }

    public  TiposUsuario findByPrimaryKey(Integer idtipousuario) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE tipos_usuario.idtipousuario = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtipousuario);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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


    public  TiposUsuario[] findWhereIdtipousuarioEquals(Integer idtipousuario) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE tipos_usuario.idtipousuario = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtipousuario);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  TiposUsuario[] findWhereTipoEquals(String tipo) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE tipos_usuario.tipo = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public  Object[][] findBySelect(String sql, Object[] sqlParams) throws TiposUsuarioDaoException {
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
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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


    public TiposUsuario[] findByWhere(String where, Object[] sqlParams) throws TiposUsuarioDaoException {
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
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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


    public int countAll() throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(TiposUsuarioPK pk) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario WHERE idtipousuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk.getIdtipousuario());
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(Integer idtipousuario) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario WHERE idtipousuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtipousuario);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdtipousuarioEquals(Integer idtipousuario) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario WHERE idtipousuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtipousuario);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereTipoEquals(String tipo) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario WHERE tipo = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, tipo);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    public int countByWhere(String where, Object[] sqlParams) throws TiposUsuarioDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idtipousuario) from tipos_usuario ";
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
            throw new TiposUsuarioDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new TiposUsuarioDaoException("Exception: " + e.getMessage(), e);
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

    protected  TiposUsuario[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            TiposUsuario dto = new TiposUsuario();
            populateDto(dto, rs);
            results.add(dto);
        }
        TiposUsuario retValue[] = new TiposUsuario[results.size()];
        results.toArray(retValue);
        return retValue;
    }


    protected  TiposUsuario fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            TiposUsuario dto = new TiposUsuario();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    protected static void populateDto(TiposUsuario dto, ResultSet rs) throws SQLException {
        try {
            dto.setIdtipousuario(rs.getInt(COLUMN_POSITION_IDTIPOUSUARIO));
            if (rs.wasNull())
                dto.setIdtipousuario(null);
        } catch (Exception e) {}
        try {
            dto.setTipo(rs.getString(COLUMN_POSITION_TIPO));
            if (rs.wasNull())
                dto.setTipo(null);
        } catch (Exception e) {}
    }


}

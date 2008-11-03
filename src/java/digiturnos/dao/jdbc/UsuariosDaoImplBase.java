package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;

public class UsuariosDaoImplBase extends PostgresqlBase implements UsuariosDaoBase {

    private Integer limit;
    private Integer offset;

    protected static final String SQL_SELECT = "SELECT usuarios.idusuario, usuarios.dni, usuarios.nombre, usuarios.email, usuarios.password, usuarios.idtipousuario FROM usuarios ";
    protected static final String SQL_MAX_ID = "SELECT MAX(idusuario) FROM usuarios";
    protected static final String SQL_INSERT = "INSERT INTO usuarios (dni, nombre, email, password, idtipousuario) VALUES (?, ?, ?, ?, ?)";
    protected static final String SQL_INSERT_WITH_ID = "INSERT INTO usuarios (idusuario, dni, nombre, email, password, idtipousuario) VALUES (?, ?, ?, ?, ?, ?)";
    protected static final String SQL_UPDATE = "UPDATE usuarios SET idusuario = ?, dni = ?, nombre = ?, email = ?, password = ?, idtipousuario = ? WHERE usuarios.idusuario = ?";
    protected static final String SQL_DELETE = "DELETE FROM usuarios WHERE usuarios.idusuario = ?";

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

    public  Usuarios[] findAll() throws UsuariosDaoException {
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public UsuariosPK insert(Usuarios dto) throws UsuariosDaoException {
        return insert(dto, null);
    }

    public  UsuariosPK insert(Usuarios dto, Integer idusuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Usuarios idto = null;
        UsuariosPK pk = null;

        try {
            String sql = SQL_INSERT;
            if (!(idusuario == null ))
                sql = SQL_INSERT_WITH_ID;
            con = getConnection();
            con.setAutoCommit(false);
            ps = con.prepareStatement(sql);
            int paramCount = 1;
            if (!(idusuario == null ))
                ps.setInt(paramCount++,  dto.getIdusuario());
            if (dto.getDni() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getDni());
            ps.setString(paramCount++,  dto.getNombre());
            ps.setString(paramCount++,  dto.getEmail());
            ps.setString(paramCount++,  dto.getPassword());
            if (dto.getIdtipousuario() == null)
                ps.setInt(paramCount++, 0);
            else
                ps.setInt(paramCount++,  dto.getIdtipousuario());
            ps.executeUpdate();
            log.trace("SQL: " + sql);
            if (idusuario == null ) {
                int cIdusuario = -1;
                String sqlIdusuario = "select currval('usuarios_idusuario_seq')";
                PreparedStatement psIdusuario = con.prepareStatement(sqlIdusuario);
                ResultSet rsIdusuario = psIdusuario.executeQuery();
                while (rsIdusuario.next())
                    cIdusuario = rsIdusuario.getInt(1);
                dto.setIdusuario(cIdusuario);
            }
            con.commit();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  int update(UsuariosPK pk, Usuarios dto) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_UPDATE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            if (dto.getIdusuario() == null)
                ps.setNull(1, 4);
            else
                ps.setInt(1,  dto.getIdusuario());
            if (dto.getDni() == null)
                ps.setNull(2, 4);
            else
                ps.setInt(2,  dto.getDni());
            if (dto.getNombre() == null)
                ps.setNull(3, 12);
            else
                ps.setString(3,  dto.getNombre());
            if (dto.getEmail() == null)
                ps.setNull(4, 12);
            else
                ps.setString(4,  dto.getEmail());
            if (dto.getPassword() == null)
                ps.setNull(5, 1);
            else
                ps.setString(5,  dto.getPassword());
            if (dto.getIdtipousuario() == null)
                ps.setNull(6, 4);
            else
                ps.setInt(6,  dto.getIdtipousuario());
            ps.setInt(7,  pk.getIdusuario());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  int delete(UsuariosPK pk) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        int numRows = -1;

        try {
            String sql = SQL_DELETE;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1,  pk.getIdusuario());
            numRows = ps.executeUpdate();
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios findWhereOIDEquals(long oid) throws UsuariosDaoException {
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios findByPrimaryKey(UsuariosPK pk) throws UsuariosDaoException {
        return findByPrimaryKey(pk.getIdusuario());
    }

    public  Usuarios findByPrimaryKey(Integer idusuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.idusuario = ?";
            sql += getOrderByClause();
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchSingleResult(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findByTiposUsuario(TiposUsuarioPK pk) throws UsuariosDaoException {
        return findByTiposUsuario(pk.getIdtipousuario());
    }


    public  Usuarios[] findByTiposUsuario(Integer idtipousuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE idtipousuario = ?";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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


    public  Usuarios[] findWhereIdusuarioEquals(Integer idusuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.idusuario = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findWhereDniEquals(Integer dni) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.dni = ?";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findWhereNombreEquals(String nombre) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.nombre = ?";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findWhereEmailEquals(String email) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.email = ?";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findWherePasswordEquals(String password) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.password = ?";
            sql += getOrderByClause();
            if (limit != null && limit.intValue() > 0)
                sql += " LIMIT " + limit;
            if (offset != null && offset.intValue() > 0)
                sql += " OFFSET " + offset;
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            log.trace("SQL: " + sql);
            rs = ps.executeQuery();
            return fetchMultipleResults(rs);
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Usuarios[] findWhereIdtipousuarioEquals(Integer idtipousuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            String sql = SQL_SELECT + " WHERE usuarios.idtipousuario = ?";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public  Object[][] findBySelect(String sql, Object[] sqlParams) throws UsuariosDaoException {
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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


    public Usuarios[] findByWhere(String where, Object[] sqlParams) throws UsuariosDaoException {
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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


    public int countAll() throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios";
            con = getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(UsuariosPK pk) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE idusuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pk.getIdusuario());
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countByPrimaryKey(Integer idusuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE idusuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdusuarioEquals(Integer idusuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE idusuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idusuario);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereDniEquals(Integer dni) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE dni = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, dni);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereNombreEquals(String nombre) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE nombre = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereEmailEquals(String email) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE email = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, email);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWherePasswordEquals(String password) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE password = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, password);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countWhereIdtipousuarioEquals(Integer idtipousuario) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios WHERE idtipousuario = ? ";
            con = getConnection();
            ps = con.prepareStatement(sql);
            ps.setInt(1, idtipousuario);
            rs = ps.executeQuery();
            while (rs.next())
                count = rs.getInt(1);
            return count;
        } catch (SQLException e) {
            logger.error("SQLException: " + e.getMessage(), e);
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    public int countByWhere(String where, Object[] sqlParams) throws UsuariosDaoException {
        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        int count = 0;
        try {
            String sql = "SELECT count(idusuario) from usuarios ";
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
            throw new UsuariosDaoException("SQLException: " + e.getMessage(), e);
        } catch (Exception e) {
            logger.error("Exception: " + e.getMessage(), e);
            throw new UsuariosDaoException("Exception: " + e.getMessage(), e);
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

    protected  Usuarios[] fetchMultipleResults(ResultSet rs) throws SQLException {
        ArrayList results = new ArrayList();
        while (rs.next()) {
            Usuarios dto = new Usuarios();
            populateDto(dto, rs);
            results.add(dto);
        }
        Usuarios retValue[] = new Usuarios[results.size()];
        results.toArray(retValue);
        return retValue;
    }


    protected  Usuarios fetchSingleResult(ResultSet rs) throws SQLException {
        if (rs.next()) {
            Usuarios dto = new Usuarios();
            populateDto(dto, rs);
            return dto;
        } else 
            return null;
    }

    protected static void populateDto(Usuarios dto, ResultSet rs) throws SQLException {
        try {
            dto.setIdusuario(rs.getInt(COLUMN_POSITION_IDUSUARIO));
            if (rs.wasNull())
                dto.setIdusuario(null);
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
            dto.setEmail(rs.getString(COLUMN_POSITION_EMAIL));
            if (rs.wasNull())
                dto.setEmail(null);
        } catch (Exception e) {}
        try {
            dto.setPassword(rs.getString(COLUMN_POSITION_PASSWORD));
            if (rs.wasNull())
                dto.setPassword(null);
        } catch (Exception e) {}
        try {
            dto.setIdtipousuario(rs.getInt(COLUMN_POSITION_IDTIPOUSUARIO));
            if (rs.wasNull())
                dto.setIdtipousuario(null);
        } catch (Exception e) {}
    }


}

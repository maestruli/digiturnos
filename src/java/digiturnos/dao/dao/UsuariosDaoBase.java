/* 
 * UsuariosDaoBase.java
 * Created: 3 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * UsuariosDao.java
 * 
 * This file was automatically generated using the DaoGenerator
 * by Tom Menegatos.  For more info contact tom@menegatos.com
 * 
 */ 

package digiturnos.dao.dao;

import java.util.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;


/** 
  * DAO for the <b>usuarios</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: usuarios
  * ----------------------------------------------
  *     column: idusuario        serial null
  *     column: dni        int4 null
  *     column: nombre        varchar null
  *     column: email        varchar null
  *     column: password        bpchar null
  *     column: idtipousuario        int4 null
  * 
  * Primary Key(s):  idusuario
  * </pre>
  * 
  */ 
public interface UsuariosDaoBase {

    /** column ordinal value constant for usuarios.idusuario serial */
    public static final int COLUMN_POSITION_IDUSUARIO = 1;
    /** column ordinal value constant for usuarios.dni int4 */
    public static final int COLUMN_POSITION_DNI = 2;
    /** column ordinal value constant for usuarios.nombre varchar */
    public static final int COLUMN_POSITION_NOMBRE = 3;
    /** column ordinal value constant for usuarios.email varchar */
    public static final int COLUMN_POSITION_EMAIL = 4;
    /** column ordinal value constant for usuarios.password bpchar */
    public static final int COLUMN_POSITION_PASSWORD = 5;
    /** column ordinal value constant for usuarios.idtipousuario int4 */
    public static final int COLUMN_POSITION_IDTIPOUSUARIO = 6;

    /** canonical name constant for usuarios.idusuario serial */
    public static final String COLUMN_IDUSUARIO = "usuarios.idusuario";
    /** canonical name constant for usuarios.dni int4 */
    public static final String COLUMN_DNI = "usuarios.dni";
    /** canonical name constant for usuarios.nombre varchar */
    public static final String COLUMN_NOMBRE = "usuarios.nombre";
    /** canonical name constant for usuarios.email varchar */
    public static final String COLUMN_EMAIL = "usuarios.email";
    /** canonical name constant for usuarios.password bpchar */
    public static final String COLUMN_PASSWORD = "usuarios.password";
    /** canonical name constant for usuarios.idtipousuario int4 */
    public static final String COLUMN_IDTIPOUSUARIO = "usuarios.idtipousuario";

    /** simple name constant for usuarios.idusuario serial */
    public static final String COLUMN_SIMPLE_IDUSUARIO = "usuarios.idusuario";
    /** simple name constant for usuarios.dni int4 */
    public static final String COLUMN_SIMPLE_DNI = "usuarios.dni";
    /** simple name constant for usuarios.nombre varchar */
    public static final String COLUMN_SIMPLE_NOMBRE = "usuarios.nombre";
    /** simple name constant for usuarios.email varchar */
    public static final String COLUMN_SIMPLE_EMAIL = "usuarios.email";
    /** simple name constant for usuarios.password bpchar */
    public static final String COLUMN_SIMPLE_PASSWORD = "usuarios.password";
    /** simple name constant for usuarios.idtipousuario int4 */
    public static final String COLUMN_SIMPLE_IDTIPOUSUARIO = "usuarios.idtipousuario";

    /** 
      * Sets the column to use in a single order by clause.  
      * Normally you would supply one of the COLUMN_ constants but
      * since the parameter is a string you can pass it more, including 
      * ASCENDING and DESCENDING commands.  Just be sure to check your code
      * so that the resulting SQL statement is syntactically correct.  This
      * shouldn't be exposed to a client interface directly since there might
      * be a chance for a SQL Injection problem.
      * 
      */ 
    public void setOrderByColumn(String column);

    /** 
      * Sets the order by column and whether it is ascending or descending
      * true for desc, false for ascending.
      * 
      * @param column the column name
      * @param descending true if the results should be sorted in descending order
      */ 
    public void setOrderByColumn(String column, boolean descending);

    /** 
      * Creates an ORDER BY clase based on the array of column names passed.  
      * Normally you would supply an array of the COLUMN_ constants but
      * since the parameter is a string you can pass it the COLUMN_ constants concatenated with the 
      * ASCENDING and DESCENDING commands.  Just be sure to check your code
      * so that the resulting SQL statement is syntactically correct.  This
      * shouldn't be exposed to a client interface directly since there might
      * be a chance for a SQL Injection problem.
      * 
      */ 
    public void setOrderByColumns(String[][] columns);

    /** 
      * Sets the maximum number of rows to be returned.  
      * 
      */ 
    public void setLimit(Integer limit);

    /** 
      * Sets the offset for the rows returned.  The offset is a positive 
      * integer value that determines where in the result set the query
      * will start returning rows.
      * 
      */ 
    public void setOffset(Integer offset);

    /** 
      * Retuns the columns set in the methods setOrderByColumn() and setOrderByColumns()
      */ 
    public String[][] getOrderByColumns();

    /** 
      * Returns a string representation of the ORDER BY.
      */ 
    public String getOrderByClause();

    /** 
      * Inserts a row in the usuarios table.
      * When passed with only a usuarios object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  UsuariosPK insert(Usuarios dto) throws UsuariosDaoException;

    /** 
      * Inserts a row in the usuarios table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  UsuariosPK insert(Usuarios dto, Integer idusuario) throws UsuariosDaoException;
    /** 
      * Updates a row in the usuarios table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(UsuariosPK pk, Usuarios dto) throws UsuariosDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(UsuariosPK pk) throws UsuariosDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Usuarios findByPrimaryKey(UsuariosPK pk) throws UsuariosDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Usuarios findByPrimaryKey(Integer idusuario) throws UsuariosDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Usuarios[] findAll() throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * idusuario is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWhereIdusuarioEquals(Integer idusuario) throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * dni is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWhereDniEquals(Integer dni) throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * nombre is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWhereNombreEquals(String nombre) throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * email is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWhereEmailEquals(String email) throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * password is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWherePasswordEquals(String password) throws UsuariosDaoException;

    /** 
      * Returns rows from the database where 
      * idtipousuario is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Usuarios[] findWhereIdtipousuarioEquals(Integer idtipousuario) throws UsuariosDaoException;

    public  Usuarios[] findByTiposUsuario(Integer idtipousuario) throws UsuariosDaoException;

    public  Usuarios[] findByTiposUsuario(TiposUsuarioPK foreignKey) throws UsuariosDaoException;

    public Usuarios[] findByWhere(String where, Object[] sqlParams) throws UsuariosDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws UsuariosDaoException;

    public int countAll() throws UsuariosDaoException;

    public int countByPrimaryKey(UsuariosPK pk) throws UsuariosDaoException;

    public int countByPrimaryKey(Integer idusuario) throws UsuariosDaoException;

    public int countWhereIdusuarioEquals(Integer idusuario) throws UsuariosDaoException;

    public int countWhereDniEquals(Integer dni) throws UsuariosDaoException;

    public int countWhereNombreEquals(String nombre) throws UsuariosDaoException;

    public int countWhereEmailEquals(String email) throws UsuariosDaoException;

    public int countWherePasswordEquals(String password) throws UsuariosDaoException;

    public int countWhereIdtipousuarioEquals(Integer idtipousuario) throws UsuariosDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws UsuariosDaoException;

}

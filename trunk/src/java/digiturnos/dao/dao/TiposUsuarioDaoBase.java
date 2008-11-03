/* 
 * TiposUsuarioDaoBase.java
 * Created: 3 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * TiposUsuarioDao.java
 * 
 * This file was automatically generated using the DaoGenerator
 * by Tom Menegatos.  For more info contact tom@menegatos.com
 * 
 */ 

package digiturnos.dao.dao;

import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;


/** 
  * DAO for the <b>tipos_usuario</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: tipos_usuario
  * ----------------------------------------------
  *     column: idtipousuario        serial null
  *     column: tipo        varchar null
  * 
  * Primary Key(s):  idtipousuario
  * </pre>
  * 
  */ 
public interface TiposUsuarioDaoBase {

    /** column ordinal value constant for tipos_usuario.idtipousuario serial */
    public static final int COLUMN_POSITION_IDTIPOUSUARIO = 1;
    /** column ordinal value constant for tipos_usuario.tipo varchar */
    public static final int COLUMN_POSITION_TIPO = 2;

    /** canonical name constant for tipos_usuario.idtipousuario serial */
    public static final String COLUMN_IDTIPOUSUARIO = "tipos_usuario.idtipousuario";
    /** canonical name constant for tipos_usuario.tipo varchar */
    public static final String COLUMN_TIPO = "tipos_usuario.tipo";

    /** simple name constant for tipos_usuario.idtipousuario serial */
    public static final String COLUMN_SIMPLE_IDTIPOUSUARIO = "tipos_usuario.idtipousuario";
    /** simple name constant for tipos_usuario.tipo varchar */
    public static final String COLUMN_SIMPLE_TIPO = "tipos_usuario.tipo";

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
      * Inserts a row in the tipos_usuario table.
      * When passed with only a tipos_usuario object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  TiposUsuarioPK insert(TiposUsuario dto) throws TiposUsuarioDaoException;

    /** 
      * Inserts a row in the tipos_usuario table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  TiposUsuarioPK insert(TiposUsuario dto, Integer idtipousuario) throws TiposUsuarioDaoException;
    /** 
      * Updates a row in the tipos_usuario table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(TiposUsuarioPK pk, TiposUsuario dto) throws TiposUsuarioDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(TiposUsuarioPK pk) throws TiposUsuarioDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  TiposUsuario findByPrimaryKey(TiposUsuarioPK pk) throws TiposUsuarioDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  TiposUsuario findByPrimaryKey(Integer idtipousuario) throws TiposUsuarioDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  TiposUsuario[] findAll() throws TiposUsuarioDaoException;

    /** 
      * Returns rows from the database where 
      * idtipousuario is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  TiposUsuario[] findWhereIdtipousuarioEquals(Integer idtipousuario) throws TiposUsuarioDaoException;

    /** 
      * Returns rows from the database where 
      * tipo is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  TiposUsuario[] findWhereTipoEquals(String tipo) throws TiposUsuarioDaoException;

    public TiposUsuario[] findByWhere(String where, Object[] sqlParams) throws TiposUsuarioDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws TiposUsuarioDaoException;

    public int countAll() throws TiposUsuarioDaoException;

    public int countByPrimaryKey(TiposUsuarioPK pk) throws TiposUsuarioDaoException;

    public int countByPrimaryKey(Integer idtipousuario) throws TiposUsuarioDaoException;

    public int countWhereIdtipousuarioEquals(Integer idtipousuario) throws TiposUsuarioDaoException;

    public int countWhereTipoEquals(String tipo) throws TiposUsuarioDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws TiposUsuarioDaoException;

}

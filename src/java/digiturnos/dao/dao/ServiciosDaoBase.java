/* 
 * ServiciosDaoBase.java
 * Created: 7 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * ServiciosDao.java
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
  * DAO for the <b>servicios</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: servicios
  * ----------------------------------------------
  *     column: idservicio        serial null
  *     column: servicio        varchar null
  * 
  * Primary Key(s):  idservicio
  * </pre>
  * 
  */ 
public interface ServiciosDaoBase {

    /** column ordinal value constant for servicios.idservicio serial */
    public static final int COLUMN_POSITION_IDSERVICIO = 1;
    /** column ordinal value constant for servicios.servicio varchar */
    public static final int COLUMN_POSITION_SERVICIO = 2;

    /** canonical name constant for servicios.idservicio serial */
    public static final String COLUMN_IDSERVICIO = "servicios.idservicio";
    /** canonical name constant for servicios.servicio varchar */
    public static final String COLUMN_SERVICIO = "servicios.servicio";

    /** simple name constant for servicios.idservicio serial */
    public static final String COLUMN_SIMPLE_IDSERVICIO = "servicios.idservicio";
    /** simple name constant for servicios.servicio varchar */
    public static final String COLUMN_SIMPLE_SERVICIO = "servicios.servicio";

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
      * Inserts a row in the servicios table.
      * When passed with only a servicios object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  ServiciosPK insert(Servicios dto) throws ServiciosDaoException;

    /** 
      * Inserts a row in the servicios table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  ServiciosPK insert(Servicios dto, Integer idservicio) throws ServiciosDaoException;
    /** 
      * Updates a row in the servicios table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(ServiciosPK pk, Servicios dto) throws ServiciosDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(ServiciosPK pk) throws ServiciosDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Servicios findByPrimaryKey(ServiciosPK pk) throws ServiciosDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Servicios findByPrimaryKey(Integer idservicio) throws ServiciosDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Servicios[] findAll() throws ServiciosDaoException;

    /** 
      * Returns rows from the database where 
      * idservicio is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Servicios[] findWhereIdservicioEquals(Integer idservicio) throws ServiciosDaoException;

    /** 
      * Returns rows from the database where 
      * servicio is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Servicios[] findWhereServicioEquals(String servicio) throws ServiciosDaoException;

    public Servicios[] findByWhere(String where, Object[] sqlParams) throws ServiciosDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws ServiciosDaoException;

    public int countAll() throws ServiciosDaoException;

    public int countByPrimaryKey(ServiciosPK pk) throws ServiciosDaoException;

    public int countByPrimaryKey(Integer idservicio) throws ServiciosDaoException;

    public int countWhereIdservicioEquals(Integer idservicio) throws ServiciosDaoException;

    public int countWhereServicioEquals(String servicio) throws ServiciosDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws ServiciosDaoException;

}

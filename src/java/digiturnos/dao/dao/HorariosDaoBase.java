/* 
 * HorariosDaoBase.java
 * Created: 9 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * HorariosDao.java
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
  * DAO for the <b>horarios</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: horarios
  * ----------------------------------------------
  *     column: idhorario        serial null
  *     column: idprofesional        int4 null
  *     column: dia        varchar null
  *     column: desde        time null
  *     column: hasta        time null
  * 
  * Primary Key(s):  idhorario
  * </pre>
  * 
  */ 
public interface HorariosDaoBase {

    /** column ordinal value constant for horarios.idhorario serial */
    public static final int COLUMN_POSITION_IDHORARIO = 1;
    /** column ordinal value constant for horarios.idprofesional int4 */
    public static final int COLUMN_POSITION_IDPROFESIONAL = 2;
    /** column ordinal value constant for horarios.dia varchar */
    public static final int COLUMN_POSITION_DIA = 3;
    /** column ordinal value constant for horarios.desde time */
    public static final int COLUMN_POSITION_DESDE = 4;
    /** column ordinal value constant for horarios.hasta time */
    public static final int COLUMN_POSITION_HASTA = 5;

    /** canonical name constant for horarios.idhorario serial */
    public static final String COLUMN_IDHORARIO = "horarios.idhorario";
    /** canonical name constant for horarios.idprofesional int4 */
    public static final String COLUMN_IDPROFESIONAL = "horarios.idprofesional";
    /** canonical name constant for horarios.dia varchar */
    public static final String COLUMN_DIA = "horarios.dia";
    /** canonical name constant for horarios.desde time */
    public static final String COLUMN_DESDE = "horarios.desde";
    /** canonical name constant for horarios.hasta time */
    public static final String COLUMN_HASTA = "horarios.hasta";

    /** simple name constant for horarios.idhorario serial */
    public static final String COLUMN_SIMPLE_IDHORARIO = "horarios.idhorario";
    /** simple name constant for horarios.idprofesional int4 */
    public static final String COLUMN_SIMPLE_IDPROFESIONAL = "horarios.idprofesional";
    /** simple name constant for horarios.dia varchar */
    public static final String COLUMN_SIMPLE_DIA = "horarios.dia";
    /** simple name constant for horarios.desde time */
    public static final String COLUMN_SIMPLE_DESDE = "horarios.desde";
    /** simple name constant for horarios.hasta time */
    public static final String COLUMN_SIMPLE_HASTA = "horarios.hasta";

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
      * Inserts a row in the horarios table.
      * When passed with only a horarios object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  HorariosPK insert(Horarios dto) throws HorariosDaoException;

    /** 
      * Inserts a row in the horarios table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  HorariosPK insert(Horarios dto, Integer idhorario) throws HorariosDaoException;
    /** 
      * Updates a row in the horarios table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(HorariosPK pk, Horarios dto) throws HorariosDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(HorariosPK pk) throws HorariosDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Horarios findByPrimaryKey(HorariosPK pk) throws HorariosDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Horarios findByPrimaryKey(Integer idhorario) throws HorariosDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Horarios[] findAll() throws HorariosDaoException;

    /** 
      * Returns rows from the database where 
      * idhorario is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Horarios[] findWhereIdhorarioEquals(Integer idhorario) throws HorariosDaoException;

    /** 
      * Returns rows from the database where 
      * idprofesional is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Horarios[] findWhereIdprofesionalEquals(Integer idprofesional) throws HorariosDaoException;

    /** 
      * Returns rows from the database where 
      * dia is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Horarios[] findWhereDiaEquals(String dia) throws HorariosDaoException;

    /** 
      * Returns rows from the database where 
      * desde is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Horarios[] findWhereDesdeEquals(java.sql.Time desde) throws HorariosDaoException;

    /** 
      * Returns rows from the database where 
      * hasta is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Horarios[] findWhereHastaEquals(java.sql.Time hasta) throws HorariosDaoException;

    public  Horarios[] findByProfesionales(Integer idprofesional) throws HorariosDaoException;

    public  Horarios[] findByProfesionales(ProfesionalesPK foreignKey) throws HorariosDaoException;

    public Horarios[] findByWhere(String where, Object[] sqlParams) throws HorariosDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws HorariosDaoException;

    public int countAll() throws HorariosDaoException;

    public int countByPrimaryKey(HorariosPK pk) throws HorariosDaoException;

    public int countByPrimaryKey(Integer idhorario) throws HorariosDaoException;

    public int countWhereIdhorarioEquals(Integer idhorario) throws HorariosDaoException;

    public int countWhereIdprofesionalEquals(Integer idprofesional) throws HorariosDaoException;

    public int countWhereDiaEquals(String dia) throws HorariosDaoException;

    public int countWhereDesdeEquals(java.sql.Time desde) throws HorariosDaoException;

    public int countWhereHastaEquals(java.sql.Time hasta) throws HorariosDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws HorariosDaoException;

}

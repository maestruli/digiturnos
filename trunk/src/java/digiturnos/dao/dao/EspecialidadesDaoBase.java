/* 
 * EspecialidadesDaoBase.java
 * Created: 7 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * EspecialidadesDao.java
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
  * DAO for the <b>especialidades</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: especialidades
  * ----------------------------------------------
  *     column: idespecialidad        serial null
  *     column: especialidad        varchar null
  *     column: idservicio        int4 null
  * 
  * Primary Key(s):  idespecialidad
  * </pre>
  * 
  */ 
public interface EspecialidadesDaoBase {

    /** column ordinal value constant for especialidades.idespecialidad serial */
    public static final int COLUMN_POSITION_IDESPECIALIDAD = 1;
    /** column ordinal value constant for especialidades.especialidad varchar */
    public static final int COLUMN_POSITION_ESPECIALIDAD = 2;
    /** column ordinal value constant for especialidades.idservicio int4 */
    public static final int COLUMN_POSITION_IDSERVICIO = 3;

    /** canonical name constant for especialidades.idespecialidad serial */
    public static final String COLUMN_IDESPECIALIDAD = "especialidades.idespecialidad";
    /** canonical name constant for especialidades.especialidad varchar */
    public static final String COLUMN_ESPECIALIDAD = "especialidades.especialidad";
    /** canonical name constant for especialidades.idservicio int4 */
    public static final String COLUMN_IDSERVICIO = "especialidades.idservicio";

    /** simple name constant for especialidades.idespecialidad serial */
    public static final String COLUMN_SIMPLE_IDESPECIALIDAD = "especialidades.idespecialidad";
    /** simple name constant for especialidades.especialidad varchar */
    public static final String COLUMN_SIMPLE_ESPECIALIDAD = "especialidades.especialidad";
    /** simple name constant for especialidades.idservicio int4 */
    public static final String COLUMN_SIMPLE_IDSERVICIO = "especialidades.idservicio";

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
      * Inserts a row in the especialidades table.
      * When passed with only a especialidades object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  EspecialidadesPK insert(Especialidades dto) throws EspecialidadesDaoException;

    /** 
      * Inserts a row in the especialidades table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  EspecialidadesPK insert(Especialidades dto, Integer idespecialidad) throws EspecialidadesDaoException;
    /** 
      * Updates a row in the especialidades table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(EspecialidadesPK pk, Especialidades dto) throws EspecialidadesDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(EspecialidadesPK pk) throws EspecialidadesDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Especialidades findByPrimaryKey(EspecialidadesPK pk) throws EspecialidadesDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Especialidades findByPrimaryKey(Integer idespecialidad) throws EspecialidadesDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Especialidades[] findAll() throws EspecialidadesDaoException;

    /** 
      * Returns rows from the database where 
      * idespecialidad is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Especialidades[] findWhereIdespecialidadEquals(Integer idespecialidad) throws EspecialidadesDaoException;

    /** 
      * Returns rows from the database where 
      * especialidad is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Especialidades[] findWhereEspecialidadEquals(String especialidad) throws EspecialidadesDaoException;

    /** 
      * Returns rows from the database where 
      * idservicio is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Especialidades[] findWhereIdservicioEquals(Integer idservicio) throws EspecialidadesDaoException;

    public  Especialidades[] findByServicios(Integer idservicio) throws EspecialidadesDaoException;

    public  Especialidades[] findByServicios(ServiciosPK foreignKey) throws EspecialidadesDaoException;

    public Especialidades[] findByWhere(String where, Object[] sqlParams) throws EspecialidadesDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws EspecialidadesDaoException;

    public int countAll() throws EspecialidadesDaoException;

    public int countByPrimaryKey(EspecialidadesPK pk) throws EspecialidadesDaoException;

    public int countByPrimaryKey(Integer idespecialidad) throws EspecialidadesDaoException;

    public int countWhereIdespecialidadEquals(Integer idespecialidad) throws EspecialidadesDaoException;

    public int countWhereEspecialidadEquals(String especialidad) throws EspecialidadesDaoException;

    public int countWhereIdservicioEquals(Integer idservicio) throws EspecialidadesDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws EspecialidadesDaoException;

}

/* 
 * TurnosDaoBase.java
 * Created: 9 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * TurnosDao.java
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
  * DAO for the <b>turnos</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: turnos
  * ----------------------------------------------
  *     column: idturno        serial null
  *     column: idpaciente        int4 null
  *     column: idprofesional        int4 null
  *     column: fecha        date null
  *     column: hora        time null
  * 
  * Primary Key(s):  idturno
  * </pre>
  * 
  */ 
public interface TurnosDaoBase {

    /** column ordinal value constant for turnos.idturno serial */
    public static final int COLUMN_POSITION_IDTURNO = 1;
    /** column ordinal value constant for turnos.idpaciente int4 */
    public static final int COLUMN_POSITION_IDPACIENTE = 2;
    /** column ordinal value constant for turnos.idprofesional int4 */
    public static final int COLUMN_POSITION_IDPROFESIONAL = 3;
    /** column ordinal value constant for turnos.fecha date */
    public static final int COLUMN_POSITION_FECHA = 4;
    /** column ordinal value constant for turnos.hora time */
    public static final int COLUMN_POSITION_HORA = 5;

    /** canonical name constant for turnos.idturno serial */
    public static final String COLUMN_IDTURNO = "turnos.idturno";
    /** canonical name constant for turnos.idpaciente int4 */
    public static final String COLUMN_IDPACIENTE = "turnos.idpaciente";
    /** canonical name constant for turnos.idprofesional int4 */
    public static final String COLUMN_IDPROFESIONAL = "turnos.idprofesional";
    /** canonical name constant for turnos.fecha date */
    public static final String COLUMN_FECHA = "turnos.fecha";
    /** canonical name constant for turnos.hora time */
    public static final String COLUMN_HORA = "turnos.hora";

    /** simple name constant for turnos.idturno serial */
    public static final String COLUMN_SIMPLE_IDTURNO = "turnos.idturno";
    /** simple name constant for turnos.idpaciente int4 */
    public static final String COLUMN_SIMPLE_IDPACIENTE = "turnos.idpaciente";
    /** simple name constant for turnos.idprofesional int4 */
    public static final String COLUMN_SIMPLE_IDPROFESIONAL = "turnos.idprofesional";
    /** simple name constant for turnos.fecha date */
    public static final String COLUMN_SIMPLE_FECHA = "turnos.fecha";
    /** simple name constant for turnos.hora time */
    public static final String COLUMN_SIMPLE_HORA = "turnos.hora";

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
      * Inserts a row in the turnos table.
      * When passed with only a turnos object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  TurnosPK insert(Turnos dto) throws TurnosDaoException;

    /** 
      * Inserts a row in the turnos table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  TurnosPK insert(Turnos dto, Integer idturno) throws TurnosDaoException;
    /** 
      * Updates a row in the turnos table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(TurnosPK pk, Turnos dto) throws TurnosDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(TurnosPK pk) throws TurnosDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Turnos findByPrimaryKey(TurnosPK pk) throws TurnosDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Turnos findByPrimaryKey(Integer idturno) throws TurnosDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Turnos[] findAll() throws TurnosDaoException;

    /** 
      * Returns rows from the database where 
      * idturno is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Turnos[] findWhereIdturnoEquals(Integer idturno) throws TurnosDaoException;

    /** 
      * Returns rows from the database where 
      * idpaciente is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Turnos[] findWhereIdpacienteEquals(Integer idpaciente) throws TurnosDaoException;

    /** 
      * Returns rows from the database where 
      * idprofesional is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Turnos[] findWhereIdprofesionalEquals(Integer idprofesional) throws TurnosDaoException;

    /** 
      * Returns rows from the database where 
      * fecha is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Turnos[] findWhereFechaEquals(java.sql.Date fecha) throws TurnosDaoException;

    /** 
      * Returns rows from the database where 
      * hora is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Turnos[] findWhereHoraEquals(java.sql.Time hora) throws TurnosDaoException;

    public  Turnos[] findByProfesionales(Integer idprofesional) throws TurnosDaoException;

    public  Turnos[] findByProfesionales(ProfesionalesPK foreignKey) throws TurnosDaoException;

    public  Turnos[] findByPacientes(Integer idpaciente) throws TurnosDaoException;

    public  Turnos[] findByPacientes(PacientesPK foreignKey) throws TurnosDaoException;

    public Turnos[] findByWhere(String where, Object[] sqlParams) throws TurnosDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws TurnosDaoException;

    public int countAll() throws TurnosDaoException;

    public int countByPrimaryKey(TurnosPK pk) throws TurnosDaoException;

    public int countByPrimaryKey(Integer idturno) throws TurnosDaoException;

    public int countWhereIdturnoEquals(Integer idturno) throws TurnosDaoException;

    public int countWhereIdpacienteEquals(Integer idpaciente) throws TurnosDaoException;

    public int countWhereIdprofesionalEquals(Integer idprofesional) throws TurnosDaoException;

    public int countWhereFechaEquals(java.sql.Date fecha) throws TurnosDaoException;

    public int countWhereHoraEquals(java.sql.Time hora) throws TurnosDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws TurnosDaoException;

}

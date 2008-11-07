/* 
 * PacientesDaoBase.java
 * Created: 7 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * PacientesDao.java
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
  * DAO for the <b>pacientes</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: pacientes
  * ----------------------------------------------
  *     column: idpaciente        serial null
  *     column: historiaclinica        int4 null
  *     column: dni        int4 null
  *     column: nombre        varchar null
  *     column: sexo        bpchar null
  *     column: fechanacimiento        date null
  *     column: domicilio        varchar null
  *     column: telefono        bpchar null
  *     column: celular        bpchar null
  *     column: email        varchar null
  *     column: observaciones        text null
  * 
  * Primary Key(s):  idpaciente
  * </pre>
  * 
  */ 
public interface PacientesDaoBase {

    /** column ordinal value constant for pacientes.idpaciente serial */
    public static final int COLUMN_POSITION_IDPACIENTE = 1;
    /** column ordinal value constant for pacientes.historiaclinica int4 */
    public static final int COLUMN_POSITION_HISTORIACLINICA = 2;
    /** column ordinal value constant for pacientes.dni int4 */
    public static final int COLUMN_POSITION_DNI = 3;
    /** column ordinal value constant for pacientes.nombre varchar */
    public static final int COLUMN_POSITION_NOMBRE = 4;
    /** column ordinal value constant for pacientes.sexo bpchar */
    public static final int COLUMN_POSITION_SEXO = 5;
    /** column ordinal value constant for pacientes.fechanacimiento date */
    public static final int COLUMN_POSITION_FECHANACIMIENTO = 6;
    /** column ordinal value constant for pacientes.domicilio varchar */
    public static final int COLUMN_POSITION_DOMICILIO = 7;
    /** column ordinal value constant for pacientes.telefono bpchar */
    public static final int COLUMN_POSITION_TELEFONO = 8;
    /** column ordinal value constant for pacientes.celular bpchar */
    public static final int COLUMN_POSITION_CELULAR = 9;
    /** column ordinal value constant for pacientes.email varchar */
    public static final int COLUMN_POSITION_EMAIL = 10;
    /** column ordinal value constant for pacientes.observaciones text */
    public static final int COLUMN_POSITION_OBSERVACIONES = 11;

    /** canonical name constant for pacientes.idpaciente serial */
    public static final String COLUMN_IDPACIENTE = "pacientes.idpaciente";
    /** canonical name constant for pacientes.historiaclinica int4 */
    public static final String COLUMN_HISTORIACLINICA = "pacientes.historiaclinica";
    /** canonical name constant for pacientes.dni int4 */
    public static final String COLUMN_DNI = "pacientes.dni";
    /** canonical name constant for pacientes.nombre varchar */
    public static final String COLUMN_NOMBRE = "pacientes.nombre";
    /** canonical name constant for pacientes.sexo bpchar */
    public static final String COLUMN_SEXO = "pacientes.sexo";
    /** canonical name constant for pacientes.fechanacimiento date */
    public static final String COLUMN_FECHANACIMIENTO = "pacientes.fechanacimiento";
    /** canonical name constant for pacientes.domicilio varchar */
    public static final String COLUMN_DOMICILIO = "pacientes.domicilio";
    /** canonical name constant for pacientes.telefono bpchar */
    public static final String COLUMN_TELEFONO = "pacientes.telefono";
    /** canonical name constant for pacientes.celular bpchar */
    public static final String COLUMN_CELULAR = "pacientes.celular";
    /** canonical name constant for pacientes.email varchar */
    public static final String COLUMN_EMAIL = "pacientes.email";
    /** canonical name constant for pacientes.observaciones text */
    public static final String COLUMN_OBSERVACIONES = "pacientes.observaciones";

    /** simple name constant for pacientes.idpaciente serial */
    public static final String COLUMN_SIMPLE_IDPACIENTE = "pacientes.idpaciente";
    /** simple name constant for pacientes.historiaclinica int4 */
    public static final String COLUMN_SIMPLE_HISTORIACLINICA = "pacientes.historiaclinica";
    /** simple name constant for pacientes.dni int4 */
    public static final String COLUMN_SIMPLE_DNI = "pacientes.dni";
    /** simple name constant for pacientes.nombre varchar */
    public static final String COLUMN_SIMPLE_NOMBRE = "pacientes.nombre";
    /** simple name constant for pacientes.sexo bpchar */
    public static final String COLUMN_SIMPLE_SEXO = "pacientes.sexo";
    /** simple name constant for pacientes.fechanacimiento date */
    public static final String COLUMN_SIMPLE_FECHANACIMIENTO = "pacientes.fechanacimiento";
    /** simple name constant for pacientes.domicilio varchar */
    public static final String COLUMN_SIMPLE_DOMICILIO = "pacientes.domicilio";
    /** simple name constant for pacientes.telefono bpchar */
    public static final String COLUMN_SIMPLE_TELEFONO = "pacientes.telefono";
    /** simple name constant for pacientes.celular bpchar */
    public static final String COLUMN_SIMPLE_CELULAR = "pacientes.celular";
    /** simple name constant for pacientes.email varchar */
    public static final String COLUMN_SIMPLE_EMAIL = "pacientes.email";
    /** simple name constant for pacientes.observaciones text */
    public static final String COLUMN_SIMPLE_OBSERVACIONES = "pacientes.observaciones";

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
      * Inserts a row in the pacientes table.
      * When passed with only a pacientes object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  PacientesPK insert(Pacientes dto) throws PacientesDaoException;

    /** 
      * Inserts a row in the pacientes table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  PacientesPK insert(Pacientes dto, Integer idpaciente) throws PacientesDaoException;
    /** 
      * Updates a row in the pacientes table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(PacientesPK pk, Pacientes dto) throws PacientesDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(PacientesPK pk) throws PacientesDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Pacientes findByPrimaryKey(PacientesPK pk) throws PacientesDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Pacientes findByPrimaryKey(Integer idpaciente) throws PacientesDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Pacientes[] findAll() throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * idpaciente is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereIdpacienteEquals(Integer idpaciente) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * historiaclinica is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereHistoriaclinicaEquals(Integer historiaclinica) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * dni is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereDniEquals(Integer dni) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * nombre is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereNombreEquals(String nombre) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * sexo is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereSexoEquals(String sexo) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * fechanacimiento is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * domicilio is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereDomicilioEquals(String domicilio) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * telefono is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereTelefonoEquals(String telefono) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * celular is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereCelularEquals(String celular) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * email is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereEmailEquals(String email) throws PacientesDaoException;

    /** 
      * Returns rows from the database where 
      * observaciones is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Pacientes[] findWhereObservacionesEquals(String observaciones) throws PacientesDaoException;

    public Pacientes[] findByWhere(String where, Object[] sqlParams) throws PacientesDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws PacientesDaoException;

    public int countAll() throws PacientesDaoException;

    public int countByPrimaryKey(PacientesPK pk) throws PacientesDaoException;

    public int countByPrimaryKey(Integer idpaciente) throws PacientesDaoException;

    public int countWhereIdpacienteEquals(Integer idpaciente) throws PacientesDaoException;

    public int countWhereHistoriaclinicaEquals(Integer historiaclinica) throws PacientesDaoException;

    public int countWhereDniEquals(Integer dni) throws PacientesDaoException;

    public int countWhereNombreEquals(String nombre) throws PacientesDaoException;

    public int countWhereSexoEquals(String sexo) throws PacientesDaoException;

    public int countWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws PacientesDaoException;

    public int countWhereDomicilioEquals(String domicilio) throws PacientesDaoException;

    public int countWhereTelefonoEquals(String telefono) throws PacientesDaoException;

    public int countWhereCelularEquals(String celular) throws PacientesDaoException;

    public int countWhereEmailEquals(String email) throws PacientesDaoException;

    public int countWhereObservacionesEquals(String observaciones) throws PacientesDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws PacientesDaoException;

}

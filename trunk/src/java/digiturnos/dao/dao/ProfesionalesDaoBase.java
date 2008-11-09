/* 
 * ProfesionalesDaoBase.java
 * Created: 8 de noviembre de 2008
 * 
 * DO NOT MODIFY THIS FILE DIRECTLY IT MAY GET OVERWRITTEN
 * Instead make any modifications to this files subclass
 * ProfesionalesDao.java
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
  * DAO for the <b>profesionales</b> database tabe.
  * Provides methods to perform insert, update, delete and select queries.
  * <br><pre>
  * Table: profesionales
  * ----------------------------------------------
  *     column: idprofesional        serial null
  *     column: dni        int4 null
  *     column: nombre        varchar null
  *     column: sexo        bpchar null
  *     column: fechanacimiento        date null
  *     column: domicilio        varchar null
  *     column: telefono        bpchar null
  *     column: celular        bpchar null
  *     column: email        varchar null
  *     column: observaciones        text null
  *     column: idespecialidad        int4 null
  * 
  * Primary Key(s):  idprofesional
  * </pre>
  * 
  */ 
public interface ProfesionalesDaoBase {

    /** column ordinal value constant for profesionales.idprofesional serial */
    public static final int COLUMN_POSITION_IDPROFESIONAL = 1;
    /** column ordinal value constant for profesionales.dni int4 */
    public static final int COLUMN_POSITION_DNI = 2;
    /** column ordinal value constant for profesionales.nombre varchar */
    public static final int COLUMN_POSITION_NOMBRE = 3;
    /** column ordinal value constant for profesionales.sexo bpchar */
    public static final int COLUMN_POSITION_SEXO = 4;
    /** column ordinal value constant for profesionales.fechanacimiento date */
    public static final int COLUMN_POSITION_FECHANACIMIENTO = 5;
    /** column ordinal value constant for profesionales.domicilio varchar */
    public static final int COLUMN_POSITION_DOMICILIO = 6;
    /** column ordinal value constant for profesionales.telefono bpchar */
    public static final int COLUMN_POSITION_TELEFONO = 7;
    /** column ordinal value constant for profesionales.celular bpchar */
    public static final int COLUMN_POSITION_CELULAR = 8;
    /** column ordinal value constant for profesionales.email varchar */
    public static final int COLUMN_POSITION_EMAIL = 9;
    /** column ordinal value constant for profesionales.observaciones text */
    public static final int COLUMN_POSITION_OBSERVACIONES = 10;
    /** column ordinal value constant for profesionales.idespecialidad int4 */
    public static final int COLUMN_POSITION_IDESPECIALIDAD = 11;

    /** canonical name constant for profesionales.idprofesional serial */
    public static final String COLUMN_IDPROFESIONAL = "profesionales.idprofesional";
    /** canonical name constant for profesionales.dni int4 */
    public static final String COLUMN_DNI = "profesionales.dni";
    /** canonical name constant for profesionales.nombre varchar */
    public static final String COLUMN_NOMBRE = "profesionales.nombre";
    /** canonical name constant for profesionales.sexo bpchar */
    public static final String COLUMN_SEXO = "profesionales.sexo";
    /** canonical name constant for profesionales.fechanacimiento date */
    public static final String COLUMN_FECHANACIMIENTO = "profesionales.fechanacimiento";
    /** canonical name constant for profesionales.domicilio varchar */
    public static final String COLUMN_DOMICILIO = "profesionales.domicilio";
    /** canonical name constant for profesionales.telefono bpchar */
    public static final String COLUMN_TELEFONO = "profesionales.telefono";
    /** canonical name constant for profesionales.celular bpchar */
    public static final String COLUMN_CELULAR = "profesionales.celular";
    /** canonical name constant for profesionales.email varchar */
    public static final String COLUMN_EMAIL = "profesionales.email";
    /** canonical name constant for profesionales.observaciones text */
    public static final String COLUMN_OBSERVACIONES = "profesionales.observaciones";
    /** canonical name constant for profesionales.idespecialidad int4 */
    public static final String COLUMN_IDESPECIALIDAD = "profesionales.idespecialidad";

    /** simple name constant for profesionales.idprofesional serial */
    public static final String COLUMN_SIMPLE_IDPROFESIONAL = "profesionales.idprofesional";
    /** simple name constant for profesionales.dni int4 */
    public static final String COLUMN_SIMPLE_DNI = "profesionales.dni";
    /** simple name constant for profesionales.nombre varchar */
    public static final String COLUMN_SIMPLE_NOMBRE = "profesionales.nombre";
    /** simple name constant for profesionales.sexo bpchar */
    public static final String COLUMN_SIMPLE_SEXO = "profesionales.sexo";
    /** simple name constant for profesionales.fechanacimiento date */
    public static final String COLUMN_SIMPLE_FECHANACIMIENTO = "profesionales.fechanacimiento";
    /** simple name constant for profesionales.domicilio varchar */
    public static final String COLUMN_SIMPLE_DOMICILIO = "profesionales.domicilio";
    /** simple name constant for profesionales.telefono bpchar */
    public static final String COLUMN_SIMPLE_TELEFONO = "profesionales.telefono";
    /** simple name constant for profesionales.celular bpchar */
    public static final String COLUMN_SIMPLE_CELULAR = "profesionales.celular";
    /** simple name constant for profesionales.email varchar */
    public static final String COLUMN_SIMPLE_EMAIL = "profesionales.email";
    /** simple name constant for profesionales.observaciones text */
    public static final String COLUMN_SIMPLE_OBSERVACIONES = "profesionales.observaciones";
    /** simple name constant for profesionales.idespecialidad int4 */
    public static final String COLUMN_SIMPLE_IDESPECIALIDAD = "profesionales.idespecialidad";

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
      * Inserts a row in the profesionales table.
      * When passed with only a profesionales object
      * it assumes the primary keys will be automatically generated.
      * 
      */ 
    public  ProfesionalesPK insert(Profesionales dto) throws ProfesionalesDaoException;

    /** 
      * Inserts a row in the profesionales table.
      * the primary key values must be passed to create the primary key
      * 
      */ 
    public  ProfesionalesPK insert(Profesionales dto, Integer idprofesional) throws ProfesionalesDaoException;
    /** 
      * Updates a row in the profesionales table.
      * The Primary Key Object determines wich row gets updated. Any null values in the DTO will nullify the corresponding values in the table.  Returns the number of rows that were updated.
      */ 
    public  int update(ProfesionalesPK pk, Profesionales dto) throws ProfesionalesDaoException;

    /** 
      * Deletes a row in the database based on the primary key supplied. Returns the number of rows that were deleted
      * 
      */ 
    public  int delete(ProfesionalesPK pk) throws ProfesionalesDaoException;

    /** 
      * Returns a single row of the database based on the primary key object supplied.  Returns <code>null</code> if there are no matches
      * 
      */ 
    public  Profesionales findByPrimaryKey(ProfesionalesPK pk) throws ProfesionalesDaoException;

    /** 
      * Returns a single row of the database based on the primary key information supplied. If there is no match, <code>null</code> is returned.
      * 
      */ 
    public  Profesionales findByPrimaryKey(Integer idprofesional) throws ProfesionalesDaoException;

    /** 
      * Returns all rows of the database.
      * 
      */ 
    public  Profesionales[] findAll() throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * idprofesional is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereIdprofesionalEquals(Integer idprofesional) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * dni is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereDniEquals(Integer dni) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * nombre is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereNombreEquals(String nombre) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * sexo is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereSexoEquals(String sexo) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * fechanacimiento is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * domicilio is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereDomicilioEquals(String domicilio) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * telefono is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereTelefonoEquals(String telefono) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * celular is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereCelularEquals(String celular) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * email is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereEmailEquals(String email) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * observaciones is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereObservacionesEquals(String observaciones) throws ProfesionalesDaoException;

    /** 
      * Returns rows from the database where 
      * idespecialidad is equal to the supplied parameter. If there are no matching rows, an empty array is returned.
      */ 
    public  Profesionales[] findWhereIdespecialidadEquals(Integer idespecialidad) throws ProfesionalesDaoException;

    public  Profesionales[] findByEspecialidades(Integer idespecialidad) throws ProfesionalesDaoException;

    public  Profesionales[] findByEspecialidades(EspecialidadesPK foreignKey) throws ProfesionalesDaoException;

    public Profesionales[] findByWhere(String where, Object[] sqlParams) throws ProfesionalesDaoException;

    public Object[][] findBySelect(String sql, Object[] sqlParams) throws ProfesionalesDaoException;

    public int countAll() throws ProfesionalesDaoException;

    public int countByPrimaryKey(ProfesionalesPK pk) throws ProfesionalesDaoException;

    public int countByPrimaryKey(Integer idprofesional) throws ProfesionalesDaoException;

    public int countWhereIdprofesionalEquals(Integer idprofesional) throws ProfesionalesDaoException;

    public int countWhereDniEquals(Integer dni) throws ProfesionalesDaoException;

    public int countWhereNombreEquals(String nombre) throws ProfesionalesDaoException;

    public int countWhereSexoEquals(String sexo) throws ProfesionalesDaoException;

    public int countWhereFechanacimientoEquals(java.sql.Date fechanacimiento) throws ProfesionalesDaoException;

    public int countWhereDomicilioEquals(String domicilio) throws ProfesionalesDaoException;

    public int countWhereTelefonoEquals(String telefono) throws ProfesionalesDaoException;

    public int countWhereCelularEquals(String celular) throws ProfesionalesDaoException;

    public int countWhereEmailEquals(String email) throws ProfesionalesDaoException;

    public int countWhereObservacionesEquals(String observaciones) throws ProfesionalesDaoException;

    public int countWhereIdespecialidadEquals(Integer idespecialidad) throws ProfesionalesDaoException;

    public int countByWhere(String where, Object[] sqlParams) throws ProfesionalesDaoException;

}

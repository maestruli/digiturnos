package digiturnos.dao.jdbc;

import digiturnos.dao.dao.*;
import digiturnos.dao.dto.*;
import digiturnos.dao.exception.*;
import java.util.*;
import java.sql.*;
import javax.sql.*;
import javax.naming.*;
import org.apache.commons.logging.*;

public class PostgresqlBase  {

    public static Log logger = LogFactory.getLog(PostgresqlBase.class);
    public static Log log = LogFactory.getLog(PostgresqlBase.class);
    public static String dsn = "java:comp/env/jdbc/digiturnos_base";                                            

    public static Connection getConnection() {
        try {
        Context ctx = new InitialContext();
        DataSource ds = (DataSource) ctx.lookup( dsn );
        return ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

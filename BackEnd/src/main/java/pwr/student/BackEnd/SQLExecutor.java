package pwr.student.BackEnd;

import java.sql.*;

public class SQLExecutor {
    private static final String urlPath = "jdbc:sqlite:.\\db\\";
    private final Connection conn;
    private static Connection connect() throws Exception {
        //Class.forName("org.sqlite.JDBC");
        Connection conn = null;
        try {
            // db parameters
            String url = urlPath+"database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

            if (conn != null)
                return conn;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if(conn==null)
                throw new Exception(urlPath);
        }
        return null;
    }
    public SQLExecutor() throws Exception {
        conn = connect();
    }
    public void createNewDatabase(String fileName) {
        String url = urlPath + fileName;

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void createNewTable() {
        String url = urlPath+"database.db";

        String sql = "CREATE TABLE IF NOT EXISTS decision (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	date date NOT NULL,\n"
                + "	component text,\n"
                + "	person text text,\n"
                + "	priority integer, \n"
                + "	description text \n"
                + ");";

        try (Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public void executeSQL(String sql){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public ResultSet select(String sql){
        try{
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public void close() throws SQLException {
        this.conn.close();
    }
}

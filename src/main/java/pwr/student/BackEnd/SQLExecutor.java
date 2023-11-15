package pwr.student.BackEnd;

import java.sql.*;
import java.text.DateFormat;

public class SQLExecutor {
    private static final String urlPath = "jdbc:sqlite:/db/";
    public static Connection connect() {
        Connection conn = null;
        try {
            // db parameters
            String url = urlPath+"database.db";
            // create a connection to the database
            conn = DriverManager.getConnection(url);

            System.out.println("Connection to SQLite has been established.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        } finally {
            if (conn != null)
                return conn;
        }
        return conn;
    }
    public static void createNewDatabase(String fileName) {

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
    public static void createNewTable() {
        // SQLite connection string
        String url = urlPath+"database.db";

        // SQL statement for creating a new table
        String sql = "CREATE TABLE IF NOT EXISTS decision (\n"
                + "	id integer PRIMARY KEY,\n"
                + "	date date NOT NULL,\n"
                + "	component text,\n"
                + "	person text text,\n"
                + "	priority integer, \n"
                + "	description text \n"
                + ");";

        try (Connection conn = DriverManager.getConnection(url);
             Statement stmt = conn.createStatement()) {
            // create a new table
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static void insert(String sql,Connection conn){
        try {
            Statement stmt = conn.createStatement();
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static ResultSet select(String sql,Connection conn){
        try{
            Statement stmt = conn.createStatement();
            return stmt.executeQuery(sql);
        } catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
    public static void main(String[] args) throws SQLException {
        //createNewDatabase("database.db");
        //createNewTable();
        String sql = SQLBuilder.buildInsert("decision", (new Date(1)).toString(),"ExampleComponent","Krzysztof",1,"Example_description");
        Connection conn = connect();
        System.out.println(sql);
        insert(sql,conn);
        String[] columns = new String[] {"id","person"};
        sql = SQLBuilder.buildSelect("decision",columns);
        System.out.println(sql);
        ResultSet rs = select(sql,conn);

        while (rs.next()) {
            System.out.println(rs.getInt("id") +  "\t" +
                    rs.getString("person"));
        }

        conn.close();
    }
}

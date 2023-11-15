package pwr.student.BackEnd;

import java.sql.*;

public class SQLExecutor {
    private static final String urlPath = "jdbc:sqlite:/db/";
    private final Connection conn;
    public SQLExecutor(){
        conn = connect();
    }
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
    public static void main(String[] args) throws SQLException {
        //createNewDatabase("database.db");
        //createNewTable();
        String sql = SQLBuilder.buildInsert("decision", new Date(1),"ExampleComponent","Krzysztof",1,"Example_description");
        SQLExecutor sqlExecutor = new SQLExecutor();
        System.out.println(sql);
        sqlExecutor.executeSQL(sql);
        sql = SQLBuilder.buildDelete("decision",1,10);
        System.out.println(sql);
        sqlExecutor.executeSQL(sql);
        String[] columns = new String[] {"*"};
        sql = SQLBuilder.buildSelect("decision",columns);
        System.out.println(sql);
        ResultSet rs = sqlExecutor.select(sql);

        while (rs.next()) {
            System.out.println(
                      rs.getInt("id") + "\t"
                    + rs.getString("person") + "\t"
                    + MyDate.getRepresentation(rs.getDate("date")) + "\t"
                    + rs.getString("component") + "\t"
                    + rs.getInt("priority") + "\t"
                    + rs.getString("description"));
        }

        sqlExecutor.close();
    }
}

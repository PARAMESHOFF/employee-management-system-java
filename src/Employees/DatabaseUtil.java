package Employees;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DatabaseUtil {

    public static void initializeDatabase() {
        String url = "jdbc:sqlite:employees.db";

        try {
            Class.forName("org.sqlite.JDBC");
            Connection con = DriverManager.getConnection(url);

            String createTable = "CREATE TABLE IF NOT EXISTS employee ("
                    + "id INTEGER PRIMARY KEY, "
                    + "name TEXT, "
                    + "gender TEXT, "
                    + "salary INTEGER)";

            Statement stmt = con.createStatement();
            stmt.execute(createTable);

            stmt.close();
            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

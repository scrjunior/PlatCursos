package cursos.base;


import java.sql.Connection;
import java.sql.DriverManager;

public class DB {

    static String URL = "localhost:3306/";
    static String DATABASE_NAME = "cursos";
    static String USERNAME = "root";
    static String PASSWORD = "Halix2020.";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/cursos?user=root&password=Halix2020.&useUnicode=true&characterEncoding=UTF-8");
        } catch (Exception e) {
            System.out.println(e);
            e.printStackTrace();
        }
        return con;
    }
}

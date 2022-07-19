import java.sql.*;

public class JavaDatabaseConnection {
    public static Connection CONNECTION;
    public static Statement STATEMENT;
    public static ResultSet RESULTSET;

    private static final String URL = "jdbc:postgresql://localhost:5432/postgres";
    private static final String USER = "postgres";
    private static final String PASSWORD = "postgres";


    public static void main(String[] args) throws SQLException {
        String query = "SELECT * FROM developers";
        try {
            CONNECTION = DriverManager.getConnection(URL,USER,PASSWORD);
            STATEMENT = CONNECTION.createStatement();
            RESULTSET = STATEMENT.executeQuery(query);
            System.out.println("developers:");
            while (RESULTSET.next()){
                String name;
                String specialty;
                int salary;

                name = RESULTSET.getString("name");
                specialty = RESULTSET.getString("specialty");
                salary = RESULTSET.getInt("salary");
                System.out.println(" ");
                System.out.println("name " + name);
                System.out.println("specialty " + specialty);
                System.out.println("salary "  + salary);
            }
        } catch (SQLException sqlException){
            sqlException.printStackTrace();
        }finally {
            try {
                CONNECTION.close();
            } catch (SQLException se) {
                System.out.println("Произошла ошибка при закрытии");
            }
            try {
                STATEMENT.close();
            } catch (SQLException se) {
                System.out.println("Произошла ошибка при закрытии");
            }
            try {
                RESULTSET.close();
            } catch (SQLException se) {
                System.out.println("Произошла ошибка при закрытии");
            }
        }
    }
}
package database;

import java.sql.*;
import java.util.Scanner;

public class Select {
    public static final String DRIVER_NAME = "com.mysql.cj.jdbc.Driver";
    public static final String CONNECTION_STR =
            "jdbc:mysql://localhost/web?" +
            "user=root&password=29071982"+
            "&serverTimezone=Europe/Moscow";

    public static void main(String[] args) {
        System.out.println("type search word");
        Scanner scanner = new Scanner(System.in);
        String word = scanner.next();
        try (Connection connection = DriverManager.getConnection(CONNECTION_STR)){
            Class.forName(DRIVER_NAME);

            // BAD - SQL injection
           /*
            Statement statement = connection.createStatement();
            String query = "SELECT title, length FROM courses " +
                            "WHERE title LIKE '%" + word + "%' " +
                            "ORDER BY title";
            ResultSet resultSet = statement.executeQuery(query);
            */

            //GOOD
            String templateQuery = "SELECT title, length FROM courses " +
                    "WHERE title LIKE ? " +
                    "ORDER BY title";
            PreparedStatement preparedStatement =
                    connection.prepareStatement(templateQuery);
            preparedStatement.setString(1, "%" + word + "%");
            ResultSet resultSet = preparedStatement.executeQuery();

            connection.setAutoCommit(false);

            while (resultSet.next()){
                String title = resultSet.getString("title");
                int len = resultSet.getInt("length");
                System.out.printf("%-50s -- %d\n",title,len);
            }

            connection.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

            CallableStatement collableStatement =
                    connection.prepareCall("call countCourses(?)");
            //connection.prepareCall("?= call countCourses(?)");
            collableStatement.execute();
            int count = collableStatement.getInt(1);
            System.out.println("count courses = " + count);

            System.out.println("Insert new course...");
            System.out.println("type title");
            String title = scanner.next();

            System.out.println("type length in hours");
            int len = Integer.parseInt(scanner.next());

            System.out.println("type description");
            String description = scanner.next();

            collableStatement = connection.prepareCall("call insertLine(?,?,?,?)");

            collableStatement.setString(1, title);
            collableStatement.setInt(2, len);
            collableStatement.setString(3, description);
            collableStatement.registerOutParameter(4, Types.INTEGER);

            collableStatement.execute();

            int id = collableStatement.getInt(4);


            System.out.printf("insert title: %s -- lenght: %d -- description: %s -- with id=%d\n", title, len, description, id);
            connection.commit();

            resultSet.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        scanner.close();
    }
}

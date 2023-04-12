import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {
        final String user = "postgres";
        final String password = "Heso9m43";
        final String url = "jdbc:postgresql://localhost:5432/skypro";

        try (Connection connection = DriverManager.getConnection(url, user, password);
        PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee INNER JOIN city\n" +
                "ON employee.city_id=city.city_id")) {
            ResultSet resultSet = preparedStatement.executeQuery();


            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                System.out.println("ID = " + id);

                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                String cityName = resultSet.getString("city_name");
                System.out.println(firstName + " " + lastName + " " + gender + " " + cityName);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}

import java.sql.*;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO{

    final String user = "postgres";
    final String password = "Heso9m43";
    final String url = "jdbc:postgresql://localhost:5432/skypro";

    @Override
    public void insertToTheEmployee(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO employee\n" +
                     "(first_name, last_name, gender, age, city_id)\n" +
                     "VALUES (?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getCity().getCityId());
            preparedStatement.executeUpdate();
            System.out.println("Добавлена новая запись в БД: " + employee.getId() + "\n");

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

    @Override
    public Employee getFromEmployeeById(int id) {
        Employee employee = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee INNER JOIN city\n" +
                     "ON employee.city_id=city.city_id WHERE id = ?")) {
            preparedStatement.setInt(1,id);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                String gender = resultSet.getString("gender");
                int age = resultSet.getInt("age");
                String cityName = resultSet.getString("city_name");
                System.out.println("Получение конкретного объекта Employee c ID=" + id + ":\n" + firstName + " " + lastName + " " + age + " " + gender + " " + cityName+ "\n");


        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        List<Employee> employee = null;
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM employee INNER JOIN city\n" +
                     "ON employee.city_id=city.city_id ORDER BY id")) {
            ResultSet resultSet = preparedStatement.executeQuery();

            System.out.println("Получение списка всех объектов Employee из базы:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String firstName = resultSet.getString("first_name");
                String lastName = resultSet.getString("last_name");
                int age = resultSet.getInt("age");
                String gender = resultSet.getString("gender");
                String cityName = resultSet.getString("city_name");
                System.out.println("ID=" + id + ": " + firstName + " " + lastName + " " + age + " " + gender + " " + cityName);
            }
            System.out.println();

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
        return employee;
    }

    @Override
    public void updateFromEmployeeById(Employee employee) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("UPDATE employee SET first_name = ?, last_name = ?, gender = ?, age = ?, city_id = ? WHERE id = ?")) {

            preparedStatement.setString(1, employee.getFirstName());
            preparedStatement.setString(2, employee.getLastName());
            preparedStatement.setString(3, employee.getGender());
            preparedStatement.setInt(4, employee.getAge());
            preparedStatement.setInt(5, employee.getCity().getCityId());
            preparedStatement.setInt(6, employee.getId());
            preparedStatement.executeUpdate();
            System.out.println("Обновление данных по ID=" + employee.getId() + ": " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getGender() + " " + employee.getAge() + " " + employee.getCity() + "\n");
        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }

    @Override
    public void deleteFromEmployeeById(int id) {
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement preparedStatement = connection.prepareStatement("DELETE FROM employee WHERE id = ?")) {
            preparedStatement.setInt(1,id);
            preparedStatement.executeUpdate();
            System.out.println("Удалена запись под ID=" + id);

        } catch (SQLException e) {
            System.out.println("Ошибка при подключении к базе данных!");
            e.printStackTrace();
        }
    }
}

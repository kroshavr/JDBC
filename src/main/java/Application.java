import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        employeeDAO.insertToTheEmployee(new Employee(1, "Антон", "Сушинцев", "male", 32, new City(5, "Уфа")));
        employeeDAO.getFromEmployeeById(1);
        employeeDAO.getAllEmployee();
        employeeDAO.updateFromEmployeeById(new Employee(6, "Лиля", "Сушинцева", "female", 32, new City(5, "Уфа")));
        employeeDAO.deleteFromEmployeeById(7);
    }
}

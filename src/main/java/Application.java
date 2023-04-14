import java.sql.*;

public class Application {
    public static void main(String[] args) throws SQLException {

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        employeeDAO.insertToTheEmployee(new Employee(1, "Антон", "Сушинцев", "male", 32, 5));
        employeeDAO.getFromEmployeeById(1);
        employeeDAO.getAllEmployee();
        employeeDAO.updateFromEmployeeById(new Employee(6, "Лиля", "Сушинцева", "female", 32,5));
        employeeDAO.deleteFromEmployeeById(7);
    }
}

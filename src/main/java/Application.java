import java.sql.*;

public class Application {
    public static void main(String[] args){

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();

//        employeeDAO.insertToTheEmployee(new Employee(8, "Антон", "Сушинцев", "male", 32, 5));
        employeeDAO.getFromEmployeeById(1);
//        employeeDAO.getAllEmployee();
//        employeeDAO.updateFromEmployeeById(new Employee(6, "Лиля", "Сушинцева", "female", 32,5));
//        employeeDAO.deleteFromEmployeeById(employeeDAO.getFromEmployeeById(6));
    }
}

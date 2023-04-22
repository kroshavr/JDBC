import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Application {
    public static void main(String[] args){

        EmployeeDAO employeeDAO = new EmployeeDaoImpl();
        CityDAO cityDAO = new CityDaoImpl();

        City city = new City("Сочи");
        List<Employee> employees = new ArrayList<>(Arrays.asList(new Employee("Елена", "Бирюкова", "female", 38),
                new Employee("Алена", "Волк", "female", 24)));

        cityDAO.insertCity(city, employees);
        cityDAO.getById(6);
        cityDAO.updateById(10, 11, new Employee("Елена", "Бирюкова", "female", 39));
        cityDAO.deleteById(5);
        cityDAO.getAll();
    }
}

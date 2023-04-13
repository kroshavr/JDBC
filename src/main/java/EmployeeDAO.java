import java.util.List;

public interface EmployeeDAO {
    void insertToTheEmployee (Employee employee);
    Employee getFromEmployeeById (int id);
    List<Employee> getAllEmployee ();
    void updateFromEmployeeById (Employee employee);
    void deleteFromEmployeeById (int id);
}

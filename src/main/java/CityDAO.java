import java.util.List;

public interface CityDAO {
    void insertCity (City city, List<Employee> employees);
    void getById (int id);
    List<City> getAll ();

    void updateById(int id, int employeeId, Employee employee);

    void deleteById (int id);
}

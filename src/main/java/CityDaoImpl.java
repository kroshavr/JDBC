import javax.persistence.*;
import java.util.List;

public class CityDaoImpl implements CityDAO{
    @Override
    public void insertCity(City city, List<Employee> employees) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(city);
        employees.stream()
                .peek(x -> x.setCity(city))
                .forEachOrdered(entityManager :: persist);
        entityManager.getTransaction().commit();
        System.out.println("Добавлена новая запись в БД: " + city + " " + employees);
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void getById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class, id);
        entityManager.getTransaction().commit();
        System.out.println(city.getEmployees());
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public List<City> getAll() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String eQuery = "SELECT s FROM City s";
        TypedQuery<City> query = entityManager.createQuery(eQuery, City.class);
        List<City> cities = query.getResultList();
        entityManager.getTransaction().commit();
        for (City city : cities) {
            System.out.println("ID=" + city.getCityId() + " " + city.getCityName() + " " + city.getEmployees());
        }
        entityManager.close();
        entityManagerFactory.close();
        return cities;
    }

    @Override
    public void updateById(int id, int employeeId, Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        City city = entityManager.find(City.class, id);
        city.getEmployees().stream()
                .filter(x -> x.getId() == employeeId)
                .peek(x -> x.setFirstName(employee.getFirstName()))
                .peek(x -> x.setLastName(employee.getLastName()))
                .peek(x -> x.setGender(employee.getGender()))
                .peek(x -> x.setAge(employee.getAge()))
                .forEachOrdered(entityManager::persist);
        entityManager.getTransaction().commit();
        System.out.println("Обновлена запись в БД");
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public void deleteById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        City city1 = entityManager.find(City.class, id);
        entityManager.remove(city1);
        System.out.println("Удалена запись под ID=" + city1);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
    }
}

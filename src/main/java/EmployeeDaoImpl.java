import javax.persistence.*;
import java.sql.*;
import java.util.List;

public class EmployeeDaoImpl implements EmployeeDAO{

    @Override
    public void insertToTheEmployee(Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        entityManager.persist(employee);
        entityManager.getTransaction().commit();
        System.out.println("Добавлена новая запись в БД: " + employee.getId() + "\n");
        entityManager.close();
        entityManagerFactory.close();
    }

    @Override
    public Employee getFromEmployeeById(int id) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Employee employee = entityManager.find(Employee.class, id);
        entityManager.getTransaction().commit();
        entityManager.close();
        entityManagerFactory.close();
        return employee;
    }

    @Override
    public List<Employee> getAllEmployee() {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        String eQuery = "SELECT s FROM Employee s";
        TypedQuery<Employee> query = entityManager.createQuery(eQuery, Employee.class);
        List<Employee> employees = query.getResultList();
        entityManager.getTransaction().commit();
        for (Employee employee : employees) {
            System.out.println("ID=" + employee.getId() + ": " + employee.getFirstName() + " " + employee.getLastName()+ " " + employee.getAge() + " " + employee.getGender() + " " + employee.getCity());
        }
        entityManager.close();
        entityManagerFactory.close();
        return employees;
    }

    @Override
    public void updateFromEmployeeById(Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("UPDATE Employee SET firstName = :firstName, lastName = :lastName, gender = :gender, age = :age, city = :city WHERE id = :id");
        query.setParameter("firstName", employee.getFirstName());
        query.setParameter("lastName", employee.getLastName());
        query.setParameter("gender", employee.getGender());
        query.setParameter("age", employee.getAge());
        query.setParameter("city", employee.getCity());
        query.setParameter("id", employee.getId());
        query.executeUpdate();
        entityManager.getTransaction().commit();
        System.out.println("Обновление данных по ID=" + employee.getId() + ": " + employee.getFirstName() + " " + employee.getLastName() + " " + employee.getGender() + " " + employee.getAge() + " " + employee.getCity() + "\n");
        entityManager.close();
        entityManagerFactory.close();
    }



    @Override
    public void deleteFromEmployeeById(Employee employee) {
        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("myPersistenceUnit");
        EntityManager entityManager = entityManagerFactory.createEntityManager();
        entityManager.getTransaction().begin();
        Query query = entityManager.createQuery("DELETE FROM Employee s WHERE s.id = :id");
        query.setParameter("id", employee);
        query.executeUpdate();
        System.out.println("Удалена запись под ID=" + employee);
        entityManager.close();
        entityManagerFactory.close();
    }
}

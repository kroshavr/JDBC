import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "city")
public class City {
    @Id
    @Column (name = "city_id")
    @GeneratedValue (strategy = GenerationType.AUTO)
    private int cityId;
    @Column (name = "city_name")
    private String cityName;
    @OneToMany (mappedBy = "city", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private List<Employee> employees;

    public City() {
    }

    public City(String cityName) {
        this.cityName = cityName;
        this.employees = new ArrayList<>();
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @Override
    public String toString() {
        return cityId + cityName;
    }
}

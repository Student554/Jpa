package th.co.geniustree.internship.faris.jpa.jpahead.model;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Entity
public class Company implements Serializable {

    @Id
    private Integer id;

    private String name;

    @OneToMany(cascade = CascadeType.REMOVE, orphanRemoval = true, mappedBy = "company")
    private List<Department> departments;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Company company = (Company) o;
        return Objects.equals(id, company.id);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id);
    }
}

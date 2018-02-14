package th.co.geniustree.internship.faris.jpa.jpahead;

import th.co.geniustree.internship.faris.jpa.jpahead.model.Company;
import th.co.geniustree.internship.faris.jpa.jpahead.model.Department;
import th.co.geniustree.internship.faris.jpa.jpahead.model.Employee;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaMain {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpahead");
        EntityManager em = emf.createEntityManager();

        try {
            em.getTransaction().begin();

            Company company = new Company();
            company.setId(11);
            company.setName("CC");
            em.persist(company);

            Department department = new Department();
            department.setId(2);
            department.setName("Motor");
            department.setCompany(company);
            em.persist(department);

            Employee employee = new Employee();
            employee.setId(1);
            employee.setFname("Faris");
            employee.setLname("Armeen");
            employee.setDepartment(department);
            em.persist(employee);

            em.getTransaction().commit();
        } finally {
            em.close();
            emf.close();
        }


    }
}

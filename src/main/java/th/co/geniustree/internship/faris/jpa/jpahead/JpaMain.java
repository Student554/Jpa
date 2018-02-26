package th.co.geniustree.internship.faris.jpa.jpahead;

import th.co.geniustree.internship.faris.jpa.jpahead.model.Company;
import th.co.geniustree.internship.faris.jpa.jpahead.model.Department;
import th.co.geniustree.internship.faris.jpa.jpahead.model.Employee;

import javax.persistence.*;
import java.util.List;

public class JpaMain {

    public static void main(String[] args) {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpahead");
        EntityManager em = emf.createEntityManager();

        try {

            em.getTransaction().begin();

            //select method

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
            emf.close();

        }
    }

    private static void addEmployee(EntityManager em, Department department, int id, String fName, String lName) {
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFname(fName);
        employee.setLname(lName);
        employee.setDepartment(department);
        em.persist(employee);
    }

    private static Department addDepartment(EntityManager em, Company company, int id, String name) {
        Department department = new Department();
        department.setId(id);
        department.setName(name);
        department.setCompany(company);
        em.persist(department);
        return department;
    }

    private static Company addCompany(EntityManager em, int id, String name) {
        Company company = new Company();
        company.setId(id);
        company.setName(name);
        em.persist(company);
        return company;
    }

    private static Company getCompany(EntityManager em, int i) {
        return em.find(Company.class, i);
    }

    private static Department getDepartment(EntityManager em, int i) {
        return em.find(Department.class, i);
    }

    private static Employee getEmployee(EntityManager em, int i) {
        return em.find(Employee.class, i);
    }

    private static void setDepartmentToEmployee(EntityManager em, int iDepartment, int iEmployee) {
        Department department = getDepartment(em, iDepartment);
        Employee employee = em.find(Employee.class, iEmployee);
        employee.setDepartment(department);
    }

    private static void setCompanyToDepartment(EntityManager em, int iCompany, int iDepartment) {
        Company company1 = getCompany(em, iCompany);
        Department department1 = em.find(Department.class, iDepartment);
        department1.setCompany(company1);
    }

    private static void removeCompany(EntityManager em, int i) {
        Company company = em.find(Company.class, i);
        em.remove(company);
    }

    private static void removeEmployee(EntityManager em, int i) {
        Employee employee = em.find(Employee.class, i);
        em.remove(employee);
    }

    private static void removeDepartment(EntityManager em, int i) {
        Department department = em.find(Department.class, i);
        em.remove(department);
    }

    private static void queryCompany(EntityManager em) {
        TypedQuery<Company> query = em.createQuery("select c from Company c", Company.class);
        List<Company> resultList = query.getResultList();

        System.out.println("---***---Company---***---");

        for (Company com : resultList) {
            System.out.println("Company_Id : " + com.getId());
            System.out.println("Company_Name : " + com.getName());
            System.out.println();
        }
        System.out.println("---***---End---***---");
    }

    private static void queryDepartment(EntityManager em) {
        TypedQuery<Department> query = em.createQuery("select d from Department d", Department.class);
        List<Department> resultList = query.getResultList();

        System.out.println("---***---Department---***---");

        for (Department dep : resultList) {
            System.out.println("Department_Id : " + dep.getId());
            System.out.println("Department_Name : " + dep.getName());

            if (dep.getCompany() != null) {
                System.out.println("Company_Id : " + dep.getCompany().getId());
            } else {
                System.out.println("Company_Id : null");
            }
            System.out.println();
        }
        System.out.println("---***---End---***---");
    }

    private static void queryEmployee(EntityManager em) {
        TypedQuery<Employee> query = em.createQuery("select e from Employee e", Employee.class);
        List<Employee> resultList = query.getResultList();

        System.out.println("---***---Employee---***---");

        for (Employee emp : resultList) {
            System.out.println("Employee_Id : " + emp.getId());
            System.out.println("Employee_FirstName : " + emp.getFname());
            System.out.println("Employee_LastName : " + emp.getLname());

            if (emp.getDepartment() != null) {
                System.out.println("Department_Id : " + emp.getDepartment().getId());
            } else {
                System.out.println("Department_Id : null");
            }
            System.out.println();
        }
        System.out.println("---***---End---***---");
    }
}

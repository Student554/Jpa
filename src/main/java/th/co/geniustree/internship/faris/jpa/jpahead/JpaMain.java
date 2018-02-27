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

            //Select Method

            em.getTransaction().commit();

        } catch (Exception e) {

            em.getTransaction().rollback();
            e.printStackTrace();

        } finally {

            em.close();
            emf.close();

        }
    }

    private static void updateEmployee(EntityManager em, int index, String FName, String LName, int depId) {
        Employee employee = getEmployee(em, index);
        Department department = getDepartment(em, depId);
        employee.setFname(FName);
        employee.setLname(LName);
        employee.setDepartment(department);
    }

    private static void updateDep(EntityManager em, int index, String comName, int comId) {
        Department department = getDepartment(em, index);
        Company company = getCompany(em, comId);
        department.setName(comName);
        department.setCompany(company);
    }

    private static void updateCompany(EntityManager em, int index, String comName) {
        Company company = getCompany(em, index);
        company.setName(comName);
    }

    private static void queryEmployeeById(EntityManager em, int index) {
        Employee employee = getEmployee(em, index);
        System.out.println("Employee_Id : " + employee.getId());
        System.out.println("Employee_FirstName : " + employee.getFname());
        System.out.println("Employee_LastName : " + employee.getLname());
        System.out.println("Department_Id : " + employee.getDepartment().getId());
    }

    private static void queryDepartmentById(EntityManager em, int index) {
        Department department = getDepartment(em, index);
        System.out.println("Department_Id : " + department.getId());
        System.out.println("Department_Name : " + department.getName());
        System.out.println("Company_Id : " + department.getCompany().getId());
    }

    private static void queryCompanyById(EntityManager em, int index) {
        Company company = getCompany(em, index);
        System.out.println("Company_Id : " + company.getId());
        System.out.println("Company_Name : " + company.getName());
    }

    private static void addEmployee(EntityManager em, int indexDepartment, int id, String fName, String lName) {
        Department department = getDepartment(em, indexDepartment);
        Employee employee = new Employee();
        employee.setId(id);
        employee.setFname(fName);
        employee.setLname(lName);
        employee.setDepartment(department);
        em.persist(employee);
    }

    private static Department addDepartment(EntityManager em, int indexCompany, int id, String name) {
        Company company = getCompany(em, indexCompany);
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

    private static void setDepartmentToEmployee(EntityManager em, int indexDepartment, int indexEmployee) {
        Department department = getDepartment(em, indexDepartment);
        Employee employee = em.find(Employee.class, indexEmployee);
        employee.setDepartment(department);
    }

    private static void setCompanyToDepartment(EntityManager em, int indexCompany, int indexDepartment) {
        Company company1 = getCompany(em, indexCompany);
        Department department1 = em.find(Department.class, indexDepartment);
        department1.setCompany(company1);
    }

    private static void removeCompany(EntityManager em, int index) {
        Company company = em.find(Company.class, index);
        em.remove(company);
    }

    private static void removeEmployee(EntityManager em, int index) {
        Employee employee = em.find(Employee.class, index);
        em.remove(employee);
    }

    private static void removeDepartment(EntityManager em, int index) {
        Department department = em.find(Department.class, index);
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

    private static Company getCompany(EntityManager em, int index) {
        return em.find(Company.class, index);
    }

    private static Department getDepartment(EntityManager em, int index) {
        return em.find(Department.class, index);
    }

    private static Employee getEmployee(EntityManager em, int index) {
        return em.find(Employee.class, index);
    }
}

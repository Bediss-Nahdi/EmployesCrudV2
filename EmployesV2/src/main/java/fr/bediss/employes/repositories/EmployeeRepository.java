package fr.bediss.employes.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fr.bediss.employes.entities.Employee;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

}

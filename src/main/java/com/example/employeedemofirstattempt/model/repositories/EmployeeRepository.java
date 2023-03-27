package com.example.employeedemofirstattempt.model.repositories;

import com.example.employeedemofirstattempt.model.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

public interface EmployeeRepository extends JpaRepository<Employee, Long>, EmployeeCustomRepository {
    @Transactional
    @Modifying
    @Query("delete from Employee e where e.id = ?1")
    void deleteEmployeeById(Long id);
}

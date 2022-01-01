package com.example.demo.employee;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Optional<List<Employee>> findByAgeIsGreaterThan(int age);

    @Modifying
    @Query(value = "update Employee e set e.age = :age, e.name = :name WHERE e.id = :id")
    int updateEmployee(@Param("id") Long id, @Param("age") int age, @Param("name") String name);


}

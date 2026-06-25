package com.marcos.spelltrade.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.marcos.spelltrade.domain.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    Boolean existsByUserIdAndStorageId(Long userId, Long StorageId);
}

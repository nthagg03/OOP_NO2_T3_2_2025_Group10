package com.phenikaa.coffeeshop.repository;

import com.phenikaa.coffeeshop.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * Employee Repository - Data access layer for Employee entity
 * Provides CRUD operations and custom queries for employees
 */
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {
    
    // Find by custom employee ID
    Optional<Employee> findByEmployeeId(String employeeId);
    
    // Find by email
    Optional<Employee> findByEmail(String email);
    
    // Find active employees
    List<Employee> findByActiveTrue();
    
    // Find employees by role
    List<Employee> findByRoleAndActiveTrue(String role);
    
    // Search employees by name (case-insensitive)
    List<Employee> findByNameContainingIgnoreCaseAndActiveTrue(String name);
    
    // Find employees hired in date range
    @Query("SELECT e FROM Employee e WHERE e.hireDate BETWEEN :startDate AND :endDate AND e.active = true")
    List<Employee> findEmployeesHiredBetween(@Param("startDate") LocalDateTime startDate, 
                                            @Param("endDate") LocalDateTime endDate);
    
    // Find employees by salary range
    @Query("SELECT e FROM Employee e WHERE e.salary BETWEEN :minSalary AND :maxSalary AND e.active = true")
    List<Employee> findBySalaryRange(@Param("minSalary") Double minSalary, @Param("maxSalary") Double maxSalary);
    
    // Find top performing employees by orders processed
    @Query("SELECT e, COUNT(o) as orderCount FROM Employee e LEFT JOIN e.orders o WHERE e.active = true GROUP BY e ORDER BY orderCount DESC")
    List<Object[]> findTopPerformingEmployees();
    
    // Find employees by total sales
    @Query("SELECT e, COALESCE(SUM(o.totalAmount), 0) as totalSales FROM Employee e LEFT JOIN e.orders o WHERE e.active = true AND (o.status = 'COMPLETED' OR o IS NULL) GROUP BY e ORDER BY totalSales DESC")
    List<Object[]> findEmployeesBySales();
    
    // Count employees by role
    @Query("SELECT COUNT(e) FROM Employee e WHERE e.role = :role AND e.active = true")
    Long countByRole(@Param("role") String role);
    
    // Find managers
    @Query("SELECT e FROM Employee e WHERE e.role = 'MANAGER' AND e.active = true")
    List<Employee> findManagers();
    
    // Find cashiers
    @Query("SELECT e FROM Employee e WHERE e.role = 'CASHIER' AND e.active = true")
    List<Employee> findCashiers();
    
    // Find baristas
    @Query("SELECT e FROM Employee e WHERE e.role = 'BARISTA' AND e.active = true")
    List<Employee> findBaristas();
    
    // Calculate total payroll
    @Query("SELECT COALESCE(SUM(e.salary), 0) FROM Employee e WHERE e.active = true")
    Double calculateTotalPayroll();
    
    // Find employees with no orders processed
    @Query("SELECT e FROM Employee e WHERE e.active = true AND e.id NOT IN (SELECT DISTINCT o.employee.id FROM Order o WHERE o.employee IS NOT NULL)")
    List<Employee> findEmployeesWithoutOrders();
    
    // Calculate average salary by role
    @Query("SELECT e.role, AVG(e.salary) FROM Employee e WHERE e.active = true GROUP BY e.role")
    List<Object[]> findAverageSalaryByRole();
}
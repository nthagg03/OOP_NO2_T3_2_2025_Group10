package com.phenikaa.coffeeshop.service;

import com.phenikaa.coffeeshop.model.Employee;
import com.phenikaa.coffeeshop.repository.EmployeeRepository;
import com.phenikaa.coffeeshop.exception.ResourceNotFoundException;
import com.phenikaa.coffeeshop.exception.DuplicateResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Employee Service - Business logic layer for Employee operations
 * Provides CRUD operations and business rules for employees
 */
@Service
@Transactional
public class EmployeeService {
    
    private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);
    
    @Autowired
    private EmployeeRepository employeeRepository;
    
    // CREATE operations
    public Employee createEmployee(Employee employee) {
        logger.info("Creating new employee: {}", employee.getName());
        
        // Check if employee ID already exists
        if (employeeRepository.findByEmployeeId(employee.getEmployeeId()).isPresent()) {
            throw new DuplicateResourceException("Employee with ID " + employee.getEmployeeId() + " already exists");
        }
        
        // Check if email already exists
        if (employeeRepository.findByEmail(employee.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Employee with email " + employee.getEmail() + " already exists");
        }
        
        // Validate business rules
        validateEmployee(employee);
        
        Employee savedEmployee = employeeRepository.save(employee);
        logger.info("Employee created successfully with ID: {}", savedEmployee.getId());
        return savedEmployee;
    }
    
    // READ operations
    @Transactional(readOnly = true)
    public List<Employee> getAllEmployees() {
        logger.debug("Fetching all employees");
        return employeeRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getAllActiveEmployees() {
        logger.debug("Fetching all active employees");
        return employeeRepository.findByActiveTrue();
    }
    
    @Transactional(readOnly = true)
    public Employee getEmployeeById(Long id) {
        logger.debug("Fetching employee by ID: {}", id);
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Employee getEmployeeByEmployeeId(String employeeId) {
        logger.debug("Fetching employee by employee ID: {}", employeeId);
        return employeeRepository.findByEmployeeId(employeeId)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with ID: " + employeeId));
    }
    
    @Transactional(readOnly = true)
    public Employee getEmployeeByEmail(String email) {
        logger.debug("Fetching employee by email: {}", email);
        return employeeRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Employee not found with email: " + email));
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesByRole(String role) {
        logger.debug("Fetching employees by role: {}", role);
        return employeeRepository.findByRoleAndActiveTrue(role);
    }
    
    @Transactional(readOnly = true)
    public List<Employee> searchEmployeesByName(String name) {
        logger.debug("Searching employees by name: {}", name);
        return employeeRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesHiredBetween(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Fetching employees hired between {} and {}", startDate, endDate);
        return employeeRepository.findEmployeesHiredBetween(startDate, endDate);
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesBySalaryRange(Double minSalary, Double maxSalary) {
        logger.debug("Fetching employees by salary range: {} - {}", minSalary, maxSalary);
        return employeeRepository.findBySalaryRange(minSalary, maxSalary);
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getTopPerformingEmployees() {
        logger.debug("Fetching top performing employees");
        return employeeRepository.findTopPerformingEmployees();
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getEmployeesBySales() {
        logger.debug("Fetching employees by sales performance");
        return employeeRepository.findEmployeesBySales();
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getManagers() {
        logger.debug("Fetching managers");
        return employeeRepository.findManagers();
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getCashiers() {
        logger.debug("Fetching cashiers");
        return employeeRepository.findCashiers();
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getBaristas() {
        logger.debug("Fetching baristas");
        return employeeRepository.findBaristas();
    }
    
    @Transactional(readOnly = true)
    public Long getEmployeeCountByRole(String role) {
        logger.debug("Counting employees by role: {}", role);
        return employeeRepository.countByRole(role);
    }
    
    @Transactional(readOnly = true)
    public Double getTotalPayroll() {
        logger.debug("Calculating total payroll");
        return employeeRepository.calculateTotalPayroll();
    }
    
    @Transactional(readOnly = true)
    public List<Employee> getEmployeesWithoutOrders() {
        logger.debug("Fetching employees without orders");
        return employeeRepository.findEmployeesWithoutOrders();
    }
    
    @Transactional(readOnly = true)
    public List<Object[]> getAverageSalaryByRole() {
        logger.debug("Calculating average salary by role");
        return employeeRepository.findAverageSalaryByRole();
    }
    
    // UPDATE operations
    public Employee updateEmployee(Long id, Employee employeeDetails) {
        logger.info("Updating employee with ID: {}", id);
        
        Employee existingEmployee = getEmployeeById(id);
        
        // Check if changing employee ID would create duplicate
        if (!existingEmployee.getEmployeeId().equals(employeeDetails.getEmployeeId()) &&
            employeeRepository.findByEmployeeId(employeeDetails.getEmployeeId()).isPresent()) {
            throw new DuplicateResourceException("Employee with ID " + employeeDetails.getEmployeeId() + " already exists");
        }
        
        // Check if changing email would create duplicate
        if (!existingEmployee.getEmail().equals(employeeDetails.getEmail()) &&
            employeeRepository.findByEmail(employeeDetails.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Employee with email " + employeeDetails.getEmail() + " already exists");
        }
        
        // Update fields
        existingEmployee.setEmployeeId(employeeDetails.getEmployeeId());
        existingEmployee.setName(employeeDetails.getName());
        existingEmployee.setEmail(employeeDetails.getEmail());
        existingEmployee.setPhone(employeeDetails.getPhone());
        existingEmployee.setRole(employeeDetails.getRole());
        existingEmployee.setSalary(employeeDetails.getSalary());
        existingEmployee.setActive(employeeDetails.getActive());
        
        validateEmployee(existingEmployee);
        
        Employee updatedEmployee = employeeRepository.save(existingEmployee);
        logger.info("Employee updated successfully: {}", updatedEmployee.getId());
        return updatedEmployee;
    }
    
    public Employee updateEmployeeSalary(Long id, Double newSalary) {
        logger.info("Updating salary for employee ID: {} to {}", id, newSalary);
        
        Employee employee = getEmployeeById(id);
        Double oldSalary = employee.getSalary();
        
        if (newSalary <= 0) {
            throw new IllegalArgumentException("Salary must be greater than 0");
        }
        
        employee.setSalary(newSalary);
        
        Employee updatedEmployee = employeeRepository.save(employee);
        logger.info("Salary updated successfully for employee: {} ({} -> {})", 
            employee.getEmployeeId(), oldSalary, newSalary);
        return updatedEmployee;
    }
    
    public Employee updateEmployeeRole(Long id, String newRole) {
        logger.info("Updating role for employee ID: {} to {}", id, newRole);
        
        Employee employee = getEmployeeById(id);
        String oldRole = employee.getRole();
        
        validateRole(newRole);
        employee.setRole(newRole);
        
        Employee updatedEmployee = employeeRepository.save(employee);
        logger.info("Role updated successfully for employee: {} ({} -> {})", 
            employee.getEmployeeId(), oldRole, newRole);
        return updatedEmployee;
    }
    
    public Employee deactivateEmployee(Long id) {
        logger.info("Deactivating employee with ID: {}", id);
        
        Employee employee = getEmployeeById(id);
        employee.setActive(false);
        
        Employee updatedEmployee = employeeRepository.save(employee);
        logger.info("Employee deactivated successfully: {}", id);
        return updatedEmployee;
    }
    
    public Employee reactivateEmployee(Long id) {
        logger.info("Reactivating employee with ID: {}", id);
        
        Employee employee = getEmployeeById(id);
        employee.setActive(true);
        
        Employee updatedEmployee = employeeRepository.save(employee);
        logger.info("Employee reactivated successfully: {}", id);
        return updatedEmployee;
    }
    
    // DELETE operations
    public void deleteEmployee(Long id) {
        logger.info("Deleting employee with ID: {}", id);
        
        Employee employee = getEmployeeById(id);
        
        // Check if employee has processed orders - business rule: don't delete employees with orders
        if (!employee.getOrders().isEmpty()) {
            throw new IllegalStateException("Cannot delete employee with existing orders. Employee ID: " + id);
        }
        
        employeeRepository.delete(employee);
        logger.info("Employee deleted successfully: {}", id);
    }
    
    // Business logic methods
    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }
    
    @Transactional(readOnly = true)
    public boolean isEmployeeIdAvailable(String employeeId) {
        return employeeRepository.findByEmployeeId(employeeId).isEmpty();
    }
    
    // Employee statistics
    @Transactional(readOnly = true)
    public double getEmployeeTotalSales(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee.getTotalSalesAmount();
    }
    
    @Transactional(readOnly = true)
    public int getEmployeeTotalOrdersProcessed(Long employeeId) {
        Employee employee = getEmployeeById(employeeId);
        return employee.getTotalOrdersProcessed();
    }
    
    // Generate unique employee ID
    public String generateEmployeeId(String role) {
        String prefix = switch (role.toUpperCase()) {
            case "MANAGER" -> "MGR";
            case "CASHIER" -> "CSH";
            case "BARISTA" -> "BAR";
            default -> "EMP";
        };
        
        long count = employeeRepository.count() + 1;
        return String.format("%s%04d", prefix, count);
    }
    
    // Role management
    public static final List<String> VALID_ROLES = List.of("MANAGER", "CASHIER", "BARISTA");
    
    @Transactional(readOnly = true)
    public List<String> getValidRoles() {
        return VALID_ROLES;
    }
    
    @Transactional(readOnly = true)
    public boolean isValidRole(String role) {
        return VALID_ROLES.contains(role.toUpperCase());
    }
    
    // Validation helpers
    private void validateEmployee(Employee employee) {
        if (employee.getName() == null || employee.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee name is required");
        }
        
        if (employee.getEmail() == null || employee.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee email is required");
        }
        
        if (employee.getPhone() == null || employee.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee phone is required");
        }
        
        if (employee.getRole() == null || employee.getRole().trim().isEmpty()) {
            throw new IllegalArgumentException("Employee role is required");
        }
        
        if (employee.getSalary() == null || employee.getSalary() <= 0) {
            throw new IllegalArgumentException("Employee salary must be greater than 0");
        }
        
        validateRole(employee.getRole());
        
        // Additional validation
        if (!employee.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        if (employee.getPhone().length() < 10) {
            throw new IllegalArgumentException("Phone number must be at least 10 digits");
        }
    }
    
    private void validateRole(String role) {
        if (!isValidRole(role)) {
            throw new IllegalArgumentException("Invalid role: " + role + ". Valid roles are: " + VALID_ROLES);
        }
    }
}
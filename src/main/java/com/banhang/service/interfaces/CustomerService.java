package com.banhang.service.interfaces;

import com.banhang.model.dto.CustomerDTO;
import com.banhang.model.entity.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    
    // CRUD Operations
    CustomerDTO createCustomer(CustomerDTO customerDTO);
    Optional<CustomerDTO> getCustomerById(Long id);
    CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    void deleteCustomer(Long id);
    
    // List Operations
    List<CustomerDTO> getAllCustomers();
    Page<CustomerDTO> getAllCustomersPageable(Pageable pageable);
    List<CustomerDTO> getActiveCustomers();
    Page<CustomerDTO> getActiveCustomersPageable(Pageable pageable);
    
    // Search Operations
    Optional<CustomerDTO> getCustomerByCode(String customerCode);
    List<CustomerDTO> searchCustomersByName(String name);
    Page<CustomerDTO> searchCustomersByName(String name, Pageable pageable);
    Optional<CustomerDTO> getCustomerByPhone(String phoneNumber);
    Optional<CustomerDTO> getCustomerByEmail(String email);
    
    // Advanced Search
    Page<CustomerDTO> searchCustomers(String name, String email, String phone, Pageable pageable);
    
    // Business Operations
    Page<CustomerDTO> getTopCustomersByOrderCount(Pageable pageable);
    List<CustomerDTO> getCustomersByTotalSpentRange(Double minAmount, Double maxAmount);
    
    // Validation Operations
    boolean existsByCustomerCode(String customerCode);
    boolean existsByEmail(String email);
    boolean existsByPhoneNumber(String phoneNumber);
    
    // Statistics
    long getTotalCustomerCount();
    long getActiveCustomerCount();
    
    // Utility Methods
    CustomerDTO convertToDTO(Customer customer);
    Customer convertToEntity(CustomerDTO customerDTO);
    void activateCustomer(Long id);
    void deactivateCustomer(Long id);
    String generateCustomerCode();
}
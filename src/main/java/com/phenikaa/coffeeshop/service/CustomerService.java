package com.phenikaa.coffeeshop.service;

import com.phenikaa.coffeeshop.model.Customer;
import com.phenikaa.coffeeshop.repository.CustomerRepository;
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
 * Customer Service - Business logic layer for Customer operations
 * Provides CRUD operations and business rules for customers
 */
@Service
@Transactional
public class CustomerService {
    
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);
    
    @Autowired
    private CustomerRepository customerRepository;
    
    // CREATE operations
    public Customer createCustomer(Customer customer) {
        logger.info("Creating new customer: {}", customer.getName());
        
        // Check if customer ID already exists
        if (customerRepository.findByCustomerId(customer.getCustomerId()).isPresent()) {
            throw new DuplicateResourceException("Customer with ID " + customer.getCustomerId() + " already exists");
        }
        
        // Check if email already exists
        if (customerRepository.findByEmail(customer.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Customer with email " + customer.getEmail() + " already exists");
        }
        
        // Check if phone already exists
        if (customerRepository.findByPhone(customer.getPhone()).isPresent()) {
            throw new DuplicateResourceException("Customer with phone " + customer.getPhone() + " already exists");
        }
        
        // Validate business rules
        validateCustomer(customer);
        
        Customer savedCustomer = customerRepository.save(customer);
        logger.info("Customer created successfully with ID: {}", savedCustomer.getId());
        return savedCustomer;
    }
    
    // READ operations
    @Transactional(readOnly = true)
    public List<Customer> getAllCustomers() {
        logger.debug("Fetching all customers");
        return customerRepository.findAll();
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getAllActiveCustomers() {
        logger.debug("Fetching all active customers");
        return customerRepository.findByActiveTrue();
    }
    
    @Transactional(readOnly = true)
    public Customer getCustomerById(Long id) {
        logger.debug("Fetching customer by ID: {}", id);
        return customerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + id));
    }
    
    @Transactional(readOnly = true)
    public Customer getCustomerByCustomerId(String customerId) {
        logger.debug("Fetching customer by customer ID: {}", customerId);
        return customerRepository.findByCustomerId(customerId)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with ID: " + customerId));
    }
    
    @Transactional(readOnly = true)
    public Customer getCustomerByEmail(String email) {
        logger.debug("Fetching customer by email: {}", email);
        return customerRepository.findByEmail(email)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with email: " + email));
    }
    
    @Transactional(readOnly = true)
    public Customer getCustomerByPhone(String phone) {
        logger.debug("Fetching customer by phone: {}", phone);
        return customerRepository.findByPhone(phone)
                .orElseThrow(() -> new ResourceNotFoundException("Customer not found with phone: " + phone));
    }
    
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByName(String name) {
        logger.debug("Searching customers by name: {}", name);
        return customerRepository.findByNameContainingIgnoreCaseAndActiveTrue(name);
    }
    
    @Transactional(readOnly = true)
    public List<Customer> searchCustomersByAddress(String address) {
        logger.debug("Searching customers by address: {}", address);
        return customerRepository.findByAddressContainingIgnoreCaseAndActiveTrue(address);
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getCustomersCreatedBetween(LocalDateTime startDate, LocalDateTime endDate) {
        logger.debug("Fetching customers created between {} and {}", startDate, endDate);
        return customerRepository.findCustomersCreatedBetween(startDate, endDate);
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getTopCustomersByOrderCount() {
        logger.debug("Fetching top customers by order count");
        return customerRepository.findTopCustomersByOrderCount();
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithOrders() {
        logger.debug("Fetching customers with orders");
        return customerRepository.findCustomersWithOrders();
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getCustomersWithoutOrders() {
        logger.debug("Fetching customers without orders");
        return customerRepository.findCustomersWithoutOrders();
    }
    
    @Transactional(readOnly = true)
    public Long getActiveCustomerCount() {
        logger.debug("Counting active customers");
        return customerRepository.countActiveCustomers();
    }
    
    @Transactional(readOnly = true)
    public List<Customer> getCustomersByEmailDomain(String domain) {
        logger.debug("Fetching customers by email domain: {}", domain);
        return customerRepository.findByEmailDomain(domain);
    }
    
    // UPDATE operations
    public Customer updateCustomer(Long id, Customer customerDetails) {
        logger.info("Updating customer with ID: {}", id);
        
        Customer existingCustomer = getCustomerById(id);
        
        // Check if changing customer ID would create duplicate
        if (!existingCustomer.getCustomerId().equals(customerDetails.getCustomerId()) &&
            customerRepository.findByCustomerId(customerDetails.getCustomerId()).isPresent()) {
            throw new DuplicateResourceException("Customer with ID " + customerDetails.getCustomerId() + " already exists");
        }
        
        // Check if changing email would create duplicate
        if (!existingCustomer.getEmail().equals(customerDetails.getEmail()) &&
            customerRepository.findByEmail(customerDetails.getEmail()).isPresent()) {
            throw new DuplicateResourceException("Customer with email " + customerDetails.getEmail() + " already exists");
        }
        
        // Check if changing phone would create duplicate
        if (!existingCustomer.getPhone().equals(customerDetails.getPhone()) &&
            customerRepository.findByPhone(customerDetails.getPhone()).isPresent()) {
            throw new DuplicateResourceException("Customer with phone " + customerDetails.getPhone() + " already exists");
        }
        
        // Update fields
        existingCustomer.setCustomerId(customerDetails.getCustomerId());
        existingCustomer.setName(customerDetails.getName());
        existingCustomer.setEmail(customerDetails.getEmail());
        existingCustomer.setPhone(customerDetails.getPhone());
        existingCustomer.setAddress(customerDetails.getAddress());
        existingCustomer.setActive(customerDetails.getActive());
        
        validateCustomer(existingCustomer);
        
        Customer updatedCustomer = customerRepository.save(existingCustomer);
        logger.info("Customer updated successfully: {}", updatedCustomer.getId());
        return updatedCustomer;
    }
    
    public Customer deactivateCustomer(Long id) {
        logger.info("Deactivating customer with ID: {}", id);
        
        Customer customer = getCustomerById(id);
        customer.setActive(false);
        
        Customer updatedCustomer = customerRepository.save(customer);
        logger.info("Customer deactivated successfully: {}", id);
        return updatedCustomer;
    }
    
    public Customer reactivateCustomer(Long id) {
        logger.info("Reactivating customer with ID: {}", id);
        
        Customer customer = getCustomerById(id);
        customer.setActive(true);
        
        Customer updatedCustomer = customerRepository.save(customer);
        logger.info("Customer reactivated successfully: {}", id);
        return updatedCustomer;
    }
    
    // DELETE operations
    public void deleteCustomer(Long id) {
        logger.info("Deleting customer with ID: {}", id);
        
        Customer customer = getCustomerById(id);
        
        // Check if customer has orders - business rule: don't delete customers with orders
        if (!customer.getOrders().isEmpty()) {
            throw new IllegalStateException("Cannot delete customer with existing orders. Customer ID: " + id);
        }
        
        customerRepository.delete(customer);
        logger.info("Customer deleted successfully: {}", id);
    }
    
    // Business logic methods
    @Transactional(readOnly = true)
    public boolean isEmailAvailable(String email) {
        return customerRepository.findByEmail(email).isEmpty();
    }
    
    @Transactional(readOnly = true)
    public boolean isPhoneAvailable(String phone) {
        return customerRepository.findByPhone(phone).isEmpty();
    }
    
    @Transactional(readOnly = true)
    public boolean isCustomerIdAvailable(String customerId) {
        return customerRepository.findByCustomerId(customerId).isEmpty();
    }
    
    // Customer statistics
    @Transactional(readOnly = true)
    public double getCustomerTotalSpent(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getTotalSpent();
    }
    
    @Transactional(readOnly = true)
    public int getCustomerTotalOrders(Long customerId) {
        Customer customer = getCustomerById(customerId);
        return customer.getTotalOrders();
    }
    
    // Generate unique customer ID
    public String generateCustomerId() {
        String prefix = "CUST";
        long count = customerRepository.count() + 1;
        return String.format("%s%04d", prefix, count);
    }
    
    // Validation helper
    private void validateCustomer(Customer customer) {
        if (customer.getName() == null || customer.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer name is required");
        }
        
        if (customer.getEmail() == null || customer.getEmail().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer email is required");
        }
        
        if (customer.getPhone() == null || customer.getPhone().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer phone is required");
        }
        
        if (customer.getAddress() == null || customer.getAddress().trim().isEmpty()) {
            throw new IllegalArgumentException("Customer address is required");
        }
        
        // Additional validation can be added here (email format, phone format, etc.)
        if (!customer.getEmail().contains("@")) {
            throw new IllegalArgumentException("Invalid email format");
        }
        
        if (customer.getPhone().length() < 10) {
            throw new IllegalArgumentException("Phone number must be at least 10 digits");
        }
    }
}
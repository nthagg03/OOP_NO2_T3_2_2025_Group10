package com.banhang.service.impl;

import com.banhang.model.dto.CustomerDTO;
import com.banhang.model.entity.Customer;
import com.banhang.repository.CustomerRepository;
import com.banhang.service.interfaces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Override
    public CustomerDTO createCustomer(CustomerDTO customerDTO) {
        Customer customer = convertToEntity(customerDTO);
        customer.setCreatedDate(LocalDateTime.now());
        customer.setIsActive(true);
        
        Customer savedCustomer = customerRepository.save(customer);
        return convertToDTO(savedCustomer);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerById(Long id) {
        return customerRepository.findById(id)
                .map(this::convertToDTO);
    }
    
    @Override
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO) {
        Customer existingCustomer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        existingCustomer.setCustomerName(customerDTO.getCustomerName());
        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        existingCustomer.setEmail(customerDTO.getEmail());
        existingCustomer.setAddress(customerDTO.getAddress());
        existingCustomer.setUpdatedDate(LocalDateTime.now());
        
        Customer savedCustomer = customerRepository.save(existingCustomer);
        return convertToDTO(savedCustomer);
    }
    
    @Override
    public void deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        
        customer.setIsActive(false);
        customer.setUpdatedDate(LocalDateTime.now());
        customerRepository.save(customer);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getAllCustomersPageable(Pageable pageable) {
        return customerRepository.findAll(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getActiveCustomers() {
        return customerRepository.findByIsActiveTrue()
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getActiveCustomersPageable(Pageable pageable) {
        return customerRepository.findByIsActiveTrue(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerByCode(String customerCode) {
        return customerRepository.findByCustomerCode(customerCode)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> searchCustomersByName(String name) {
        return customerRepository.findByCustomerNameContainingIgnoreCase(name)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> searchCustomersByName(String name, Pageable pageable) {
        return customerRepository.findByCustomerNameContainingIgnoreCaseAndIsActiveTrue(name, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerByPhone(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<CustomerDTO> getCustomerByEmail(String email) {
        return customerRepository.findByEmail(email)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> searchCustomers(String name, String email, String phone, Pageable pageable) {
        return customerRepository.searchCustomers(name, email, phone, pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Page<CustomerDTO> getTopCustomersByOrderCount(Pageable pageable) {
        return customerRepository.findTopCustomersByOrderCount(pageable)
                .map(this::convertToDTO);
    }
    
    @Override
    @Transactional(readOnly = true)
    public List<CustomerDTO> getCustomersByTotalSpentRange(Double minAmount, Double maxAmount) {
        return customerRepository.findByTotalSpentRange(minAmount, maxAmount)
                .stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByCustomerCode(String customerCode) {
        return customerRepository.existsByCustomerCode(customerCode);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByEmail(String email) {
        return customerRepository.existsByEmail(email);
    }
    
    @Override
    @Transactional(readOnly = true)
    public boolean existsByPhoneNumber(String phoneNumber) {
        return customerRepository.existsByPhoneNumber(phoneNumber);
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getTotalCustomerCount() {
        return customerRepository.count();
    }
    
    @Override
    @Transactional(readOnly = true)
    public long getActiveCustomerCount() {
        return customerRepository.countByIsActiveTrue();
    }
    
    @Override
    public void activateCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setIsActive(true);
        customer.setUpdatedDate(LocalDateTime.now());
        customerRepository.save(customer);
    }
    
    @Override
    public void deactivateCustomer(Long id) {
        Customer customer = customerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Customer not found with id: " + id));
        customer.setIsActive(false);
        customer.setUpdatedDate(LocalDateTime.now());
        customerRepository.save(customer);
    }
    
    @Override
    public String generateCustomerCode() {
        long count = customerRepository.count();
        return "CUS" + String.format("%06d", count + 1);
    }
    
    @Override
    public CustomerDTO convertToDTO(Customer customer) {
        CustomerDTO dto = new CustomerDTO();
        dto.setId(customer.getId());
        dto.setCustomerCode(customer.getCustomerCode());
        dto.setCustomerName(customer.getCustomerName());
        dto.setPhoneNumber(customer.getPhoneNumber());
        dto.setEmail(customer.getEmail());
        dto.setAddress(customer.getAddress());
        dto.setIsActive(customer.getIsActive());
        
        // Set computed fields
        dto.setTotalOrders(customer.getTotalOrders());
        dto.setTotalSpent(customer.getTotalSpent());
        
        return dto;
    }
    
    @Override
    public Customer convertToEntity(CustomerDTO dto) {
        Customer customer = new Customer();
        customer.setId(dto.getId());
        customer.setCustomerCode(dto.getCustomerCode());
        customer.setCustomerName(dto.getCustomerName());
        customer.setPhoneNumber(dto.getPhoneNumber());
        customer.setEmail(dto.getEmail());
        customer.setAddress(dto.getAddress());
        customer.setIsActive(dto.getIsActive() != null ? dto.getIsActive() : true);
        
        return customer;
    }
}
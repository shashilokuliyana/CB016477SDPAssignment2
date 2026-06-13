package com.sdp.logistics.service;

import com.sdp.logistics.dto.AuthResponse;
import com.sdp.logistics.dto.LoginRequest;
import com.sdp.logistics.dto.RegisterRequest;
import com.sdp.logistics.entity.Customer;
import com.sdp.logistics.exception.InvalidCredentialsException;
import com.sdp.logistics.exception.ResourceAlreadyExistsException;
import com.sdp.logistics.repository.CustomerRepository;
import com.sdp.logistics.util.PasswordUtil;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public AuthResponse register(RegisterRequest request) {
        String email = request.getEmail().trim().toLowerCase();

        if (customerRepository.existsByEmail(email)) {
            throw new ResourceAlreadyExistsException("Email address is already registered");
        }

        Customer customer = new Customer(
                request.getName().trim(),
                email,
                PasswordUtil.sha256(request.getPassword()),
                request.getPhone()
        );

        Customer savedCustomer = customerRepository.save(customer);

        return new AuthResponse(
                savedCustomer.getCustomerId(),
                savedCustomer.getName(),
                savedCustomer.getEmail(),
                savedCustomer.getPhone(),
                "Registration successful"
        );
    }

    public AuthResponse login(LoginRequest request) {
        String email = request.getEmail().trim().toLowerCase();
        String hashedPassword = PasswordUtil.sha256(request.getPassword());

        Customer customer = customerRepository.findByEmail(email)
                .orElseThrow(() -> new InvalidCredentialsException("Invalid email or password"));

        if (!hashedPassword.equals(customer.getPasswordHash())) {
            throw new InvalidCredentialsException("Invalid email or password");
        }

        return new AuthResponse(
                customer.getCustomerId(),
                customer.getName(),
                customer.getEmail(),
                customer.getPhone(),
                "Login successful"
        );
    }
}
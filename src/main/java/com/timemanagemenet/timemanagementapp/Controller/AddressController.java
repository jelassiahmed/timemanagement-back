package com.timemanagemenet.timemanagementapp.Controller;

import com.timemanagemenet.timemanagementapp.Entity.Address;
import com.timemanagemenet.timemanagementapp.Service.Address.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController {
    private final AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    @PreAuthorize("hasRole('ROLE_ADMIN')") // Restricted to ROLE_ADMIN
    public ResponseEntity<List<Address>> getAllAddresses() {
        List<Address> addresses = addressService.getAllAddresses();
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable("id") long id) {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();

        // Ensure the authenticated user can access their own address
        if (!isUserAuthorized(keycloakUserId, address.getKeycloakUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        return ResponseEntity.ok(address);
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')") // Restricted to ROLE_EMPLOYEE
    public ResponseEntity<Address> createAddress(@RequestBody Address address) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();
        address.setKeycloakUserId(keycloakUserId);

        Address createdAddress = addressService.createAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable("id") long id, @RequestBody Address address) {
        Address existingAddress = addressService.getAddressById(id);
        if (existingAddress == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();

        // Ensure the authenticated user can update their own address
        if (!isUserAuthorized(keycloakUserId, existingAddress.getKeycloakUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        address.setKeycloakUserId(existingAddress.getKeycloakUserId());
        Address updatedAddress = addressService.updateAddress(id, address);
        return ResponseEntity.ok(updatedAddress);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable("id") long id) {
        Address address = addressService.getAddressById(id);
        if (address == null) {
            return ResponseEntity.notFound().build();
        }

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String keycloakUserId = authentication.getName();

        // Ensure the authenticated user can delete their own address
        if (!isUserAuthorized(keycloakUserId, address.getKeycloakUserId())) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    private boolean isUserAuthorized(String authenticatedUserId, String targetUserId) {
        // Implement your logic to check if the authenticated user is authorized to access the target user's data
        return authenticatedUserId.equals(targetUserId);
    }
}
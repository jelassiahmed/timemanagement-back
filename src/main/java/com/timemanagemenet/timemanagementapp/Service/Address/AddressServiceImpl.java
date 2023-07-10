package com.timemanagemenet.timemanagementapp.Service.Address;

import com.timemanagemenet.timemanagementapp.Entity.Address;
import com.timemanagemenet.timemanagementapp.Repository.AddressRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {
    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public List<Address> getAllAddresses() {
        return addressRepository.findAll();
    }

    @Override
    public Address getAddressById(long id) {
        Optional<Address> address = addressRepository.findById(id);
        return address.orElse(null);
    }

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(long id, Address updatedAddress) {
        Optional<Address> existingAddress = addressRepository.findById(id);
        if (existingAddress.isPresent()) {
            updatedAddress.setAddressId(id);
            return addressRepository.save(updatedAddress);
        }
        return null;
    }

    @Override
    public void deleteAddress(long id) {
        addressRepository.deleteById(id);
    }
}
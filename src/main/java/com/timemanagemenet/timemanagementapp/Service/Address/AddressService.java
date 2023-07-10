package com.timemanagemenet.timemanagementapp.Service.Address;
import com.timemanagemenet.timemanagementapp.Entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> getAllAddresses();
    Address getAddressById(long id);
    Address createAddress(Address address);
    Address updateAddress(long id, Address address);
    void deleteAddress(long id);
}

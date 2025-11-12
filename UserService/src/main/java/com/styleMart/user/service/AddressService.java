package com.styleMart.user.service;

import com.styleMart.user.model.Address;
import com.styleMart.user.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AddressService{

    @Autowired
    private AddressRepository addressRepository;

    public Address createAddress(Address address) {
        // Optionally: if isDefault=true, reset other addresses for the user
        if (address.getIsDefault()) {
            List<Address> userAddresses = addressRepository.findByUserId(address.getUserId());
            userAddresses.forEach(a -> a.setIsDefault(false));
            addressRepository.saveAll(userAddresses);
        }
        return addressRepository.save(address);
    }

    public Address updateAddress(Long id, Address address) {
        Optional<Address> existing = addressRepository.findById(id);
        if (existing.isPresent()) {
            Address addr = existing.get();
            addr.setStreet(address.getStreet());
            addr.setCity(address.getCity());
            addr.setState(address.getState());
            addr.setCountry(address.getCountry());
            addr.setPostalCode(address.getPostalCode());
            addr.setLandmark(address.getLandmark());
            addr.setIsDefault(address.getIsDefault());

            // Handle default flag
            if (address.getIsDefault()) {
                List<Address> userAddresses = addressRepository.findByUserId(addr.getUserId());
                userAddresses.forEach(a -> a.setIsDefault(false));
                addressRepository.saveAll(userAddresses);
                addr.setIsDefault(true);
            }

            return addressRepository.save(addr);
        }
        return null;
    }

    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }

    public Address getAddressById(Long id) {
        return addressRepository.findById(id).orElse(null);
    }

    public List<Address> getAddressesByUserId(Long userId) {
        return addressRepository.findByUserId(userId);
    }

    public Address getDefaultAddressByUserId(Long userId) {
        return addressRepository.findByUserIdAndIsDefaultTrue(userId);
    }

    public List<Address> selectAll() {
        return addressRepository.findAll();
    }
}

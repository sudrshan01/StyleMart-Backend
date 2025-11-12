package com.styleMart.user.controller;

import com.styleMart.user.model.Address;
import com.styleMart.user.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/addresses")
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping
    public Address create(@RequestBody Address address) {
        return addressService.createAddress(address);
    }
@GetMapping
public List<Address> getAll(){
        return addressService.selectAll();
}
    @GetMapping("/user/{userId}")
    public List<Address> getByUser(@PathVariable Long userId) {
        return addressService.getAddressesByUserId(userId);
    }

    @GetMapping("/{id}")
    public Address getById(@PathVariable Long id) {
        return addressService.getAddressById(id);
    }

    @PutMapping("/{id}")
    public Address update(@PathVariable Long id, @RequestBody Address address) {
        return addressService.updateAddress(id, address);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        addressService.deleteAddress(id);
    }
}

package uz.akbar.app_crud_address.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import uz.akbar.app_crud_address.entity.Address;
import uz.akbar.app_crud_address.service.AddressService;

/**
 * AddressController
 */
@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressService addressService;

    @GetMapping
    public HttpEntity<?> getAddresses(@RequestParam(defaultValue = "0") int page,
                                      @RequestParam(defaultValue = "10") int size) {

        List<Address> addresses = addressService.getAddresses(page, size);
        return ResponseEntity.ok(addresses);

    }

    @GetMapping("/{id}")
    public HttpEntity<?> getAddress(@PathVariable Integer id) {

        Address address = addressService.getAddress(id);
        return ResponseEntity.status(address != null ? 200 : 409).body(address);
    }

    @PostMapping
    public HttpEntity<?> addAddress(@RequestBody Address address) {
        Address savedAddress = addressService.addAddress(address);
        return ResponseEntity.status(201).body(savedAddress);
    }

    @PutMapping("/{id}")
    public HttpEntity<?> editAddress(@PathVariable Integer id, @RequestBody Address address) {

        Address editedAddress = addressService.editAddress(id, address);
        return ResponseEntity.status(editedAddress != null ? 202 : 409).body(editedAddress);
    }

    @DeleteMapping("/{id}")
    public HttpEntity<?> deleteAddress(@PathVariable Integer id) {

        boolean isDeleted = addressService.deleteAddress(id);
        if (isDeleted)
            return ResponseEntity.noContent().build();
        return ResponseEntity.notFound().build();
    }
}

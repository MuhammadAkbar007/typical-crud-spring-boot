package uz.akbar.app_crud_address.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import uz.akbar.app_crud_address.entity.Address;
import uz.akbar.app_crud_address.repository.AddressRepository;

/**
 * AddressService
 */
@Service
public class AddressService {

	@Autowired
	AddressRepository addressRepository;

	public List<Address> getAddresses(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		Page<Address> pageAddress = addressRepository.findAll(pageable);
		return pageAddress.getContent();
	}

	public Address getAddress(Integer id) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		return optionalAddress.orElse(null);
	}

	public Address addAddress(Address address) {
		return addressRepository.save(address);
	}

	public Address editAddress(Integer id, Address address) {
		Optional<Address> optionalAddress = addressRepository.findById(id);
		if (optionalAddress.isPresent()) {
			Address editingAddress = optionalAddress.get();
			editingAddress.setCity(address.getCity());
			editingAddress.setStreet(address.getStreet());
			return addressRepository.save(editingAddress);
		}
		return null;
	}

	public boolean deleteAddress(Integer id) {
		if (addressRepository.findById(id).isPresent()) {
			addressRepository.deleteById(id);
			return true;
		}
		return false;
	}

}

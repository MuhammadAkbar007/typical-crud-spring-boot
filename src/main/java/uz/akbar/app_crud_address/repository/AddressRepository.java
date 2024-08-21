package uz.akbar.app_crud_address.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import uz.akbar.app_crud_address.entity.Address;

/**
 * AddressRepository
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

}

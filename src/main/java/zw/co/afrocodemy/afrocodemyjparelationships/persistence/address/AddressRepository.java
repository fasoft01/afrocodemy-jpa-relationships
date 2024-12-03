package zw.co.afrocodemy.afrocodemyjparelationships.persistence.address;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address,Long> {
    Page<Address> findAllByEmployee_FirstNameContainingIgnoreCaseOrHouseNumberContainingIgnoreCaseOrStreetContainingIgnoreCaseOrZipCodeContainingIgnoreCase(
            String searchParam, String searchParam2, String searchParam3, String searchParam4, Pageable pageable);
}

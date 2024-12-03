package zw.co.afrocodemy.afrocodemyjparelationships.service.address;

import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.Address;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressResponse;

public interface AddressService {
    AddressResponse createAddress(@Valid AddressRequest addressRequest);

    AddressResponse getAddressById(Long id);

    AddressResponse updateAddress(Long id, @Valid AddressRequest addressRequest);

    void deleteAddress(Long id);

    Address findById(Long id);

    Page<AddressResponse> getAll(String searchParam, Pageable pageable);
}

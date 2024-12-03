package zw.co.afrocodemy.afrocodemyjparelationships.service.address.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.Address;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.persistence.address.AddressRepository;
import zw.co.afrocodemy.afrocodemyjparelationships.service.address.AddressService;
import zw.co.afrocodemy.afrocodemyjparelationships.service.employee.EmployeeService;
import zw.co.afrocodemy.afrocodemyjparelationships.service.exceptions.RecordNotFoundException;
import zw.co.afrocodemy.afrocodemyjparelationships.service.responsemappers.ResponseMapper;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final EmployeeService employeeService;

    @Override
    public AddressResponse createAddress(AddressRequest addressRequest) {
        log.info("Creating address for employee ID: {}", addressRequest.getEmployeeId());

        var employee = employeeService.findById(addressRequest.getEmployeeId());

        Address address = Address.builder()
                .houseNumber(addressRequest.getHouseNumber())
                .street(addressRequest.getStreet())
                .zipCode(addressRequest.getZipCode())
                .employee(employee)
                .build();

        addressRepository.save(address);

        log.info("Address created with ID: {}", address.getId());
        return ResponseMapper.mapToAddressResponse(address);
    }

    @Override
    public AddressResponse getAddressById(Long id) {
        log.info("Fetching address with ID: {}", id);

        Address address = findById(id);

        log.info("Address found with ID: {}", address.getId());
        return ResponseMapper.mapToAddressResponse(address);
    }

    @Override
    public AddressResponse updateAddress(Long id, AddressRequest addressRequest) {
        log.info("Updating address with ID: {}", id);

        Address address = findById(id);

       var employee = employeeService.findById(addressRequest.getEmployeeId());

        address.setHouseNumber(addressRequest.getHouseNumber());
        address.setStreet(addressRequest.getStreet());
        address.setZipCode(addressRequest.getZipCode());
        address.setEmployee(employee);

        addressRepository.save(address);

        log.info("Address updated with ID: {}", address.getId());
        return ResponseMapper.mapToAddressResponse(address);
    }

    @Override
    public void deleteAddress(Long id) {
        log.info("Deleting address with ID: {}", id);

        Address address = findById(id);
        addressRepository.delete(address);

        log.info("Address with ID: {} deleted successfully", id);
    }

    @Override
    public Address findById(Long id) {
        return addressRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("Address with ID {} not found", id);
                    return new RecordNotFoundException(
                            String.format("Address with ID %d not found", id)
                    );
                });
    }

    @Override
    public Page<AddressResponse> getAll(String searchParam, Pageable pageable) {
        log.info("Fetching all addresses with searchParam: {}", searchParam);

        if (searchParam != null && !searchParam.isBlank()) {
            return addressRepository.findAllByEmployee_FirstNameContainingIgnoreCaseOrHouseNumberContainingIgnoreCaseOrStreetContainingIgnoreCaseOrZipCodeContainingIgnoreCase(
                    searchParam, searchParam, searchParam, searchParam, pageable)
                    .map(ResponseMapper::mapToAddressResponse);
        } else {
            return addressRepository.findAll(pageable)
                    .map(ResponseMapper::mapToAddressResponse);
        }
    }
}

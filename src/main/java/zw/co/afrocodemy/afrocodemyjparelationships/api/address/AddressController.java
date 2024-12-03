package zw.co.afrocodemy.afrocodemyjparelationships.api.address;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressRequest;
import zw.co.afrocodemy.afrocodemyjparelationships.domain.address.dto.AddressResponse;
import zw.co.afrocodemy.afrocodemyjparelationships.service.address.AddressService;

@Slf4j
@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@SecurityRequirement(name = "authorization")
@Tag(name = "Address Controller", description = "APIs for managing addresses, including CRUD operations and pagination")
public class AddressController {

    private final AddressService addressService;

    @Operation(summary = "Create a new address", description = "Adds a new address to the database.")
    @PostMapping
    public ResponseEntity<AddressResponse> createAddress(
            @RequestBody @Valid AddressRequest addressRequest) {
        log.info("Creating address for employee ID: {}", addressRequest.getEmployeeId());
        return ResponseEntity.ok(addressService.createAddress(addressRequest));
    }

    @Operation(summary = "Get address by ID", description = "Fetches an address by its unique ID.")
    @GetMapping("/{id}")
    public ResponseEntity<AddressResponse> getAddressById(
            @PathVariable @Parameter(description = "Unique ID of the address") Long id) {
        log.info("Fetching address with ID: {}", id);
        return ResponseEntity.ok(addressService.getAddressById(id));
    }

    @Operation(summary = "Update an address", description = "Updates details of an existing address.")
    @PutMapping("/{id}")
    public ResponseEntity<AddressResponse> updateAddress(
            @PathVariable @Parameter(description = "Unique ID of the address") Long id,
            @RequestBody @Valid AddressRequest addressRequest) {
        log.info("Updating address with ID: {}", id);
        return ResponseEntity.ok(addressService.updateAddress(id, addressRequest));
    }

    @Operation(summary = "Delete an address", description = "Deletes an address from the database.")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(
            @PathVariable @Parameter(description = "Unique ID of the address") Long id) {
        log.info("Deleting address with ID: {}", id);
        addressService.deleteAddress(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "List all addresses", description = "Fetches a paginated list of all addresses.")
    @GetMapping
    public ResponseEntity<Page<AddressResponse>> getAll(
            @RequestParam(required = false) @Parameter(description = "Search parameter (optional)") String searchParam,
            @RequestParam(defaultValue = "0") @Parameter(description = "Page number") int page,
            @RequestParam(defaultValue = "10") @Parameter(description = "Page size") int size) {
        Pageable pageable = PageRequest.of(page, size, Sort.by("id").ascending());
        log.info("Fetching addresses with searchParam: {}", searchParam);
        return ResponseEntity.ok(addressService.getAll(searchParam, pageable));
    }
}

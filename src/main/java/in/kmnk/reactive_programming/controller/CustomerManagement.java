package in.kmnk.reactive_programming.controller;

import in.kmnk.reactive_programming.dto.CustomerResponseDto;
import in.kmnk.reactive_programming.dto.RegisterCustomer;
import in.kmnk.reactive_programming.entity.Customer;
import in.kmnk.reactive_programming.service.interfaces.ICustomerManagementService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/customer")
public class CustomerManagement {
    @Autowired
    ICustomerManagementService customerManagementService;

    @GetMapping("/get-all-customers")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetching all records from db",
            content = {@Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class))}),
            @ApiResponse(responseCode = "400", description = "No records found")
    })
    Flux<CustomerResponseDto> getAllUser(){
        return customerManagementService.getAllUsers();
    }

    @GetMapping("/get-customer-by-name/{name}")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found customers with given name",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class))),
            @ApiResponse(responseCode = "400", description = "Nothing Found",
            content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = CustomerResponseDto.class
            )))
    })
    Flux<CustomerResponseDto> getCustomersByName(@PathVariable String name){
        return customerManagementService.getAllUsersByName(name);
    }

    @DeleteMapping("/delete-customer-by-id/{id}")
    Mono<Void> deleteCustomerById(@PathVariable Long id){
        return customerManagementService.deleteCustomerById(id);
    }

    @GetMapping("/get-customer-by-id/{id}")
    Mono<CustomerResponseDto> getCustomerId(@PathVariable Long id){
        return customerManagementService.getCustomerById(id);
    }

    @PostMapping("/register")
    Mono<CustomerResponseDto> registerCustomer(@RequestBody RegisterCustomer customer){
        return customerManagementService.createCustomer(customer);
    }
}

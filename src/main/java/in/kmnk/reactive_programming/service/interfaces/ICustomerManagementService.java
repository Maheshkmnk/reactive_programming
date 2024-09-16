package in.kmnk.reactive_programming.service.interfaces;

import in.kmnk.reactive_programming.dto.CustomerResponseDto;
import in.kmnk.reactive_programming.dto.RegisterCustomer;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface ICustomerManagementService {
    Flux<CustomerResponseDto> getAllUsersByName(String name);

    Flux<CustomerResponseDto> getAllUsers();

    Mono<CustomerResponseDto> getCustomerById(Long id);

    Mono<Void> deleteCustomerById(Long id);

    Mono<CustomerResponseDto> createCustomer(RegisterCustomer customer);

}

package in.kmnk.reactive_programming.service.impl;

import in.kmnk.reactive_programming.dto.CustomerResponseDto;
import in.kmnk.reactive_programming.dto.RegisterCustomer;
import in.kmnk.reactive_programming.entity.Customer;
import in.kmnk.reactive_programming.repository.CustomerRepo;
import in.kmnk.reactive_programming.service.interfaces.ICustomerManagementService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class CustomerManagementServiceImpl implements ICustomerManagementService {

    @Autowired
    CustomerRepo customerRepo;

    @Override
    public Flux<CustomerResponseDto> getAllUsersByName(String name) {
        return customerRepo.findByName(name)
                .map(customer -> {
                    CustomerResponseDto custResp = new CustomerResponseDto();
                    BeanUtils.copyProperties(customer,custResp);
                    return custResp;
                });
    }

    @Override
    public Flux<CustomerResponseDto> getAllUsers() {
        return customerRepo.findAll()
                .map(customer -> {
                    CustomerResponseDto custResp = new CustomerResponseDto();
                    BeanUtils.copyProperties(customer,custResp);
                    return custResp;
                });
    }

    @Override
    public Mono<CustomerResponseDto> getCustomerById(Long id) {
        return customerRepo.findById(id)
                .map(customer -> {
                    CustomerResponseDto custResp = new CustomerResponseDto();
                    BeanUtils.copyProperties(customer,custResp);
                    return custResp;
                });
    }

    @Override
    public Mono<Void> deleteCustomerById(Long id) {
        return customerRepo.deleteById(id);
    }

    @Override
    public Mono<CustomerResponseDto> createCustomer(RegisterCustomer customer) {
        Customer custEntity = new Customer();
        BeanUtils.copyProperties(customer, custEntity);
        return customerRepo.save(custEntity)
                .map(save -> {
                    CustomerResponseDto custResp = new CustomerResponseDto();
                    BeanUtils.copyProperties(save, custResp);
                    return custResp;
                });
    }
}

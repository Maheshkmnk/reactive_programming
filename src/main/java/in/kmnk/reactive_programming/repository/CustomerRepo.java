package in.kmnk.reactive_programming.repository;

import in.kmnk.reactive_programming.entity.Customer;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface CustomerRepo extends ReactiveCrudRepository<Customer, Long> {
     Flux<Customer> findByName(String name);
}

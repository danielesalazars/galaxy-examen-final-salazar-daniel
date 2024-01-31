package cloud.csonic.labmaven.service;

import cloud.csonic.labmaven.model.Customer;
import cloud.csonic.labmaven.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

//TODO: PMD error
//import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService{


    private final CustomerRepository customerRepository;

    @Override
    public List<Customer> listAll() {
        return customerRepository.listAll();
    }

    @Override
    public Customer getById(long id) {
        return customerRepository.getById(id);
    }


}

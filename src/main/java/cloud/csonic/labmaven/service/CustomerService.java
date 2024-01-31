package cloud.csonic.labmaven.service;

import cloud.csonic.labmaven.model.Customer;

import java.util.List;

public interface CustomerService {

    List<Customer> listAll();
    Customer getById(long id);
}

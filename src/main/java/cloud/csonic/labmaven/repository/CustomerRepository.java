package cloud.csonic.labmaven.repository;

import cloud.csonic.labmaven.model.Customer;

import java.util.List;

public interface CustomerRepository {

    List<Customer> listAll();
    Customer getById(long id);

}

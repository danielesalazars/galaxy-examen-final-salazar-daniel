package cloud.csonic.labmaven.api;

import cloud.csonic.labmaven.api.dto.CustomerDto;
import cloud.csonic.labmaven.api.dto.CustomersDto;
import cloud.csonic.labmaven.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@AllArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping
    public CustomersDto listAll(){

        return CustomersDto.builder()
                .customers(customerService.listAll())
                .build();
    }

    @GetMapping("/{id}")
    public CustomerDto getById(@PathVariable("id")long id){

        return CustomerDto.builder()
                .customer(customerService.getById(id))
                .build();
    }




}

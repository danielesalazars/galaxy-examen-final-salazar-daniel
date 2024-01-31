package cloud.csonic.labmaven.api.dto;

import cloud.csonic.labmaven.model.Customer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto {
    
    private final Customer customer;
}

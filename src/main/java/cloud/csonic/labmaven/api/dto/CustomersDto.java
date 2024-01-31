package cloud.csonic.labmaven.api.dto;

import cloud.csonic.labmaven.model.Customer;
//import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Builder
@Getter
@Setter
//@SuppressFBWarnings(value = {"EI_EXPOSE_REP", "EI_EXPOSE_REP2"}, justification = "I prefer to suppress these FindBugs warnings")
public class CustomersDto {

    private final List<Customer> customers;
}

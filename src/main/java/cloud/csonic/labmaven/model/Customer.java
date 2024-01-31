package cloud.csonic.labmaven.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Customer {

    private long id;
    private String lastName;
    private String name;


}

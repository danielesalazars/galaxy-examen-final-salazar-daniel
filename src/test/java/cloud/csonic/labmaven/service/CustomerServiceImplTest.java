package cloud.csonic.labmaven.service;

import cloud.csonic.labmaven.model.Customer;
import cloud.csonic.labmaven.repository.CustomerRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = { CustomerServiceImplTest.TestConfiguration.class })
class CustomerServiceImplTest {

    public static class TestConfiguration{

        @Bean
        public CustomerService customerService(CustomerRepository customerRepository){
            CustomerService customerService = new CustomerServiceImpl(customerRepository);
            return customerService;
        }

    }

    @MockBean
    private CustomerRepository customerRepository;

    @Autowired
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
        reset(customerRepository);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void sanity() {
        assertThat(customerRepository).isNotNull();
        assertThat(customerService).isNotNull();
    }

    @Test
    void listAll() {

        var list = Arrays.asList(
                Customer.builder()
                        .id(1)
                        .name("name_1")
                        .lastName("lastName_1")
                .build(),
                Customer.builder()
                        .id(2)
                        .name("name_2")
                        .lastName("lastName_2")
                        .build()
        );

        when(customerRepository.listAll()).thenReturn(list);
        var data = customerService.listAll();

        assertThat(data).isNotNull();
        assertThat(data.size()).isEqualTo(2);
        assertThat(data.get(0).getId()).isEqualTo(1);
        assertThat(data.get(0).getName()).isEqualTo("name_1");
        assertThat(data.get(0).getLastName()).isEqualTo("lastName_1");

        verify(customerRepository,times(1)).listAll();
        verifyNoMoreInteractions(customerRepository);
    }

    @Test
    void getById() {

        var c1 =   Customer.builder()
                .id(1)
                .name("name_1")
                .lastName("lastName_1")
                .build();

        when(customerRepository.getById(1)).thenReturn(c1);

        var data = customerService.getById(1);
        assertThat(data).isNotNull();
        assertThat(data.getId()).isEqualTo(1);
        assertThat(data.getName()).isEqualTo("name_1");
        assertThat(data.getLastName()).isEqualTo("lastName_1");

        verify(customerRepository,times(1)).getById(1);
        verifyNoMoreInteractions(customerRepository);

    }
}
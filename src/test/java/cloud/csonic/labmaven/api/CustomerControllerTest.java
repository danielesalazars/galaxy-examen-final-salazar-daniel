package cloud.csonic.labmaven.api;

import cloud.csonic.labmaven.model.Customer;
import cloud.csonic.labmaven.service.CustomerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.hamcrest.Matchers.is;


@WebMvcTest(CustomerController.class)
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerService customerService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void listAll() throws Exception {

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

        when(customerService.listAll()).thenReturn(list);

        this.mockMvc.perform(get("/customers"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customers", hasSize(2)))
                .andExpect(jsonPath("$.customers[0].id", is(1)))
                .andExpect(jsonPath("$.customers[0].name", is("name_1")))
                .andExpect(jsonPath("$.customers[0].lastName", is("lastName_1")))
                .andExpect(jsonPath("$.customers[1].id", is(2)))
                .andExpect(jsonPath("$.customers[1].name", is("name_2")))
                .andExpect(jsonPath("$.customers[1].lastName", is("lastName_2")));

        verify(customerService, times(1)).listAll();
        verifyNoMoreInteractions(customerService);
    }

    @Test
    void getById() throws Exception {
        var c1 = Customer.builder()
                .id(767)
                .name("name_1")
                .lastName("lastName_1")
                .build();

        when(customerService.getById(767)).thenReturn(c1);

        this.mockMvc.perform(get("/customers/767"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customer.id", is(767)))
                .andExpect(jsonPath("$.customer.name", is("name_1")))
                .andExpect(jsonPath("$.customer.lastName", is("lastName_1")));

    }
}

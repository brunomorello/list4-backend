package pt.bmo.list4u.api.shoppinglist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.web.context.WebApplicationContext;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;

import java.util.List;
import java.util.Locale;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ProductRepository productRepository;

    private String BASE_URL = "/api/products";

    @BeforeEach
    void setup(WebApplicationContext webApplicationContext) {
        Product product = new Product("Product Test");
        productRepository.save(product);
    }

    @Test
    void should_find_product_by_id() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/1")).andDo(print()).andExpect(status().isOk());
    }

    @Test
    void when_id_inexistent_then_return_not_found() throws Exception {
        this.mockMvc.perform(get(BASE_URL + "/32312")).andExpect(status().isNotFound());
    }

    @Test
    void should_get_all_products() throws Exception {
        MvcResult mvcResult = this.mockMvc.perform(get(BASE_URL)).andDo(print()).andExpect(status().isOk()).andReturn();
        List<Product> product = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), List.class);

        assertFalse(product.isEmpty());
    }

    @Test
    void should_create_a_new_product() throws Exception {
        Product product = new Product("Beer");

        this.mockMvc.perform(post(BASE_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
        ).andExpect(status().isCreated());

        Optional<Product> productFound = productRepository.findByName(product.getName().toUpperCase(Locale.ROOT));
        assertTrue(productFound.isPresent());
        assertEquals(productFound.get().getName(), product.getName().toUpperCase(Locale.ROOT));
        assertNotEquals(1, productFound.get().getId());
    }

    @Test
    void should_update_a_product() throws Exception {
        Product product = new Product("Product Test");
        product.setId(1l);

        MvcResult mvcResult = this.mockMvc.perform(put(BASE_URL + "/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(product))
        ).andExpect(status().isOk()).andReturn();

        Product productUpdated = objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Product.class);

        assertEquals(product.getId(), productUpdated.getId());
        assertEquals(product.getName().toUpperCase(Locale.ROOT), productUpdated.getName());
    }

    @Test
    void when_update_a_product_with_inexistent_id_then_return_not_found() throws Exception {
        Product product = new Product("Product Test");

        this.mockMvc.perform(put(BASE_URL + "/1232131")
                .contentType(MediaType.APPLICATION_JSON)
                .content(this.objectMapper.writeValueAsString(product)))
                .andExpect(status().isNotFound());
    }

}
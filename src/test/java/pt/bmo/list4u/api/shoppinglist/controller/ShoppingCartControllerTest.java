package pt.bmo.list4u.api.shoppinglist.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.MultiValueMap;
import org.springframework.util.MultiValueMapAdapter;
import pt.bmo.list4u.api.shoppinglist.model.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ShoppingCartController.class)
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    private String BASE_URL = "/api/shopping-carts/";

//    @Test
    void should_find_shopping_cart_by_id() throws Exception {
        this.mockMvc.perform(get(BASE_URL + 1L)).andDo(print()).andExpect(status().isOk());
    }

//    @Test
    void should_retrieve_all_shopping_carts() throws Exception {
        this.mockMvc.perform(get(BASE_URL))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
    void should_retrieve_shopping_carts_based_on_query_params() throws Exception {
        Map<String, String> queryParams = new HashMap<String, String>();
        MultiValueMapAdapter queryParamsMap = new MultiValueMapAdapter(queryParams);
        queryParamsMap.add("param1", "value1");
        queryParamsMap.add("param2", "value2");

        this.mockMvc.perform(get(BASE_URL).queryParams(queryParamsMap))
                .andDo(print())
                .andExpect(status().isOk());
    }

//    @Test
    void should_create_shopping_cart() throws Exception {
        Product beer = new Product("Beer");
        Supermarket supermarket = new Supermarket(1L, "Test", Country.PORTUGAL);
        List<ItemCart> items = Arrays.asList(new ItemCart(beer, 4L, 1.5, false, "Pingodoce"));
        ShoppingCart shoppingCart = new ShoppingCart("test", items, false, supermarket, LocalDateTime.now());

        String shoppingCartPayload = new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .writeValueAsString(shoppingCart);

        this.mockMvc.perform(
                post(BASE_URL)
                        .content(shoppingCartPayload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isCreated());
    }

//    @Test
    void should_update_shopping_cart_name() throws Exception {
        Product beer = new Product("Beer");
        Supermarket supermarket = new Supermarket(1L, "Test", Country.BRAZIL);
        List<ItemCart> items = Arrays.asList(new ItemCart(beer, 4L, 1.5, false, "Pingodoce"));
        ShoppingCart shoppingCart = new ShoppingCart(1L, "test2", items, false, supermarket, LocalDateTime.now());

        String payload = new ObjectMapper().registerModule(new JavaTimeModule())
                .writeValueAsString(shoppingCart);

        this.mockMvc.perform(
                put(BASE_URL + shoppingCart.getId())
                        .content(payload)
                        .contentType(MediaType.APPLICATION_JSON)
        ).andDo(print()).andExpect(status().isNoContent());
    }
}
package pt.bmo.list4u.api.shoppinglist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
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
import org.springframework.util.MultiValueMapAdapter;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.ItemCart;
import pt.bmo.list4u.api.shoppinglist.model.Product;
import pt.bmo.list4u.api.shoppinglist.model.ShoppingCart;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.ItemCartRepository;
import pt.bmo.list4u.api.shoppinglist.repository.ProductRepository;
import pt.bmo.list4u.api.shoppinglist.repository.ShoppingCartRepository;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_DOUBLE;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_LONG;
import static pt.bmo.list4u.api.shoppinglist.utils.FakeValues.FAKE_STR;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class ShoppingCartControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private SupermarketRepository supermarketRepository;

    @Autowired
    private ItemCartRepository itemCartRepository;

    private static final String BASE_URL = "/api/shopping-carts/";

    private ShoppingCart shoppingCart;

    @BeforeEach
    void setup() {
        this.shoppingCart = createShoppingCart();
    }

    @Test
    void should_find_shopping_cart_by_id() throws Exception {
//        MvcResult mvcResult = this.mockMvc.perform(get(BASE_URL + this.shoppingCart.id())).andExpect(status().isOk()).andReturn();
//        ShoppingCart shoppingCartFound = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ShoppingCart.class);
//
//        assertEquals(FAKE_STR, shoppingCartFound.name());
//        assertFalse(shoppingCartFound.items().isEmpty());
    }

    @Test
    void should_retrieve_all_shopping_carts() throws Exception {
//        this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    void should_retrieve_finished_shopping_carts_using_query_params() throws Exception {
//        Map<String, String> queryParams = new HashMap<>();
//        MultiValueMapAdapter queryParamsMap = new MultiValueMapAdapter(queryParams);
//        queryParamsMap.add("finished", "true");
//
//        this.mockMvc.perform(get(BASE_URL).queryParams(queryParamsMap))
//                .andDo(print())
//                .andExpect(status().isOk());
    }

    @Test
    void should_retrieve_shopping_carts_by_period_using_query_params() throws Exception {
//        MultiValueMapAdapter queryParamsMap = new MultiValueMapAdapter(new HashMap<String, String >());
//        queryParamsMap.add("byPeriod", "2023-07-20,2023-07-29");
    }

    @Test
    void should_create_shopping_cart() throws Exception {
//        String shoppingCartPayload = new ObjectMapper()
//                .registerModule(new JavaTimeModule())
//                .writeValueAsString(shoppingCart);
//
//        this.mockMvc.perform(
//                post(BASE_URL)
//                        .content(shoppingCartPayload)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andDo(print()).andExpect(status().isCreated());
    }

    @Test
    void should_update_shopping_cart_name() throws Exception {
//        String payload = new ObjectMapper().registerModule(new JavaTimeModule())
//                .writeValueAsString(shoppingCart);
//
//        this.mockMvc.perform(
//                put(BASE_URL + shoppingCart.id())
//                        .content(payload)
//                        .contentType(MediaType.APPLICATION_JSON)
//        ).andExpect(status().isOk());
    }

    @Test
    void should_remove_a_item_from_cart() throws Exception {
//        ShoppingCart shoppingCart = createShoppingCart();
////        shoppingCart.items(Collections.emptyList());
//
//        MvcResult mvcResult = this.mockMvc.perform(put(BASE_URL + shoppingCart.id())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(this.objectMapper.writeValueAsString(shoppingCart)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        ShoppingCart shoppingCartUpdated = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), ShoppingCart.class);
//
//        assertTrue(shoppingCartUpdated.items().isEmpty());
    }

    @Test
    void when_id_is_inexistent_then_return_not_found() throws Exception {
//        this.mockMvc.perform(put(BASE_URL + 3123131)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(shoppingCart)))
//                .andExpect(status().isNotFound());
    }

    @Test
    void should_delete_a_shopping_cart() throws Exception {
//        this.mockMvc.perform(delete(BASE_URL + shoppingCart.id()))
//                .andExpect(status().isOk());
    }

    private ShoppingCart createShoppingCart() {
        Product beer = new Product(FAKE_LONG, FAKE_STR);

        Supermarket supermarket = new Supermarket(FAKE_LONG, FAKE_STR, Country.PORTUGAL);

        ItemCart itemCart = new ItemCart(FAKE_LONG, beer, FAKE_LONG, FAKE_DOUBLE, false);
        List<ItemCart> items = Arrays.asList(itemCart);

        ShoppingCart shoppingCart = new ShoppingCart(FAKE_LONG, FAKE_STR, items, false, supermarket, LocalDateTime.now());
//        shoppingCartRepository.save(shoppingCart);

        return shoppingCart;
    }
}
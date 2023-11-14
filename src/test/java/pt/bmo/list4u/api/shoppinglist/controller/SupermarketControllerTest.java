package pt.bmo.list4u.api.shoppinglist.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import pt.bmo.list4u.api.shoppinglist.entity.SupermarketEntity;
import pt.bmo.list4u.api.shoppinglist.model.Country;
import pt.bmo.list4u.api.shoppinglist.model.Supermarket;
import pt.bmo.list4u.api.shoppinglist.repository.SupermarketRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
class SupermarketControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private SupermarketRepository repository;

    private static final String BASE_URL = "/api/supermarkets";

    @Test
    void should_find_supermarket_by_id() throws Exception {
//        var supermarket = SupermarketEntity.builder()
//                .name("Extra")
//                .country(Country.BRAZIL)
//        .build();
//
//        var supermarketCreated = repository.save(supermarket).block();
//
//        MvcResult mvcResult = this.mockMvc.perform(get(BASE_URL + "/" + supermarketCreated.getId())).andExpect(status().isOk()).andReturn();
//
//        Supermarket supermarketFound = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Supermarket.class);
//
//        assertEquals(supermarket.getName(), supermarketFound.name());
//        assertEquals(supermarket.getCountry(), supermarketFound.country());
    }

    @Test
    void when_inexistent_id_then_return_not_found() throws Exception {
//        this.mockMvc.perform(get(BASE_URL + "/12312")).andExpect(status().isNotFound());
    }

    @Test
    void should_get_all_supermarkets() throws Exception {
//        this.mockMvc.perform(get(BASE_URL)).andExpect(status().isOk());
    }

    @Test
    void should_create_a_supermarket() throws Exception {
//        Supermarket supermarket = Supermarket.builder()
//                .name("Carrefour")
//                .country(Country.BRAZIL)
//        .build();
//
//        MvcResult mvcResult = this.mockMvc.perform(post(BASE_URL)
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(this.objectMapper.writeValueAsString(supermarket)))
//                .andExpect(status().isCreated())
//                .andReturn();
//
//        Supermarket supermarketCreated = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Supermarket.class);
//
//        assertNotEquals(0, supermarketCreated.id());
//        assertEquals(supermarket.name(), supermarketCreated.name());
//        assertEquals(supermarket.country(), supermarketCreated.country());
    }

    @Test
    void should_update_a_supermarket() throws Exception {
//        var supermarket = SupermarketEntity.builder()
//                .name("Dia")
//                .country(Country.BRAZIL)
//        .build();
//
//        SupermarketEntity supermarketCreated = this.repository.save(supermarket).block();
//
//        supermarket.setName("Pingo Doce");
//        supermarket.setCountry(Country.PORTUGAL);
//
//        MvcResult mvcResult = this.mockMvc.perform(put(BASE_URL + "/" + supermarketCreated.getId())
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(this.objectMapper.writeValueAsString(supermarket)))
//                .andExpect(status().isOk())
//                .andReturn();
//
//        Supermarket supermarketUpdated = this.objectMapper.readValue(mvcResult.getResponse().getContentAsString(), Supermarket.class);
//
//        assertEquals(supermarket.getName(), supermarketUpdated.name());
//        assertEquals(supermarket.getCountry(), supermarketUpdated.country());
    }

    @Test
    void when_updated_with_inexistent_id_then_return_not_found() throws Exception {
//        var supermarket = Supermarket.builder()
//                .name("Dia")
//                .country(Country.BRAZIL)
//        .build();
//
//        this.mockMvc.perform(put(BASE_URL + "/12312")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(this.objectMapper.writeValueAsString(supermarket)))
//                .andExpect(status().isNotFound());
    }

    @Test
    void should_delete_a_supermarket() throws Exception {
//        var supermarket = SupermarketEntity.builder()
//                .name("Pão de Açúcar")
//                .country(Country.BRAZIL)
//        .build();
//
//        var supermarketCreated = this.repository.save(supermarket).block();
//
//        this.mockMvc.perform(delete(BASE_URL + "/" + supermarketCreated.getId()))
//                .andExpect(status().isOk());
//
//        SupermarketEntity supermarketEntityFound = this.repository.findById(supermarketCreated.getId()).block();
//
//        assertNotNull(supermarketEntityFound);
    }

    @Test
    void when_delete_with_inexistent_id_then_return_not_found() throws Exception {
//        var supermarket = Supermarket.builder()
//                .name("Dia")
//                .country(Country.BRAZIL)
//        .build();
//
//        this.mockMvc.perform(delete(BASE_URL + "/12312")
//                        .contentType(MediaType.APPLICATION_JSON)
//                        .content(this.objectMapper.writeValueAsString(supermarket)))
//                .andExpect(status().isNotFound());
    }
}
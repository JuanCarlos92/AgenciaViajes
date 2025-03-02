package org.juancarlos.hoteles;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@AutoConfigureMockMvc
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class ApplicationTests {

    @Autowired
    MockMvc mockMvc;

    @Order(0)
    void testListarHoteles() throws Exception {
        mockMvc.perform(get("/hoteles")).andDo(print());
    }

    @Order(1)
    void testHotelID() throws Exception {
        mockMvc.perform(get("/hoteles/1")).andDo(print());
    }

}

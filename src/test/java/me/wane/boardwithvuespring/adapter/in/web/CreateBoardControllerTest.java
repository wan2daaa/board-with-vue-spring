package me.wane.boardwithvuespring.adapter.in.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import me.wane.boardwithvuespring.adapter.in.web.dto.CreateBoardRequest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

//@WebMvcTest(CreateBoardController.class)
@SpringBootTest
@AutoConfigureMockMvc
class CreateBoardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createBoard() throws Exception {
        //given
        CreateBoardRequest request = new CreateBoardRequest("타이틀", "content");

        //when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/boards")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(MockMvcResultMatchers.status().isCreated());
    }
}
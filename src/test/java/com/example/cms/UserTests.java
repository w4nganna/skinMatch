package com.example.cms;

import com.example.cms.model.entity.User;
import com.example.cms.model.repository.UserRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class UserTests {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private UserRepository userRepository;

    @Test
    void addUser() throws Exception {
        ObjectNode userJson = objectMapper.createObjectNode();
        userJson.put("userId", "00003");
        userJson.put("email", "test.user@mail.com");
        userJson.put("password", "test1234");

        MockHttpServletResponse response = mockMvc.perform(
                        post("/users").
                                contentType("application/json").
                                content(userJson.toString()))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(userRepository.findById("00003").isPresent());
        User addedUser = userRepository.findById("00003").get();

        assertEquals("00003", addedUser.getUserId());
        assertEquals("test.user@mail.com", addedUser.getEmail());
    }

    @Test
    void deleteUser() throws Exception {
        User user = new User("00004", "delete.me@mail.com", "delete123");
        userRepository.save(user);

        MockHttpServletResponse response = mockMvc.perform(
                        delete("/users/00004").
                                contentType("application/json"))
                .andReturn().getResponse();

        assertEquals(200, response.getStatus());
        assertTrue(userRepository.findById("00004").isEmpty());
    }
}


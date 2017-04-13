package com.web;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.Assert.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@SqlGroup(value = {
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:user.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:userAfterTest.sql")
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//@WebMvcTest(UserController.class)
public class UserControllerTest {
	@Autowired
	private UserRepository userRepository;
	
    @Autowired
    private WebApplicationContext ctx;

    @Before
    public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    private MockMvc mockMvc;

    @Test
    public void getAllUsers() throws Exception {
        mockMvc
                .perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON_UTF8)).andExpect(jsonPath("$", hasSize(1)));
    }
    
    
    @Test
    public void deleteUser() throws Exception {
        mockMvc
                .perform(delete("/user/1"))
                .andExpect(status().isOk());
        assertEquals(userRepository.findAll().size(),0);


    }
    @Test
    public void addUser() throws Exception {
    	User new_one = new User("some_username","some_email");
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(new_one);

        mockMvc
                .perform(post("/user")
                .contentType(MediaType.APPLICATION_JSON)		
                .content(json))
                .andExpect(status().isOk());
        
        assertEquals(userRepository.findAll().size(),2);

    }
    
}

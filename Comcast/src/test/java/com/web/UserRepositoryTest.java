package com.web;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.jdbc.SqlGroup;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.transaction.Transactional;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.springframework.test.context.jdbc.SqlConfig.TransactionMode.ISOLATED;


@RunWith(SpringRunner.class)
@SpringBootTest
@SqlGroup(value = {
        @Sql(executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD, scripts = "classpath:user.sql"),
        @Sql(executionPhase = Sql.ExecutionPhase.AFTER_TEST_METHOD, scripts = "classpath:userAfterTest.sql")
})
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void createUser(){
        User user = new User("ram","ram@g.com");
        userRepository.save(user);
        assertEquals(userRepository.findOne(2L).getUsername(),user.getUsername());
    }

    @Test
    public void deleteUser(){
        userRepository.delete(1L);
        assertEquals(userRepository.findAll().size(),0);
    }

    @Test
    public void findAllUser(){
        assertEquals(userRepository.findAll().size(),1);
    }





}

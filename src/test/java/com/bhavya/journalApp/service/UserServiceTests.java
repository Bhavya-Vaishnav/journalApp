package com.bhavya.journalApp.service;

import com.bhavya.journalApp.entity.User;
import com.bhavya.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByUserName() {
        User user=userRepository.findByUserName("bhavya");
        assertFalse(user.getJournalEntries().isEmpty());
    }

    @ParameterizedTest
    @CsvSource({
            "1,2,3"
    })
    public void test(int a,int b,int expect){
        assertEquals(expect,a+b);
    }
}

package net.engineeringdigest.journalApp.service;

import net.engineeringdigest.journalApp.entity.User;
import net.engineeringdigest.journalApp.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;

import static org.mockito.Mockito.*;

@ActiveProfiles("dev")
@ExtendWith(MockitoExtension.class)
@Disabled
public class UserDetailsServiceImplTests {


    @InjectMocks
    private UserDetailsServiceImpl userDetailsService;

    @Mock
    private UserRepository userRepository;


//    @BeforeEach
//    void setUp() {
//        MockitoAnnotations.initMocks(this);
//    }

    @Test
    void loadUserByUsernameTest() {
        when(userRepository.findByUsername(ArgumentMatchers.anyString())).thenReturn(User.builder().username("Eric").password("654321").roles(new ArrayList<>()).build());
        UserDetails user = userDetailsService.loadUserByUsername("Eric");
        Assertions.assertNotNull(user);
    }
}

package es.hibernate.springbootdb.service;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import es.hibernate.springbootdb.entity.User;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.mockito.Mockito.*;

class JwtUserDetailsServiceTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private JwtUserDetailsService jwtUserDetailsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void loadUserByUsername_UserExists() {
        String usernameOrEmail = "testUser";
        User user = new User();
        user.setUserUsername(usernameOrEmail);
        user.setUserPassword("password");

        when(userService.getUserByUsernameOrEmail(usernameOrEmail)).thenReturn(user);

        UserDetails userDetails = jwtUserDetailsService.loadUserByUsername(usernameOrEmail);

        assertNotNull(userDetails, "UserDetails should not be null");
        assertEquals(usernameOrEmail, userDetails.getUsername(), "Username should match");
        assertEquals("password", userDetails.getPassword(), "Password should match");

        verify(userService, times(1)).getUserByUsernameOrEmail(usernameOrEmail);
    }

    @Test
    void loadUserByUsername_UserDoesNotExist() {
        String usernameOrEmail = "nonExistentUser";

        when(userService.getUserByUsernameOrEmail(usernameOrEmail)).thenReturn(null);

        UsernameNotFoundException exception = assertThrows(UsernameNotFoundException.class, () -> {
            jwtUserDetailsService.loadUserByUsername(usernameOrEmail);
        });

        assertEquals("User not found with username or email: " + usernameOrEmail, exception.getMessage());

        verify(userService, times(1)).getUserByUsernameOrEmail(usernameOrEmail);
    }
}
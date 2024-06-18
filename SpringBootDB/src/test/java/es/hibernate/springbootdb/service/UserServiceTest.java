package es.hibernate.springbootdb.service;

import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.UserRepository;
import es.hibernate.springbootdb.security.SecurityUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Mock
    private BCryptPasswordEncoder passwordEncoder;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createUser() {
        User user = new User();
        user.setUserUsername("testUser");
        user.setUserEmail("testUser@example.com");
        user.setUserPassword("password");

        when(userRepository.save(any(User.class))).thenReturn(user);

        User createdUser = userService.createUser(user);

        assertNotNull(createdUser);
        assertEquals("testUser", createdUser.getUserUsername());
        assertEquals("testUser@example.com", createdUser.getUserEmail());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void getAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());
        users.add(new User());

        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.getAllUsers();

        assertEquals(2, result.size());
        verify(userRepository, times(1)).findAll();
    }

    @Test
    void getUserById() {
        User user = new User();
        user.setUserId(1L);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        User result = userService.getUserById(1L);

        assertNotNull(result);
        assertEquals(1L, result.getUserId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void getUserByUsernameOrEmail() {
        User user = new User();
        user.setUserUsername("testUser");
        user.setUserEmail("testUser@example.com");

        when(userRepository.findByUserUsername("testUser")).thenReturn(user);
        when(userRepository.findByUserEmail("testUser@example.com")).thenReturn(user);

        User resultByUsername = userService.getUserByUsernameOrEmail("testUser");
        User resultByEmail = userService.getUserByUsernameOrEmail("testUser@example.com");

        assertNotNull(resultByUsername);
        assertNotNull(resultByEmail);
        assertEquals("testUser", resultByUsername.getUserUsername());
        assertEquals("testUser@example.com", resultByEmail.getUserEmail());

        verify(userRepository, times(1)).findByUserUsername("testUser");
        verify(userRepository, times(1)).findByUserEmail("testUser@example.com");
    }

    @Test
    void getSaltByUsernameOrEmail() {
        User user = new User();
        user.setUserSalt("testSalt");

        when(userRepository.findByUserUsername("testUser")).thenReturn(user);

        String salt = userService.getSaltByUsernameOrEmail("testUser");

        assertEquals("testSalt", salt);
        verify(userRepository, times(1)).findByUserUsername("testUser");
    }

    @Test
    void updateUser() {
        User user = new User();
        user.setUserId(1L);

        User updatedUser = new User();
        updatedUser.setUserUsername("updatedUser");
        updatedUser.setUserEmail("updatedUser@example.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updatedUser);

        User result = userService.updateUser(1L, updatedUser);

        assertNotNull(result);
        assertEquals("updatedUser", result.getUserUsername());
        assertEquals("updatedUser@example.com", result.getUserEmail());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void deleteUser() {
        doNothing().when(userRepository).deleteById(1L);

        userService.deleteUser(1L);

        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void renewPassword() {
        User user = new User();
        user.setUserId(1L);
        String oldSalt = SecurityUtils.generateSalt();
        String oldPassword = "oldPassword";
        String encodedOldPassword = SecurityUtils.encodePassword(oldPassword, oldSalt);

        user.setUserPassword(encodedOldPassword);
        user.setUserSalt(oldSalt);

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(passwordEncoder.matches(anyString(), anyString())).thenReturn(true);
        when(passwordEncoder.encode(anyString())).thenAnswer(invocation -> {
            String rawPassword = invocation.getArgument(0);
            return new BCryptPasswordEncoder().encode(rawPassword);
        });

        boolean result = userService.renewPassword(1L, "oldPassword", "newPassword");

        assertTrue(result);
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void validatePassword() {
        User user = new User();
        String salt = "salt";
        String rawPassword = "password";
        String saltedPassword = rawPassword + salt;

        user.setUserSalt(salt);
        user.setUserPassword(new BCryptPasswordEncoder().encode(saltedPassword));

        when(passwordEncoder.matches(anyString(), anyString())).thenAnswer(invocation -> {
            String rawPasswordArg = invocation.getArgument(0);
            String encodedPasswordArg = invocation.getArgument(1);
            return new BCryptPasswordEncoder().matches(rawPasswordArg, encodedPasswordArg);
        });

        boolean result = userService.validatePassword(rawPassword, user);

        assertTrue(result);
    }

    @Test
    void getPasswordEncoder() {
        BCryptPasswordEncoder encoder = userService.getPasswordEncoder();
        assertNotNull(encoder);
    }
}
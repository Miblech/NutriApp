package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.UserRepository;
import es.hibernate.springbootdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @GetMapping("/get/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @PutMapping("/update/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updatedUser) {
        return userService.updateUser(id, updatedUser);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    @PutMapping("/renew-password/{userId}")
    public boolean renewPassword(@PathVariable Long userId,
                                 @RequestParam String oldPassword,
                                 @RequestParam String newPassword) {
        return userService.renewPassword(userId, oldPassword, newPassword);
    }
}

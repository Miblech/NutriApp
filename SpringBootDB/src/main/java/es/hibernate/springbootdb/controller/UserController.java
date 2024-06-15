package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.PasswordChangeRequest;
import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.UserRepository;
import es.hibernate.springbootdb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
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

    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserByUsernameOrEmail(userDetails.getUsername());
    }

    @PutMapping("/me/update")
    public User updateCurrentUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User updatedUser) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        return userService.updateUser(currentUser.getUserId(), updatedUser);
    }

    @DeleteMapping("/me/delete")
    public void deleteCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        userService.deleteUser(currentUser.getUserId());
    }

    @PutMapping("/me/renew-password")
    public boolean renewPassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PasswordChangeRequest request) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        return userService.renewPassword(currentUser.getUserId(), request.getOldPassword(), request.getNewPassword());
    }
}

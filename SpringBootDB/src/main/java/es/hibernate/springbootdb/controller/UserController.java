package es.hibernate.springbootdb.controller;

import es.hibernate.springbootdb.entity.PasswordChangeRequest;
import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.UserRepository;
import es.hibernate.springbootdb.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/users")
@Tag(name = "User API", description = "API for managing users")
@SecurityRequirement(name = "bearerAuth")
public class UserController {

    @Autowired
    private UserService userService;

    @Operation(summary = "Get all users in Database", description = "Retrieve a list of all users")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved users"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @Operation(summary = "Register a new user", description = "Register a new user with a username, email, and password",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "User registration details",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class),
                            examples = @ExampleObject(value = "{ \"userUsername\": \"Pedro\", \"userEmail\": \"pedro2727@gmail.com\", \"userPassword\": \"12345\" }"))
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "User registered successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/register")
    public User createUser(@RequestBody User user) {
        return userService.createUser(user);
    }

    @Operation(summary = "Get current user", description = "Retrieve details of the currently authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Successfully retrieved user"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/me")
    public User getCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getUserByUsernameOrEmail(userDetails.getUsername());
    }

    @Operation(summary = "Update current user", description = "Update details of the currently authenticated user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Updated user details",
                    required = true,
                    content = @Content(schema = @Schema(implementation = User.class))
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User updated successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid user details"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/me/update")
    public User updateCurrentUser(@AuthenticationPrincipal UserDetails userDetails, @RequestBody User updatedUser) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        return userService.updateUser(currentUser.getUserId(), updatedUser);
    }

    @Operation(summary = "Delete current user", description = "Delete the currently authenticated user")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "User deleted successfully"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @DeleteMapping("/me/delete")
    public void deleteCurrentUser(@AuthenticationPrincipal UserDetails userDetails) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        userService.deleteUser(currentUser.getUserId());
    }

    @Operation(summary = "Change user password", description = "Change the password of the currently authenticated user",
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Password change request containing old and new passwords",
                    required = true,
                    content = @Content(schema = @Schema(implementation = PasswordChangeRequest.class),
                            examples = @ExampleObject(value = "{ \"oldPassword\": \"oldPass123\", \"newPassword\": \"newPass123\" }"))
            ))
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Password changed successfully"),
            @ApiResponse(responseCode = "400", description = "Invalid password details"),
            @ApiResponse(responseCode = "401", description = "Unauthorized"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PutMapping("/me/renew-password")
    public boolean renewPassword(@AuthenticationPrincipal UserDetails userDetails, @RequestBody PasswordChangeRequest request) {
        User currentUser = userService.getUserByUsernameOrEmail(userDetails.getUsername());
        return userService.renewPassword(currentUser.getUserId(), request.getOldPassword(), request.getNewPassword());
    }
}

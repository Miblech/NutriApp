package es.hibernate.springbootdb.service;

import es.hibernate.springbootdb.entity.User;
import es.hibernate.springbootdb.repository.UserRepository;
import es.hibernate.springbootdb.security.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {



    @Autowired
    private UserRepository userRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public User createUser(User user) {

        String salt = SecurityUtils.generateSalt();
        String encodedPassword = SecurityUtils.encodePassword(user.getUserPassword(), salt);

        user.setUserSalt(salt);
        user.setUserPassword(encodedPassword);

        return userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    public User updateUser(Long id, User updatedUser) {
        return userRepository.findById(id).map(user -> {
            user.setUserUsername(updatedUser.getUserUsername());
            user.setUserEmail(updatedUser.getUserEmail());
            user.setUserDob(updatedUser.getUserDob());
            user.setUserGender(updatedUser.getUserGender());
            user.setUserHeight(updatedUser.getUserHeight());
            user.setUserWeight(updatedUser.getUserWeight());
            return userRepository.save(user);
        }).orElseGet(() -> {
            updatedUser.setUserId(id);
            return userRepository.save(updatedUser);
        });
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }


    public boolean renewPassword(Long userId, String oldPassword, String newPassword) {
        User user = userRepository.findById(userId).orElse(null);
        if (user == null) {
            return false;
        }

        String saltedOldPassword = oldPassword + user.getUserSalt();
        if (passwordEncoder.matches(saltedOldPassword, user.getUserPassword())) {
            String newSalt = SecurityUtils.generateSalt();
            String encodedNewPassword = SecurityUtils.encodePassword(newPassword, newSalt);
            user.setUserSalt(newSalt);
            user.setUserPassword(encodedNewPassword);
            userRepository.save(user);
            return true;
        } else {
            return false;
        }
    }
}

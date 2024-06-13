package es.hibernate.springbootdb.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @Column(nullable = false, unique = true)
    private String userUsername;

    @Column(nullable = false, unique = true)
    private String userEmail;

    private String userPassword;

    private String userSalt;

    private LocalDate userDob;

    private Integer userGender;

    private Float userHeight;

    private Float userWeight;

    @Column(columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime userLastSeen;

    public User(Long userId, String userUsername, String userEmail, String userPassword, String userSalt, LocalDate userDob, Integer userGender, Float userHeight, Float userWeight, LocalDateTime userLastSeen) {
        this.userId = userId;
        this.userUsername = userUsername;
        this.userEmail = userEmail;
        this.userPassword = userPassword;
        this.userSalt = userSalt;
        this.userDob = userDob;
        this.userGender = userGender;
        this.userHeight = userHeight;
        this.userWeight = userWeight;
        this.userLastSeen = userLastSeen;
    }
}
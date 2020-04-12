package com.niko.restbooks.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.niko.restbooks.security.models.Role;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@ToString(exclude = "password")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userNumber")
    private Integer userNumber;

    @Column(name = "login")
    @NotNull(message = "Login cannot be empty")
    @Size(min = 1, max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "Login can contain only letters, numbers and underscores")
    private String login;

    @Column(name = "password")
    @NotNull(message = "Password cannot be empty")
    // Max size is 100, because when it was 20 I always got validation errors no metter what the password was
    @Size(min = 8, max = 100)
    private String password;

    @Column(name = "role")
    @NotNull
    private Role activeRole;

    @ManyToMany
    @JoinTable(
            name = "favourite_books",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id")
    )
    private List<BookEntity> favouriteBooks;
}

package by.smirnov.springsecurityapp.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 2, max = 30, message = "имя должно быть от 2 до 100 символов длиной")
    @Column(name = "username")
    private String username;

    @Min(value = 1990, message = "Год рождения должен быть больше 1900")
    @Column(name = "year_of_birth")
    private int yearOfBirth;

    @Column(name = "email")
    @NotEmpty(message = "E-mail не должен быть пустым")
    @Email
    private String email;

    @Column(name = "password")
    private String password;

    public Person(String username, int yearOfBirth, String email) {
        this.username = username;
        this.yearOfBirth = yearOfBirth;
        this.email = email;
    }
}
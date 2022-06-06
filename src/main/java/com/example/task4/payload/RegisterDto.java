package com.example.task4.payload;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class RegisterDto {

    @NotBlank
    @Email(message = "invalid email format")
    String email;

    @NotBlank(message = "password must not be blank")
    @Min(value = 1, message = "password length must be at least 1")
    String password;

    @NotBlank(message = "first name must not be blank")
    String firstName;

    String lastName;
}

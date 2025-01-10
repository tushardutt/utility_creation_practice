package com.userManagement.user_registration.model;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;
@Data
@AllArgsConstructor
@NoArgsConstructor

@RedisHash("User")
public class User implements Serializable{
    private String id;
    @NotEmpty(message = "Name cannot be empty")
    @Size(min = 3, message = "Name must be at least 3 characters long")
    private String name;
    @NotEmpty(message = "Email cannot be empty")
    @Email(message = "Email should be valid")
    private String email;
    @NotEmpty(message = "Password cannot be empty")
    @Size(min = 8, max = 20, message = "Password must be between 8 and 20 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
}

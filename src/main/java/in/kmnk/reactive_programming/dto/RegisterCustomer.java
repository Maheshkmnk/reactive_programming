package in.kmnk.reactive_programming.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.UniqueElements;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterCustomer {
    @NotNull
    @Size(min = 3, max = 60, message = "length must be in between 3 and 60")
    private String name;

    @NotNull
    @Size(min = 18, max = 100, message = "age should be 18 to 100")
    private Integer age;

    @NotNull
    @Email(message = "Not a valid email")
    private String email;

    @Size(min = 6, max = 30, message = "password length should be 6 to 30")
    @Pattern(regexp = "(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[^a-zA-Z\\d]).{8,30}",
            message = "Password must contain at least one lowercase letter, one uppercase letter, one digit, one special character, and be between 6 and 30 characters long")
    private String password;
}

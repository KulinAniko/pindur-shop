package hu.pindur.backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.GroupSequence;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@GroupSequence({AppUserChangePasswordCommand.class, FirstOrder.class, SecondOrder.class})
//@FieldMatch(firstValue = "password", secondValue = "confirmPassword", message = "The password fields must match!",
//        groups = FirstOrder.class)
public class AppUserChangePasswordCommand {
//    @NotNull(message = "Password is required!", groups = FirstOrder.class)
    private String oldPassword;

//    @PasswordValidation(groups = {SecondOrder.class})
//    @NotNull(message = "Password is required!", groups = FirstOrder.class)
    private String password;

    private String confirmPassword;
}

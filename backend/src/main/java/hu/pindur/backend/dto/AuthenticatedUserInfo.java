package hu.pindur.backend.dto;


import hu.pindur.backend.domain.UserRole;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticatedUserInfo {
    private String email;
    private List<UserRole> roles;
}

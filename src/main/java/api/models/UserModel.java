package api.models;


import lombok.*;

import java.util.List;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor

public class UserModel {

    private String id;

    private String firstName;

    private String lastName;

    private String password;

    private String email;

    private String username;

    private List<VacationPackageModel> packages;

}

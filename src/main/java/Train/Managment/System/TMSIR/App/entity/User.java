package Train.Managment.System.TMSIR.App.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name = "users")
@Data
public class User
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    @NotBlank(message = "Username cannot be blank")
    private String username;
    @Column(nullable = false,unique = true)
    @NotBlank(message = "Password cannot be blank")
    private String password;
    private String role;
    private String fullName;
    private String email;
    @Column(nullable = false, unique = true)
    @NotBlank(message = "Mobile is require")
    private String mobileNumber;


}

package kw.hk.mm.mr.slightlicloudi.user;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Email;

@Entity
@Data
@Table(name = "users")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "preferences"})
public class User {
    @Id
    @GeneratedValue
    private long id;
    @Email(message = "Email should be valid")
    private String email;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    @ToString.Exclude
    @OneToOne(mappedBy = "user")
    private UserPreferences preferences;
}

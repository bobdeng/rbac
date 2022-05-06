package cn.bobdeng.base.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rbac_password")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PasswordDO {
    @Id
    private int id;
    private String password;

    public PasswordDO(User user, Password password, PasswordEncoder passwordEncoder) {
        this.id = user.id();
        this.password = password.encode(passwordEncoder);
    }

    public Password password() {
        return new Password(this.password);
    }
}

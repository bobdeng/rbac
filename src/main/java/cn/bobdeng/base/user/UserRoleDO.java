package cn.bobdeng.base.user;

import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "rbac_user_role")
public class UserRoleDO {
    @Id
    private int id;
    private String roles;

    public UserRoleDO(User user, List<String> roles) {
        this.id = user.id();
        this.roles = new Gson().toJson(roles);
    }
}

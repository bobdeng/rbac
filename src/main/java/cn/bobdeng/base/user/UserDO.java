package cn.bobdeng.base.user;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rbac_user")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class UserDO {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    private String tenantId;
    private String name;

    public UserDO(Users users, User user) {
        this.name = user.name();
        this.tenantId = users.tenantId();
    }
}

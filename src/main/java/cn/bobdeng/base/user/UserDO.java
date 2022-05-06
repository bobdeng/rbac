package cn.bobdeng.base.user;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "rbac_user")
public class UserDO {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.IDENTITY)
    private int id;
    private String tenantId;
    private String name;
    private String userId;

    public UserDO(Users users, User user) {
        this.userId = user.id();
        this.name = user.name();
        this.tenantId = users.tenantId();
    }
}

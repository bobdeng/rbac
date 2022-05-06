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
@Table(name = "rbac_account")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class AccountDO {
    @Id
    private int id;
    private String name;
    private String tenantId;

    public AccountDO(User user, Account account) {
        this.id = user.id();
        this.tenantId = user.tenantId();
        this.name = account.name();
    }
}

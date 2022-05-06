package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import lombok.Getter;

public class User {
    @Getter
    private UserId id;
    private TenantId tenantId;
    private UserName name;

    public User(UserName name) {
        this.name = name;
    }

    public User(UserId id, TenantId tenantId) {
        this.id = id;
        this.tenantId = tenantId;
    }

    public User(UserDO userDO) {
        this.name = new UserName(userDO.getName());
        this.id = new UserId(userDO.getId());
        this.tenantId = new TenantId(userDO.getTenantId());
    }

    public String name() {
        return name.name();
    }

    public Integer id() {
        return id.id();
    }

    public void bindAccount(Account account, UserAccountRepository accountRepository) {
        accountRepository.findAccount(tenantId, account)
                .map(Account::new)
                .filter(it -> !it.isUser(this))
                .ifPresent(it -> {
                    throw new UserAccountAlreadyExistException(account.name());
                });
        accountRepository.save(new AccountDO(this, account));
    }

    public String tenantId() {
        return tenantId.id();
    }
}

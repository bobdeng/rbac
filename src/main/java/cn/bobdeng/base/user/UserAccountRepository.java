package cn.bobdeng.base.user;


import cn.bobdeng.base.TenantId;

import java.util.Optional;

public interface UserAccountRepository {
    void save(AccountDO userDO);

    Optional<AccountDO> findAccount(TenantId tenantId, Account account);
}

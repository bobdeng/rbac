package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public class UserAccountRepositoryImpl implements UserAccountRepository {
    private final DummyDao<AccountDO, Integer> accountDao;

    public UserAccountRepositoryImpl(DummyDao<AccountDO, Integer> accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void save(AccountDO accountDO) {
        accountDao.save(accountDO);
    }

    @Override
    public Optional<AccountDO> findAccount(TenantId tenantId, Account account) {
        return accountDao.all().stream()
                .filter(accountDO -> accountDO.getTenantId().equals(tenantId.id()))
                .filter(accountDO -> accountDO.getName().equals(account.name()))
                .findFirst();
    }
}

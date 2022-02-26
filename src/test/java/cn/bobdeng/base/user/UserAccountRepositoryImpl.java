package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public class UserAccountRepositoryImpl implements UserAccountRepository {
    private final DummyDao<AccountDO, String> accountDao;

    public UserAccountRepositoryImpl(DummyDao<AccountDO, String> accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public void save(User user, Account account) {
        accountDao.save(AccountDO.builder()
                .id(user.id())
                .name(account.getName())
                .build());

    }

    @Override
    public Optional<UserId> findUserByAccount(Account account) {
        return accountDao.all().stream()
                .filter(accountDO -> accountDO.getName().equals(account.getName()))
                .map(accountDO -> UserId.of(accountDO.getId()))
                .findFirst();
    }
}

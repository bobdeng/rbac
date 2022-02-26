package cn.bobdeng.base.user;

import java.util.Optional;

public interface UserAccountRepository {
    void save(User user, Account account);

    Optional<UserId> findUserByAccount(Account account);
}

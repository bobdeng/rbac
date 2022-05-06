package cn.bobdeng.base.user;

import java.util.Optional;

public interface PasswordRepository {
    void save(PasswordDO passwordDO);

    Optional<PasswordDO> findByUser(User user);

    PasswordEncoder passwordEncoder();
}

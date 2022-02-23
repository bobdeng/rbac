package cn.bobdeng.base.user;

import java.util.Optional;

public interface UserRepository {
    User save(Users users, User user);

    Optional<User> findById(String id);

    void save(User user);
}

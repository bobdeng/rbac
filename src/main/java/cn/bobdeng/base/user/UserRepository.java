package cn.bobdeng.base.user;


import java.util.Optional;

public interface UserRepository {
    UserDO save(UserDO userDO);

    Optional<User> findById(UserId id);
}

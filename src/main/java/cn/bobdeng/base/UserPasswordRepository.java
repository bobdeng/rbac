package cn.bobdeng.base;

import java.util.Optional;

public interface UserPasswordRepository {
    void save(User user, Password password);

    Optional<EncodedPassword> findByUser(User user);
}

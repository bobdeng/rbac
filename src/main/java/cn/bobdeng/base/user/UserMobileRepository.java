package cn.bobdeng.base.user;

import java.util.List;
import java.util.Optional;

public interface UserMobileRepository {
    void save(User user, Mobile userMobile);

    List<Mobile> findByUser(User user);

    Optional<UserId> findByMobile(Mobile mobile);
}

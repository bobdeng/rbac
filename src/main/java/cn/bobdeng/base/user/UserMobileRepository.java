package cn.bobdeng.base.user;

import java.util.List;

public interface UserMobileRepository {
    void save(User user, Mobile userMobile);

    List<Mobile> findByUser(User user);
}

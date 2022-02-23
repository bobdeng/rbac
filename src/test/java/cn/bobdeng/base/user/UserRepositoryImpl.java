package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

public class UserRepositoryImpl implements UserRepository {
    private DummyDao<UserDO, String> userDao;

    public UserRepositoryImpl(DummyDao<UserDO, String> userDao) {

        this.userDao = userDao;
    }

    @Override
    public User save(Users users, User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.id());
        userDO.setStatus(user.status().status.getStatus());
        userDao.save(userDO);
        return user;
    }
}

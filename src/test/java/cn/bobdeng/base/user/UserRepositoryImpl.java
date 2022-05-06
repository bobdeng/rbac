package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

public class UserRepositoryImpl implements UserRepository {
    private final DummyDao<UserDO, Integer> dummyDao;

    public UserRepositoryImpl(DummyDao<UserDO, Integer> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public void save(UserDO userDO) {
        dummyDao.save(userDO);
    }
}

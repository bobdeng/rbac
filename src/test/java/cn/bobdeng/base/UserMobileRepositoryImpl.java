package cn.bobdeng.base;

import cn.bobdeng.dummydao.DummyDao;

public class UserMobileRepositoryImpl implements UserMobileRepository {
    private final DummyDao<UserMobileDO, String> dummyDao;

    public UserMobileRepositoryImpl(DummyDao<UserMobileDO, String> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public void save(User user, UserMobile userMobile) {
        dummyDao.save(new UserMobileDO(user,userMobile));
    }
}

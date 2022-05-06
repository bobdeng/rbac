package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Objects;
import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private final DummyDao<UserDO, Integer> dummyDao;

    public UserRepositoryImpl(DummyDao<UserDO, Integer> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public UserDO save(UserDO userDO) {
        return dummyDao.save(userDO);
    }

    @Override
    public Optional<User> findById(UserId id) {
        return Optional.empty();
    }

}

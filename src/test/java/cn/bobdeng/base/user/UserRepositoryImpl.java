package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public class UserRepositoryImpl implements UserRepository {
    private DummyDao<UserDO, String> userDao;

    public UserRepositoryImpl(DummyDao<UserDO, String> userDao) {

        this.userDao = userDao;
    }

    @Override
    public User save(Users users, User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.id());
        userDO.setStatus(user.status().statusName());
        userDao.save(userDO);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userDao.findById(id)
                .map(userDO -> {
                    return new User(UserId.of(id), UserStatus.of(userDO.getStatus()));
                });
    }

    @Override
    public void save(User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.id());
        userDO.setStatus(user.status().statusName());
        userDao.save(userDO);
    }
}

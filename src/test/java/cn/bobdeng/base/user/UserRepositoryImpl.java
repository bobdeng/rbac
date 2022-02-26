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
        userDO.setLevel(user.levelName());
        userDO.setName(user.name());
        userDao.save(userDO);
        return user;
    }

    @Override
    public Optional<User> findById(String id) {
        return userDao.findById(id)
                .map(userDO -> {
                    UserId userId = UserId.of(id);
                    UserStatus userStatus = UserStatus.of(userDO.getStatus());
                    UserLevel level = UserLevel.of(userDO.getLevel());
                    UserName name = new UserName(userDO.getName());
                    return new User(userId, userStatus, level, name);
                });
    }

    @Override
    public void save(User user) {
        UserDO userDO = new UserDO();
        userDO.setId(user.id());
        userDO.setStatus(user.status().statusName());
        userDO.setLevel(user.levelName());
        userDO.setName(user.name());
        userDao.save(userDO);
    }
}

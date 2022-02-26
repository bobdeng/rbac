package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
                .map(this::toEntity);
    }

    private User toEntity(UserDO userDO) {
        UserId userId = UserId.of(userDO.getId());
        UserStatus userStatus = UserStatus.of(userDO.getStatus());
        UserLevel level = UserLevel.of(userDO.getLevel());
        UserName name = new UserName(userDO.getName());
        return new User(userId, userStatus, level, name);
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

    @Override
    public List<User> all(Users users) {
        return userDao.all()
                .stream()
                .map(this::toEntity)
                .collect(Collectors.toList());
    }
}

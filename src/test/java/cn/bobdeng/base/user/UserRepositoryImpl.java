package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
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
    public Optional<User> findById(UserId id, TenantId tenantId) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByName(TenantId tenantId, String name) {
        return dummyDao.all().stream().filter(userDO -> Objects.equals(userDO.getName(), name))
                .map(User::new)
                .findFirst();
    }

}

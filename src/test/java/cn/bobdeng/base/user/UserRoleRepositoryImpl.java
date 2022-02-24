package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

public class UserRoleRepositoryImpl implements UserRoleRepository {
    private DummyDao<UserRoleDO, Integer> userRoleDao;

    public UserRoleRepositoryImpl(DummyDao<UserRoleDO, Integer> userRoleDao) {
        this.userRoleDao = userRoleDao;
    }

    @Override
    public void save(User user, UserRoles userRoles) {
        userRoleDao.save(UserRoleDO.builder()
                .userId(user.id())
                .roles(userRoles.rolesAsJson())
                .build());
    }

    @Override
    public Optional<UserRoleId> findByUser(User user) {
        return userRoleDao.all().stream()
                .filter(userRoleDO -> userRoleDO.getUserId().equals(user.id()))
                .map(userRoleDO -> new UserRoleId(userRoleDO.getId()))
                .findFirst();
    }

    @Override
    public Optional<UserRoles> findUserRoles(User user) {
        return userRoleDao.all().stream()
                .filter(userRoleDO -> userRoleDO.getUserId().equals(user.id()))
                .map(userRoleDO -> UserRoles.fromJson(userRoleDO.getRoles()))
                .findFirst();
    }

    @Override
    public void saveById(User user, UserRoleId userRoleId, UserRoles userRoles) {
        userRoleDao.save(UserRoleDO.builder()
                .id(userRoleId.id())
                .roles(userRoles.rolesAsJson())
                .userId(user.id())
                .build());
    }
}

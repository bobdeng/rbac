package cn.bobdeng.base.user;

import cn.bobdeng.base.role.RoleRepository;
import cn.bobdeng.dummydao.DummyDao;
import lombok.Setter;

import java.util.Optional;

public class UserRoleRepositoryImpl implements UserRoleRepository {

    private DummyDao<UserRoleDO, Integer> dummyDao;
    @Setter
    private RoleRepository roleRepository;

    public UserRoleRepositoryImpl(DummyDao<UserRoleDO, Integer> dummyDao) {

        this.dummyDao = dummyDao;
    }

    @Override
    public void save(UserRoleDO userRoleDO) {
        dummyDao.save(userRoleDO);
    }

    @Override
    public Optional<UserRoleDO> findById(Integer id) {
        return dummyDao.findById(id);
    }

    @Override
    public RoleRepository roleRepository() {
        return roleRepository;
    }
}

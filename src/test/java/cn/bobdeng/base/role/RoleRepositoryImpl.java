package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.DummyDao;

import java.util.stream.Stream;

public class RoleRepositoryImpl implements RoleRepository {
    private DummyDao<RoleDO, Integer> dummyDao;

    public RoleRepositoryImpl(DummyDao<RoleDO, Integer> dummyDao) {

        this.dummyDao = dummyDao;
    }

    @Override
    public void save(RoleDO roleDO) {
        dummyDao.save(roleDO);
    }

    @Override
    public Stream<Role> findAll(TenantId tenantId) {
        return dummyDao.all()
                .stream()
                .filter(roleDO -> roleDO.getTenantId().equals(tenantId.id()))
                .map(Role::new);
    }
}

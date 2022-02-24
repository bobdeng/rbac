package cn.bobdeng.base.role;

import cn.bobdeng.dummydao.DummyDao;
import com.google.gson.Gson;

import java.util.Optional;

public class RoleRepositoryImpl implements RoleRepository {
    private final DummyDao<RoleDO,String> roleDao;

    public RoleRepositoryImpl(DummyDao<RoleDO, String> roleDao) {
        this.roleDao = roleDao;
    }

    @Override
    public void save(Roles roles, Role role) {
        roleDao.save(RoleDO.builder()
                .functions(role.functionsAsJson())
                .name(role.name())
                .tenantId(roles.tenantId())
                .build());
    }

    @Override
    public Optional<Role> findById(RoleId roleId) {
        return roleDao.findById(roleId.id())
                .map(roleDO -> new Role(roleId,new RoleName(roleDO.getName()),Functions.fromJson(roleDO.getFunctions())));
    }
}

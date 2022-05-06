package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;

public class Roles {
    private TenantId tenantId;

    public Roles(TenantId tenantId) {

        this.tenantId = tenantId;
    }

    public void saveRole(Role role, RoleRepository roleRepository) throws RoleAlreadyExistException {
        if (roleRepository.findAll(tenantId)
                .anyMatch(it->it.hasSameName(role))) {
            throw new RoleAlreadyExistException(role.name());
        }
        RoleDO roleDO = new RoleDO(tenantId, role);
        roleRepository.save(roleDO);
    }
}

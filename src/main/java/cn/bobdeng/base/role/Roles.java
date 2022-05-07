package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;

public class Roles {
    private TenantId tenantId;

    public Roles(TenantId tenantId) {

        this.tenantId = tenantId;
    }

    public void saveRole(Role role, RoleRepository roleRepository) throws RoleAlreadyExistException {
        if (role.hasId()) {
            update(role, roleRepository);
            return;
        }
        create(role, roleRepository);
    }

    private void create(Role role, RoleRepository roleRepository) throws RoleAlreadyExistException {
        if (roleRepository.findAll(tenantId)
                .anyMatch(it -> it.hasSameName(role))) {
            throw new RoleAlreadyExistException(role.name());
        }
        roleRepository.save(new RoleDO(tenantId, role));
    }

    private void update(Role role, RoleRepository roleRepository) {
        if (roleRepository.findById(tenantId, role.id()).isEmpty()) {
            throw new PermissionDeniedException();
        }
        roleRepository.save(new RoleDO(tenantId, role));
    }
}

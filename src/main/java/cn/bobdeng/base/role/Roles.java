package cn.bobdeng.base.role;

import cn.bobdeng.base.user.TenantId;

import java.util.Optional;

public class Roles {
    public static RoleRepository roleRepository;
    private TenantId tenantId;

    public Roles(TenantId tenantId) {

        this.tenantId = tenantId;
    }

    public Roles() {
    }

    public Role newRole(RoleName roleName, Functions functions) {
        Role result = Role.create(roleName, functions);
        roleRepository.save(this, result);
        return result;
    }

    public String tenantId() {
        return Optional.ofNullable(this.tenantId).map(TenantId::getId).orElse(null);
    }
}

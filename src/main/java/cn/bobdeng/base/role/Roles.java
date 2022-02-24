package cn.bobdeng.base.role;

public class Roles {
    public static RoleRepository roleRepository;

    public Role newRole(RoleName roleName, Functions functions) {
        Role result = Role.create(roleName, functions);
        roleRepository.save(this, result);
        return result;
    }
}

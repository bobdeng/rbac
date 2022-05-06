package cn.bobdeng.base.role;

import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.util.List;

@ToString
public class Role {
    private RoleId id;
    private RoleName roleName;
    private RoleFunctions roleFunctions;

    public Role(RoleName roleName, RoleFunctions roleFunctions) {

        this.roleName = roleName;
        this.roleFunctions = roleFunctions;
    }

    public Role(RoleDO roleDO) {
        this.roleName = new RoleName(roleDO.getName());
        this.roleFunctions = new RoleFunctions(roleDO.getFunctions());
        this.id = new RoleId(roleDO.getId());
    }

    public List<String> functions() {
        return roleFunctions.getFunctions();
    }

    public String name() {
        return roleName.getName();
    }

    public boolean hasSameName(Role role) {
        return this.roleName.equals(role.roleName);
    }

    public boolean isName(RoleName roleName) {
        return this.roleName.equals(roleName);
    }

    public boolean hasPermission(String functionName) {
        return this.roleFunctions.contains(functionName);
    }
}

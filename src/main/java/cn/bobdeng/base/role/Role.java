package cn.bobdeng.base.role;

import java.util.stream.Stream;

public class Role {
    private RoleId id;
    private RoleName name;
    private Functions functions;

    public Role(RoleId id, RoleName name, Functions functions) {
        this.id = id;
        this.name = name;
        this.functions = functions;
    }

    public static Role create(RoleName name, Functions functions) {
        return new Role(RoleId.create(),name,functions);
    }

    public Functions functions() {
        return functions;
    }

    public String name() {
        return name.name();
    }

    public String functionsAsJson() {
        return functions.asJson();
    }

    public boolean hasPermission(Function function) {
        return functions.hasPermission(function);
    }

    public boolean hasAnyPermission(Function[] functions) {
        return Stream.of(functions)
                .anyMatch(function -> this.functions.hasPermission(function));
    }
}

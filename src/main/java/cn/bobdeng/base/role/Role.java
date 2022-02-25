package cn.bobdeng.base.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.util.stream.Stream;

@EqualsAndHashCode
@Getter
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
        return new Role(RoleId.create(), name, functions);
    }

    public String name() {
        return name.name();
    }

    public String functionsAsJson() {
        return functions.asJson();
    }

    public boolean hasAnyPermission(Function... functions) {
        return Stream.of(functions)
                .anyMatch(function -> this.functions.hasPermission(function));
    }

    public String id() {
        return id.id();
    }
}

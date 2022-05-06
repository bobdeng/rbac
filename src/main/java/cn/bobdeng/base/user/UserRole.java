package cn.bobdeng.base.user;

import cn.bobdeng.base.role.RoleName;
import com.google.gson.Gson;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class UserRole {
    private List<String> roles;

    public UserRole(UserRoleDO userRoleDO) {
        this.roles = Arrays.asList(new Gson().fromJson(userRoleDO.getRoles(), String[].class));
    }

    public List<String> roles() {
        return roles;
    }

    public Stream<RoleName> rolesStream() {
        return this.roles.stream()
                .map(RoleName::new);
    }
}

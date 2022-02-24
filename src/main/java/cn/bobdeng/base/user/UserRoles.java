package cn.bobdeng.base.user;

import cn.bobdeng.base.role.RoleId;
import com.google.gson.Gson;
import lombok.EqualsAndHashCode;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
@EqualsAndHashCode
public class UserRoles {
    private List<RoleId> roleIdList;

    public UserRoles(List<RoleId> roleIdList) {
        this.roleIdList = roleIdList;
    }

    public static UserRoles fromJson(String roles) {
        List<RoleId> list = Arrays.asList(new Gson().fromJson(roles, RoleId[].class));
        return new UserRoles(list);
    }

    public String rolesAsJson() {
        return new Gson().toJson(roleIdList);
    }

    public Stream<RoleId> roles() {
        return roleIdList.stream();
    }
}

package cn.bobdeng.base.user;

import cn.bobdeng.base.role.*;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserRoleTest {
    private DummyDao<UserRoleDO, Integer> userRoleDao;
    private DummyDao<RoleDO, String> roleDao;

    @BeforeEach
    public void init() {
        userRoleDao = new DummyDao<>(UserRoleDO.class, "id", new AutoIntegerIdGenerator());
        roleDao = new DummyDao<>(RoleDO.class, "id", new UUIDGeneratorImpl());
        Users.userRoleRepository = new UserRoleRepositoryImpl(userRoleDao);
        Roles.roleRepository = new RoleRepositoryImpl(roleDao);
    }

    @Test
    public void should_has_one_role_when_new_role() {
        User user = User.create();
        UserRoles userRoles = new UserRoles(Arrays.asList(RoleId.of("123456")));
        user.setRoles(userRoles);

        assertThat(userRoleDao.all().size(), is(1));
    }

    @Test
    public void should_has_one_role_when_save_role() {
        User user = User.create();
        UserRoles userRoles = new UserRoles(Arrays.asList(RoleId.of("123456")));
        userRoleDao.save(UserRoleDO.builder()
                .userId(user.id())
                .roles(userRoles.rolesAsJson())
                .build());

        user.setRoles(userRoles);

        assertThat(userRoleDao.all().size(), is(1));
    }

    @Test
    public void should_has_no_permission_when_has_no_role() {
        User user = User.create();
        assertThat(user.hasPermission(new Function("function.name")), is(false));
    }

    @Test
    public void should_has_permission_when_has_has_role() {
        String roleId = "role_id_123";
        String functionName = "function.name";
        User user = User.create();
        userRoleDao.save(UserRoleDO.builder()
                .userId(user.id())
                .roles(new UserRoles(Arrays.asList(new RoleId(roleId))).rolesAsJson())
                .build());
        Functions functions = new Functions(Arrays.asList(new Function(functionName)));
        roleDao.save(RoleDO.builder()
                .functions(functions.asJson())
                .id(roleId)
                .build());

        assertThat(user.hasPermission(new Function(functionName)), is(true));
    }
}

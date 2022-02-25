package cn.bobdeng.base.role;

import cn.bobdeng.base.user.TenantId;
import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;

public class RoleTest {
    private DummyDao<RoleDO, String> roleDao;

    @BeforeEach
    public void setup() {
        roleDao = new DummyDao<>(RoleDO.class, "id", new UUIDGeneratorImpl());
        Roles.roleRepository = new RoleRepositoryImpl(roleDao);
    }

    @Test
    public void should_has_one_role_when_save_role() {
        Roles roles = new Roles();
        RoleName roleName = new RoleName("角色名称");
        List<Function> functionList = Arrays.asList(new Function("function.1"));
        Functions functions = new Functions(functionList);

        Role role = roles.newRole(roleName, functions);

        assertThat(role, notNullValue());
        assertThat(roleDao.all().size(), is(1));
    }

    @Test
    public void should_has_one_role_when_save_role_with_tenant() {
        TenantId tenantId = new TenantId("123456");
        Roles roles = new Roles(tenantId);
        RoleName roleName = new RoleName("角色名称");
        List<Function> functionList = Arrays.asList(new Function("function.1"));
        Functions functions = new Functions(functionList);

        Role role = roles.newRole(roleName, functions);

        assertThat(role, notNullValue());
        assertThat(roleDao.all().size(), is(1));
        assertThat(roleDao.all().get(0).getTenantId(), is(tenantId.getId()));
    }

    @Test
    public void list_all_no_tenant() {
        Roles roles = new Roles();
        RoleName roleName = new RoleName("角色名称");
        List<Function> functionList = Arrays.asList(new Function("function.1"));
        Functions functions = new Functions(functionList);
        roles.newRole(roleName, functions);

        List<Role> list = roles.list();

        assertThat(list.size(), is(1));

    }

}

package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoleTest {
    private DummyDao<RoleDO, Integer> dummyDao;
    private RoleRepository roleRepository;

    @BeforeEach
    public void setup() {
        dummyDao = new DummyDao<>(RoleDO.class, "id", new AutoIntegerIdGenerator());
        roleRepository = new RoleRepositoryImpl(dummyDao);
    }

    @Test
    public void should_create_role() throws RoleAlreadyExistException {
        Roles roles = new Roles(new TenantId("1"));
        RoleName roleName = new RoleName("admin");
        List<String> functions = Arrays.asList("1", "2", "3");
        RoleFunctions roleFunctions = new RoleFunctions(functions);
        Role role = new Role(roleName, roleFunctions);

        roles.saveRole(role, roleRepository);

        assertThat(dummyDao.all().size(), is(1));
        RoleDO roleDO = dummyDao.all().get(0);
        assertThat(roleDO.getFunctions(), is(new Gson().toJson(functions)));
        assertThat(roleDO.getTenantId(), is("1"));
        assertThat(roleDO.getName(), is("admin"));
    }

    @Test
    public void should_throw_when_role_name_exist() {
        Roles roles = new Roles(new TenantId("1"));
        RoleName roleName = new RoleName("admin");
        List<String> functions = Arrays.asList("1", "2", "3");
        RoleFunctions roleFunctions = new RoleFunctions(functions);
        Role role = new Role(roleName, roleFunctions);
        dummyDao.save(RoleDO.builder()
                .name("admin")
                .tenantId("1")
                .functions("[\"1\"]")
                .build());

        RoleAlreadyExistException e = assertThrows(RoleAlreadyExistException.class, () -> roles.saveRole(role, roleRepository));
        assertThat(e.getName(), is("admin"));
    }

    @Test
    public void should_save_role() throws RoleAlreadyExistException {
        dummyDao.save(RoleDO.builder()
                .id(1)
                .name("admin")
                .tenantId("1")
                .functions("[\"1\"]")
                .build());

        Roles roles = new Roles(new TenantId("1"));
        RoleName roleName = new RoleName("admin");
        List<String> functions = Arrays.asList("1", "2", "3");
        RoleFunctions roleFunctions = new RoleFunctions(functions);
        Role role = new Role(new RoleId(1), roleName, roleFunctions);

        roles.saveRole(role, roleRepository);

        assertThat(dummyDao.all().size(), is(1));
        RoleDO roleDO = dummyDao.all().get(0);
        assertThat(roleDO.getFunctions(), is(new Gson().toJson(functions)));
        assertThat(roleDO.getTenantId(), is("1"));
        assertThat(roleDO.getName(), is("admin"));
    }

    @Test
    public void should_throw_when_save_role_with_different_tenant() {
        RoleDO roleDO = dummyDao.save(RoleDO.builder()
                .name("admin")
                .tenantId("1")
                .functions("[]").build());
        Roles roles = new Roles(new TenantId("2"));
        assertThrows(PermissionDeniedException.class, () -> roles.saveRole(new Role(new RoleId(roleDO.getId()), new RoleName("admin"), new RoleFunctions(Arrays.asList("123"))), roleRepository));
    }

}

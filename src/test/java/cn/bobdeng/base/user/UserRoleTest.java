package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.base.role.RoleDO;
import cn.bobdeng.base.role.RoleRepositoryImpl;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserRoleTest {
    private UserRoleRepositoryImpl userRoleRepository;
    private DummyDao<UserRoleDO, Integer> userRoleDao;
    private RoleRepositoryImpl roleRepository;
    private DummyDao<RoleDO, Integer> roleDao;

    @BeforeEach
    public void setup() {
        userRoleDao = new DummyDao<>(UserRoleDO.class, "id", new AutoIntegerIdGenerator());
        userRoleRepository = new UserRoleRepositoryImpl(userRoleDao);
        roleDao = new DummyDao<>(RoleDO.class, "id", new AutoIntegerIdGenerator());
        roleRepository = new RoleRepositoryImpl(roleDao);
        userRoleRepository.setRoleRepository(roleRepository);
    }

    @Test
    public void should_set_roles_of_user() {
        User user = new User(new UserId(100), null);

        user.setRoles(Arrays.asList("admin"), userRoleRepository);

        assertThat(userRoleDao.all().size(), is(1));
        UserRoleDO userRoleDO = userRoleDao.all().get(0);
        assertThat(userRoleDO.getId(), is(user.id()));
        assertThat(userRoleDO.getRoles(), is(new Gson().toJson(Arrays.asList("admin"))));
    }

    @Test
    public void user_has_permission_when_has_role() {
        TenantId tenantId = new TenantId("1");
        User user = new User(new UserId(100), tenantId);
        String adminRole = "admin";
        userRoleDao.save(UserRoleDO.builder()
                .id(user.id())
                .roles(new Gson().toJson(List.of(adminRole, "not_exist")))
                .build());
        roleDao.save(RoleDO.builder()
                .functions(new Gson().toJson(List.of("user.create")))
                .tenantId(tenantId.id())
                .name(adminRole)
                .build());

        assertThat(user.hasPermission("user.create", userRoleRepository), is(true));
        assertThat(user.hasPermission("user.delete", userRoleRepository), is(false));

    }
}

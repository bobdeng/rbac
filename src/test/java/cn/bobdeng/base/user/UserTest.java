package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {
    private DummyDao<UserDO, String> userDao;

    @BeforeEach
    public void init() {
        userDao = new DummyDao<>(UserDO.class, "id", new UUIDGeneratorImpl());
        Users.userRepository = new UserRepositoryImpl(userDao);
    }

    @Test
    public void should_create_user_without_tenant() {
        Users users = new Users();
        User user = users.newUser();
        assertThat(user.id(), notNullValue());
        assertThat(user.status(), is(UserStatus.active()));
        assertThat(userDao.all().size(), is(1));
    }

    @Test
    public void should_create_user_with_tenant() {
        TenantId tenant = TenantId.of("123");
        Users users = Users.ofTenant(tenant);
        User user = users.newUser();
        assertThat(user.getId(), notNullValue());
        assertThat(userDao.all().size(), is(1));
    }

    @Test
    public void should_return_tenantId_when_has_tenantId() {
        TenantId tenant = TenantId.of("123");
        Users users = Users.ofTenant(tenant);
        assertThat(users.tenantId(), is("123"));
    }

    @Test
    public void should_return_null_when_has_no_tenantId() {
        Users users = new Users();
        assertThat(users.tenantId(), nullValue());
    }

    @Test
    public void should_suspend_when_user_is_suspend() {

    }
}

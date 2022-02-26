package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

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
        assertThat(user.statusName(), is(UserStatus.active().statusName()));
        assertThat(userDao.all().size(), is(1));
        assertThat(userDao.all().get(0).getLevel(), is("user"));
        assertThat(user.isAdmin(), is(false));
    }

    @Test
    public void should_is_admin_when_create_admin() {
        Users users = new Users();
        User user = users.newAdmin();
        assertThat(userDao.all().size(), is(1));
        assertThat(userDao.all().get(0).getLevel(), is("admin"));
        assertThat(user.isAdmin(), is(true));
    }

    @Test
    public void should_create_user_with_tenant() {
        TenantId tenant = TenantId.of("123");
        Users users = Users.ofTenant(tenant);
        User user = users.newUser(new UserName("张三"));
        assertThat(user.getId(), notNullValue());
        assertThat(userDao.all().size(), is(1));
        assertThat(userDao.all().get(0).getName(), is("张三"));
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
        UserDO userDO = new UserDO(User.create(UserName.empty()));
        userDao.save(userDO);
        User user = Users.userRepository.findById(userDO.getId()).orElse(null);

        user.suspend();

        UserDO savedUserDO = userDao.findById(userDO.getId()).orElse(null);
        assertThat(savedUserDO.getStatus(), is(UserStatusEnum.suspend.getStatus()));
    }

    @Test
    public void should_deleted_when_user_is_delete() {
        UserDO userDO = new UserDO(User.create(UserName.empty()));
        userDao.save(userDO);
        User user = Users.userRepository.findById(userDO.getId()).orElse(null);

        user.remove();

        UserDO savedUserDO = userDao.findById(userDO.getId()).orElse(null);
        assertThat(savedUserDO.getStatus(), is(UserStatusEnum.deleted.getStatus()));
    }

    @Test
    public void should_active_when_user_is_active() {
        UserDO userDO = new UserDO(User.create(UserName.empty()));
        userDO.setStatus(UserStatusEnum.suspend.getStatus());
        userDao.save(userDO);
        User user = Users.userRepository.findById(userDO.getId()).orElse(null);

        user.active();

        UserDO savedUserDO = userDao.findById(userDO.getId()).orElse(null);
        assertThat(savedUserDO.getStatus(), is(UserStatusEnum.active.getStatus()));
    }

    @Test
    public void list_user_by_name() {
        UserDO userDO = new UserDO(User.create(new UserName("张三")));
        userDO.setStatus(UserStatusEnum.suspend.getStatus());
        userDao.save(userDO);

        List<User> users = new Users().list();

        assertThat(users, notNullValue());
        assertThat(users.size(), is(1));
    }
}

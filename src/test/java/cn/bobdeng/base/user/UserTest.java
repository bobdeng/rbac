package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;

public class UserTest {
    private DummyDao<UserDO, Integer> dummyDao;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        dummyDao = new DummyDao<>(UserDO.class, "id", new AutoIntegerIdGenerator());
        userRepository = new UserRepositoryImpl(dummyDao);
    }

    @Test
    public void should_create_user() {
        NewUserRequest newUserRequest = new NewUserRequest("123456", "bob");
        Users users = new Users(new TenantId("1111"));
        users.newUser(newUserRequest, userRepository);

        assertThat(dummyDao.all().size(), CoreMatchers.is(1));
        UserDO userDO = dummyDao.all().get(0);
        assertThat(userDO.getUserId(), CoreMatchers.is("123456"));
        assertThat(userDO.getName(), CoreMatchers.is("bob"));
        assertThat(userDO.getTenantId(), CoreMatchers.is("1111"));
    }
}

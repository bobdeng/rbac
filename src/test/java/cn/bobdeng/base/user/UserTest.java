package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
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
    public void should_create_user() throws UserAlreadyExistException {
        NewUserRequest newUserRequest = new NewUserRequest("bob");
        Users users = new Users(new TenantId("1111"));

        User user = users.newUser(newUserRequest, userRepository);

        assertThat(user.id(), notNullValue());
        assertThat(dummyDao.all().size(), is(1));
        UserDO userDO = dummyDao.all().get(0);
        assertThat(userDO.getName(), is("bob"));
        assertThat(userDO.getTenantId(), is("1111"));
    }

}

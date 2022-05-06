package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

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

    @Test
    public void should_throw_when_user_name_exist() throws UserAlreadyExistException {
        dummyDao.save(UserDO.builder()
                .tenantId("1111")
                .name("bob")
                .build());
        NewUserRequest newUserRequest = new NewUserRequest("bob");
        Users users = new Users(new TenantId("1111"));

        UserAlreadyExistException e = assertThrows(UserAlreadyExistException.class, () -> users.newUser(newUserRequest, userRepository));
        assertThat(e.getName(), is("bob"));
    }
}

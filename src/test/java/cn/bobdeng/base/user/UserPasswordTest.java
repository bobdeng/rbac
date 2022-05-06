package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

public class UserPasswordTest {
    private DummyDao<PasswordDO, Integer> dummyDao;
    private PasswordRepositoryImpl passwordRepository;

    @BeforeEach
    public void setup() {
        dummyDao = new DummyDao<>(PasswordDO.class, "id", new AutoIntegerIdGenerator());
        passwordRepository = new PasswordRepositoryImpl(dummyDao);
    }

    @Test
    public void should_save_password() {
        Password userPassword = new Password("123456");
        User user = new User(new UserId(1), null);

        user.setPassword(userPassword, passwordRepository, new PasswordEncoderImpl());

        assertThat(dummyDao.all().size(), is(1));
        PasswordDO passwordDO = dummyDao.all().get(0);
        assertThat(passwordDO.getPassword(), is(new PasswordEncoderImpl().encode("123456")));
    }
    @Test
    public void should_return_true_when_verify_password_success(){
        Password userPassword = new Password("123456");
        User user = new User(new UserId(1), null);

        user.setPassword(userPassword, passwordRepository, new PasswordEncoderImpl());

        assertThat(user.verifyPassword(new Password("123456"), passwordRepository, new PasswordEncoderImpl()), is(true));
        assertThat(user.verifyPassword(new Password("123455"), passwordRepository, new PasswordEncoderImpl()), is(false));
    }

}

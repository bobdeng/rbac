package cn.bobdeng.base;

import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.IdGenerator;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserPasswordTest {
    private DummyDao<PasswordDO, String> dummyDao;

    @BeforeEach
    public void setup() {
        dummyDao = new DummyDao<>(PasswordDO.class, "id", new UUIDGeneratorImpl());
        Users.userPasswordRepository = new UserPasswordRepositoryImpl(dummyDao);
        Users.passwordEncoder = mock(PasswordEncoder.class);
    }

    @Test
    public void save_password_when_no_password() {
        String rawPassword = "uhk!dfd_";
        User user = new User(new UserId("123456"));
        String encodedPassword = "encoded_sdfdfafd";
        when(Users.passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        user.setPassword(new Password(rawPassword));

        List<PasswordDO> passwords = dummyDao.all();
        assertThat(passwords.size(), is(1));
        assertThat(passwords.get(0).getPassword(), is(encodedPassword));
    }

    @Test
    public void save_password_when_has_password() {
        String rawPassword = "uhk!dfd_";
        User user = new User(new UserId("123456"));
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setId(user.id());
        passwordDO.setPassword("123123");
        dummyDao.save(passwordDO);
        String encodedPassword = "encoded_sdfdfafd";
        when(Users.passwordEncoder.encode(rawPassword)).thenReturn(encodedPassword);

        user.setPassword(new Password(rawPassword));

        List<PasswordDO> passwords = dummyDao.all();
        assertThat(passwords.size(), is(1));
        assertThat(passwords.get(0).getPassword(), is(encodedPassword));
    }

    @Test
    public void should_fail_when_verify_password_and_no_password() {
        User user = new User(new UserId("123456"));
        assertThat(user.verifyPassword(new Password("123456")), is(false));
    }

    @Test
    public void should_success_when_verify_success() {
        User user = new User(new UserId("123456"));
        PasswordDO passwordDO = new PasswordDO();
        passwordDO.setId(user.id());
        passwordDO.setPassword("123123");
        dummyDao.save(passwordDO);
        when(Users.passwordEncoder.verify(new Password("123456"), new EncodedPassword("123123"))).thenReturn(true);

        assertThat(user.verifyPassword(new Password("123456")), is(true));
    }
}

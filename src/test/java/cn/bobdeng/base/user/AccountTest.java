package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AccountTest {
    private DummyDao<AccountDO, String> accountDao;

    @BeforeEach
    public void setup() {
        Users.userRepository = new UserRepositoryImpl(new DummyDao<>(UserDO.class, "id", new UUIDGeneratorImpl()));
        accountDao = new DummyDao<>(AccountDO.class, "id", new UUIDGeneratorImpl());
        Users.accountRepository = new UserAccountRepositoryImpl(accountDao);
    }

    @Test
    public void should_has_account_when_save() {
        User user = new Users().newUser(UserName.empty());
        Account account = new Account("bobdeng");

        user.bindAccount(account);

        assertThat(accountDao.all().size(), is(1));
    }

    @Test
    public void should_throw_when_account_exist() {
        accountDao.save(AccountDO.builder()
                .id("123")
                .name("bobdeng")
                .build());
        User user = new Users().newUser(UserName.empty());
        Account account = new Account("bobdeng");

        assertThrows(DuplicateAccountException.class, () -> user.bindAccount(account));

    }

    @Test
    public void should_not_throw_when_account_is_same() {
        User user = new Users().newUser(UserName.empty());
        accountDao.save(AccountDO.builder()
                .id(user.id())
                .name("bobdeng")
                .build());
        Account account = new Account("bobdeng");
        user.bindAccount(account);
        assertThat(accountDao.all().size(), is(1));
    }
}

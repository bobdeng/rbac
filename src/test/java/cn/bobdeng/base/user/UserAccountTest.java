package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.dummydao.AutoIntegerIdGenerator;
import cn.bobdeng.dummydao.DummyDao;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class UserAccountTest {
    private DummyDao<AccountDO, Integer> accountDao;
    private UserAccountRepository accountRepository;

    @BeforeEach
    public void setup() {
        accountDao = new DummyDao<>(AccountDO.class, "id", new AutoIntegerIdGenerator());
        accountRepository = new UserAccountRepositoryImpl(accountDao);
    }

    @Test
    public void should_bind_account_to_user() {
        Account userAccount = new Account("bob");
        User user = new User(new UserId(1), new TenantId("1"));

        user.bindAccount(userAccount, accountRepository);

        assertThat(accountDao.all().size(), is(1));
        AccountDO accountDO = accountDao.all().get(0);
        assertThat(accountDO.getId(), is(user.id()));
        assertThat(accountDO.getTenantId(), is(user.tenantId()));
    }

    @Test
    public void should_throw_when_create_account_exist() {
        Account userAccount = new Account("bob");
        accountDao.save(AccountDO.builder()
                .id(100)
                .name("bob")
                .tenantId("1")
                .build());
        User user = new User(new UserId(1), new TenantId("1"));

        UserAccountAlreadyExistException e = assertThrows(UserAccountAlreadyExistException.class, () -> user.bindAccount(userAccount, accountRepository));

        assertThat(e.getAccount(), is("bob"));
    }

    @Test
    public void should_throw_when_change_account_exist() {
        Account userAccount = new Account("bob");
        accountDao.save(AccountDO.builder()
                .id(100)
                .name("bob")
                .tenantId("1")
                .build());
        accountDao.save(AccountDO.builder()
                .id(1)
                .name("joe")
                .tenantId("1")
                .build());
        User user = new User(new UserId(1), new TenantId("1"));

        UserAccountAlreadyExistException e = assertThrows(UserAccountAlreadyExistException.class, () -> user.bindAccount(userAccount, accountRepository));

        assertThat(e.getAccount(), is("bob"));
    }

    @Test
    public void should_change_account() {
        Account userAccount = new Account("bob");
        accountDao.save(AccountDO.builder()
                .id(1)
                .name("joe")
                .tenantId("1")
                .build());
        User user = new User(new UserId(1), new TenantId("1"));

        user.bindAccount(userAccount, accountRepository);

        assertThat(accountDao.all().size(), is(1));
        AccountDO accountDO = accountDao.all().get(0);
        assertThat(accountDO.getId(), is(user.id()));
        assertThat(accountDO.getTenantId(), is(user.tenantId()));
    }

    @Test
    public void should_do_nothing_if_keep_same() {
        Account userAccount = new Account("bob");
        accountDao.save(AccountDO.builder()
                .id(1)
                .name("bob")
                .tenantId("1")
                .build());
        User user = new User(new UserId(1), new TenantId("1"));

        user.bindAccount(userAccount, accountRepository);

        assertThat(accountDao.all().size(), is(1));
        AccountDO accountDO = accountDao.all().get(0);
        assertThat(accountDO.getId(), is(user.id()));
        assertThat(accountDO.getTenantId(), is(user.tenantId()));
    }


    @Test
    public void should_create_when_another_tenant_has_same_account() {
        Account userAccount = new Account("bob");
        accountDao.save(AccountDO.builder()
                .id(2)
                .name("bob")
                .tenantId("2")
                .build());
        User user = new User(new UserId(1), new TenantId("1"));

        user.bindAccount(userAccount, accountRepository);

        assertThat(accountDao.all().size(), is(2));
    }
}

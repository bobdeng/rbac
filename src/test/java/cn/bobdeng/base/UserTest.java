package cn.bobdeng.base;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class UserTest {
    @Test
    public void should_create_user_without_tenant() {
        Users users = new Users();
        Users.userRepository = mock(UserRepository.class);
        User user = users.newUser();
        assertThat(user.id(), notNullValue());
        verify(Users.userRepository).save(users, user);
    }

    @Test
    public void should_create_user_with_tenant() {
        TenantId tenant = TenantId.of("123");
        Users users = Users.ofTenant(tenant);
        Users.userRepository = mock(UserRepository.class);
        User user = users.newUser();
        assertThat(user.getId(), notNullValue());
        verify(Users.userRepository).save(users, user);
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
}

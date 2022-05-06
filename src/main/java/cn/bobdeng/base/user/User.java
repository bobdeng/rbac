package cn.bobdeng.base.user;

import cn.bobdeng.base.TenantId;
import cn.bobdeng.base.role.Role;
import cn.bobdeng.base.role.RoleName;
import cn.bobdeng.base.role.RoleRepository;
import lombok.Getter;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class User {
    @Getter
    private UserId id;
    private TenantId tenantId;
    private UserName name;

    public User(UserName name) {
        this.name = name;
    }

    public User(UserId id, TenantId tenantId) {
        this.id = id;
        this.tenantId = tenantId;
    }

    public User(UserDO userDO) {
        this.name = new UserName(userDO.getName());
        this.id = new UserId(userDO.getId());
        this.tenantId = new TenantId(userDO.getTenantId());
    }

    public String name() {
        return name.name();
    }

    public Integer id() {
        return id.id();
    }

    public void bindAccount(Account account, UserAccountRepository accountRepository) {
        accountRepository.findAccount(tenantId, account)
                .map(Account::new)
                .filter(it -> !it.isUser(this))
                .ifPresent(it -> {
                    throw new UserAccountAlreadyExistException(account.name());
                });
        accountRepository.save(new AccountDO(this, account));
    }

    public String tenantId() {
        return tenantId.id();
    }

    public void setPassword(Password password, PasswordRepository passwordRepository, PasswordEncoder passwordEncoder) {
        passwordRepository.save(new PasswordDO(this, password, passwordEncoder));
    }

    public boolean verifyPassword(Password password, PasswordRepository passwordRepository, PasswordEncoder passwordEncoder) {
        return passwordRepository.findByUser(this)
                .map(PasswordDO::password)
                .stream().anyMatch(it -> password.match(it, passwordEncoder));
    }

    public void setRoles(List<String> roles, UserRoleRepository userRoleRepository) {
        UserRoleDO userRoleDO = new UserRoleDO(this, roles);
        userRoleRepository.save(userRoleDO);
    }

    public boolean hasPermission(String functionName,
                                 UserRoleRepository userRoleRepository) {
        List<Role> roles = userRoleRepository.roleRepository().findAll(tenantId).toList();
        Function<RoleName, Stream<? extends Role>> findRoleByName =
                roleName -> roles.stream().filter(role -> role.isName(roleName));
        return userRoleRepository.findById(this.id())
                .map(UserRole::new)
                .stream()
                .flatMap(UserRole::rolesStream)
                .flatMap(findRoleByName)
                .anyMatch(role -> role.hasPermission(functionName));
    }
}

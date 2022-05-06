package cn.bobdeng.base.user;

public interface PasswordEncoder {
    String encode(String password);

    boolean match(String rawPassword, String encodedPassword);
}

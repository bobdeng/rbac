package cn.bobdeng.base.user;

public class PasswordEncoderImpl implements PasswordEncoder {
    @Override
    public String encode(String password) {
        return password.hashCode() + "";
    }

    @Override
    public boolean match(String rawPassword, String encodedPassword) {
        return (rawPassword.hashCode() + "").equals(encodedPassword);
    }
}

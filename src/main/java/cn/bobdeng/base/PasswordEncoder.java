package cn.bobdeng.base;

public interface PasswordEncoder {
    String encode(String rawPassword);

    boolean verify(Password password, EncodedPassword encodedPassword);
}

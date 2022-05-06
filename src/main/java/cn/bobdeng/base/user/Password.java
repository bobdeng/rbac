package cn.bobdeng.base.user;

import lombok.Getter;

public class Password {
    @Getter
    private final String password;

    public Password(String password) {
        this.password = password;
    }

    public String encode(PasswordEncoder passwordEncoder) {
        return passwordEncoder.encode(this.password);
    }

    public boolean match(Password encoded, PasswordEncoder passwordEncoder) {
        return passwordEncoder.match(password, encoded.password);
    }
}

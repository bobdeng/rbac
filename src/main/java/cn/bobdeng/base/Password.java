package cn.bobdeng.base;

import static cn.bobdeng.base.Users.*;

public class Password {
    private String rawPassword;

    public Password(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String encode() {
        return passwordEncoder.encode(rawPassword);
    }
}

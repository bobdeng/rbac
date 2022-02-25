package cn.bobdeng.base.user;


import lombok.EqualsAndHashCode;
import lombok.Getter;

import static cn.bobdeng.base.user.Users.passwordEncoder;
@EqualsAndHashCode
@Getter
public class Password {
    private String rawPassword;

    public Password(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String encode() {
        return passwordEncoder.encode(rawPassword);
    }

}

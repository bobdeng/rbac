package cn.bobdeng.base.user;


import lombok.EqualsAndHashCode;

import java.util.Objects;

import static cn.bobdeng.base.user.Users.*;
@EqualsAndHashCode
public class Password {
    private String rawPassword;

    public Password(String rawPassword) {
        this.rawPassword = rawPassword;
    }

    public String encode() {
        return passwordEncoder.encode(rawPassword);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        Password password = (Password) o;
        return Objects.equals(rawPassword, password.rawPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(rawPassword);
    }
}

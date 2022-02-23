package cn.bobdeng.base;

import java.util.Objects;

public class EncodedPassword {
    private String encoded;

    public EncodedPassword(String encoded) {
        this.encoded = encoded;
    }

    public boolean match(Password password) {
        return Users.passwordEncoder.verify(password,this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        EncodedPassword that = (EncodedPassword) o;
        return Objects.equals(encoded, that.encoded);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encoded);
    }
}

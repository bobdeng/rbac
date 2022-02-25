package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode
public class EncodedPassword {
    private String encoded;

    public EncodedPassword(String encoded) {
        this.encoded = encoded;
    }

    public boolean match(Password password) {
        return Users.passwordEncoder.verify(password, this);
    }
}

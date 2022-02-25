package cn.bobdeng.base.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@EqualsAndHashCode
@Getter
public class EncodedPassword {
    private String encoded;

    public EncodedPassword(String encoded) {
        this.encoded = encoded;
    }

    public boolean match(Password password) {
        return Users.passwordEncoder.verify(password, this);
    }
}

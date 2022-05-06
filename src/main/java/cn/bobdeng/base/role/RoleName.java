package cn.bobdeng.base.role;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@EqualsAndHashCode
@ToString
public class RoleName {
    @Getter
    private String name;

    public RoleName(String name) {

        this.name = name;
    }

}

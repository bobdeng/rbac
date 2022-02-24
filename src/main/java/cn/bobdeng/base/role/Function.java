package cn.bobdeng.base.role;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode
public class Function {
    private String name;

    public Function(String name) {
        this.name = name;
    }
}

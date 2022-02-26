package cn.bobdeng.base.user;

import lombok.Data;
import lombok.Getter;

@Data
public class UserDO {
    private String id;
    private String status;
    private String level;
    private String name;

    public UserDO(User user) {
        this.id = user.id();
        this.status = user.statusName();
        this.level = user.levelName();
        this.name = user.name();
    }

    public UserDO() {
    }
}

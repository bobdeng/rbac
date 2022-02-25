package cn.bobdeng.base.user;

import lombok.Data;
import lombok.Getter;

@Data
public class UserDO {
    private String id;
    private String status;
    private String level;
    public UserDO(User user) {
        this.id = user.id();
        this.status = user.statusName();
        this.level = user.levelName();
    }

    public UserDO() {
    }
}

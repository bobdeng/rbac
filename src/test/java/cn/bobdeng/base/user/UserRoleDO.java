package cn.bobdeng.base.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRoleDO {
    private int id;
    private String userId;
    private String roles;
}

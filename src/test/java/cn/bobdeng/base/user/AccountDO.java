package cn.bobdeng.base.user;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountDO {
    private String id;
    private String name;
}

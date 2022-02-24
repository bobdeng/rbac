package cn.bobdeng.base.role;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RoleDO {
    private String id;
    private String name;
    private String functions;
}

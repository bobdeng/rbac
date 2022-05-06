package cn.bobdeng.base.role;

import cn.bobdeng.base.TenantId;
import com.google.gson.Gson;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
@Table(name = "rbac_role")
public class RoleDO {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String name;
    private String functions;
    private String tenantId;

    public RoleDO(TenantId tenantId, Role role) {
        this.tenantId = tenantId.id();
        this.functions = new Gson().toJson(role.functions());
        this.name = role.name();
    }
}

package cn.bobdeng.base.user;

public class TenantId {
    private String id;

    public TenantId(String id) {
        this.id = id;
    }

    public static TenantId of(String tenantId) {
        return new TenantId(tenantId);
    }

    public String getId() {
        return id;
    }
}
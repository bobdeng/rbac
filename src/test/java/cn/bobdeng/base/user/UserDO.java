package cn.bobdeng.base.user;

public class UserDO {
    private String id;
    private String status;

    public UserDO(User user) {
        this.id = user.id();
        this.status = user.status().status.getStatus();
    }

    public UserDO() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

package cn.bobdeng.base.user;

public enum UserStatusEnum {
    active("active"),suspend("suspend"),deleted("deleted");

    private String status;

    UserStatusEnum(String status) {

        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}

package cn.bobdeng.base.user;

import cn.bobdeng.base.user.Mobile;
import cn.bobdeng.base.user.User;

public class UserMobileDO {
    private String id;
    private String mobile;
    private String userId;

    public UserMobileDO(User user, Mobile userMobile) {
        this.userId = user.id();
        this.mobile = userMobile.number();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}

package cn.bobdeng.base;

public class UserMobile {
    private Mobile mobile;

    public UserMobile(Mobile mobile) {

        this.mobile = mobile;
    }

    public String number() {
        return mobile.number();
    }
}

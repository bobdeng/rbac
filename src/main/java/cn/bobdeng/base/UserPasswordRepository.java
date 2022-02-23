package cn.bobdeng.base;

public interface UserPasswordRepository {
    void save(User user, Password password);
}

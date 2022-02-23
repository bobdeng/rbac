package cn.bobdeng.base;

import cn.bobdeng.dummydao.DummyDao;

public class UserPasswordRepositoryImpl implements UserPasswordRepository {
    private DummyDao<PasswordDO, String> dummyDao;

    public UserPasswordRepositoryImpl(DummyDao<PasswordDO, String> dummyDao) {

        this.dummyDao = dummyDao;
    }

    @Override
    public void save(User user, Password password) {
        PasswordDO passwordDO=new PasswordDO();
        passwordDO.setPassword(password.encode());
        passwordDO.setId(user.id());
        dummyDao.save(passwordDO);
    }
}

package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.Optional;

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

    @Override
    public Optional<EncodedPassword> findByUser(User user) {
        return dummyDao.findById(user.id())
                .map(passwordDO -> new EncodedPassword(passwordDO.getPassword()));
    }
}

package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;
import lombok.Setter;

import java.util.Optional;

public class PasswordRepositoryImpl implements PasswordRepository {
    private final DummyDao<PasswordDO, Integer> passwordDao;
    @Setter
    private PasswordEncoder passwordEncoder;

    public PasswordRepositoryImpl(DummyDao<PasswordDO, Integer> passwordDao) {
        this.passwordDao = passwordDao;
    }

    @Override
    public void save(PasswordDO passwordDO) {
        passwordDao.save(passwordDO);
    }

    @Override
    public Optional<PasswordDO> findByUser(User user) {
        return passwordDao.findById(user.id());
    }

    @Override
    public PasswordEncoder passwordEncoder() {
        return passwordEncoder;
    }
}

package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class UserMobileRepositoryImpl implements UserMobileRepository {
    private final DummyDao<UserMobileDO, String> dummyDao;

    public UserMobileRepositoryImpl(DummyDao<UserMobileDO, String> dummyDao) {
        this.dummyDao = dummyDao;
    }

    @Override
    public void save(User user, Mobile userMobile) {
        dummyDao.save(new UserMobileDO(user, userMobile));
    }

    @Override
    public List<Mobile> findByUser(User user) {
        return dummyDao.all()
                .stream().filter(userMobileDO -> userMobileDO.getUserId().equals(user.id()))
                .map(userMobileDO -> new Mobile(userMobileDO.getMobile())).collect(Collectors.toList());
    }

    @Override
    public Optional<UserId> findByMobile(Mobile mobile) {
        return Optional.empty();
    }
}

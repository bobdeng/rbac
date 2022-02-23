package cn.bobdeng.base.user;

import cn.bobdeng.dummydao.DummyDao;
import cn.bobdeng.dummydao.UUIDGeneratorImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

public class UserMobileTest {
    private DummyDao<UserMobileDO, String> userMobileDao;

    @BeforeEach
    public void setup() {
        userMobileDao = new DummyDao<>(UserMobileDO.class, "id", new UUIDGeneratorImpl());
        Users.userMobileRepository = new UserMobileRepositoryImpl(userMobileDao);
    }

    @Test
    public void should_save_mobile_when_user_has_no_mobile() {
        User user = new User(new UserId("123456"));
        user.bindMobile(new Mobile("13333334444"));
        List<UserMobileDO> mobiles = userMobileDao.all();
        assertThat(mobiles.size(), is(1));
        assertThat(mobiles.get(0).getMobile(), is("13333334444"));
        assertThat(mobiles.get(0).getUserId(), is(user.id()));
    }
    @Test
    public void should_not_save_mobile_when_user_has_mobile() {
        String number = "13333334444";
        User user = new User(new UserId("123456"));
        Mobile mobile = new Mobile(number);
        UserMobileDO userMobileDO=new UserMobileDO(user,mobile);
        userMobileDao.save(userMobileDO);
        user.bindMobile(mobile);
        List<UserMobileDO> mobiles = userMobileDao.all();
        assertThat(mobiles.size(), is(1));
    }
}

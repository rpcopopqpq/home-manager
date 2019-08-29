package kr.co.nexsys.mcp.homemanager.user.service;

import kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator;
import kr.co.nexsys.mcp.homemanager.user.dao.UserDao;
import kr.co.nexsys.mcp.homemanager.user.dao.dvo.UserDvo;
import kr.co.nexsys.mcp.homemanager.user.service.vo.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator.defaultUserMrn;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;


@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class UserServiceTest {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserService userService;

    @Before
    public void init() {

    }

    @Test
    public void shouldCreateUser() {
        User u = TestDataGenerator.buildUser();
        userService.createUser(u);
        UserDvo actual = userDao.findOneByUserId(u.getUserId());

        assertThat(actual, notNullValue());
        assertThat(actual.getUserId(), equalTo(u.getUserId()));
        assertThat(actual.getMrn(), equalTo(defaultUserMrn));

    }

}

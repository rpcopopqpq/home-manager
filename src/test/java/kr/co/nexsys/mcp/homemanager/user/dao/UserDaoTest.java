package kr.co.nexsys.mcp.homemanager.user.dao;

import kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator;
import kr.co.nexsys.mcp.homemanager.user.dao.dvo.UserDvo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class UserDaoTest {
    @Autowired
    private UserDao dao;

    @Test
    public void shouldSave() {
        UserDvo dvo = TestDataGenerator.buildUserDvo();
        assertThat(dvo.getId(), nullValue());
        dao.saveAndFlush(dvo);
        assertThat(dvo.getId(), notNullValue());
    }


    @Test
    public void shouldFindById() {
        UserDvo origin = TestDataGenerator.buildUserDvo();
        dao.save(origin);

        Optional<UserDvo> actual = dao.findById(origin.getId());
        assertThat(actual.isPresent(), is(true));
        assertThat(actual.get().getId(), is(origin.getId()));
    }

    @Test
    public void shouldDelete() {
        UserDvo origin = dao.save(TestDataGenerator.buildUserDvo());
        assertThat(dao.findById(origin.getId()).isPresent(), is(true));
        dao.delete(origin);
        assertThat(dao.findById(origin.getId()).isPresent(), is(false));
    }


}

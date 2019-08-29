package kr.co.nexsys.mcp.homemanager.common.property;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator.defaultOrgMrn;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class AppPropertyTest {

    @Autowired
    private AppProperty appProperty;

    @Test
    public void shouldGetAppProperty() {
        assertThat(appProperty.getOrganization(), is(defaultOrgMrn));
    }
}

package kr.co.nexsys.mcp.homemanager.common.utils;

import kr.co.nexsys.mcp.homemanager.common.property.AppProperty;
import org.junit.Before;
import org.junit.Test;

import static kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator.defaultOrgMrn;
import static kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator.defaultUserId;
import static kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator.defaultUserMrnPrefix;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;


public class DataTrasformerTest {

    private DataTrasformer dataTrasformer;

    @Before
    public void setup() {
        AppProperty appProperty = new AppProperty();
        appProperty.setOrganization(defaultOrgMrn);
        dataTrasformer = new DataTrasformer(appProperty);
    }

    @Test
    public void shouldGetUserMrnPrefix() {
        assertThat(dataTrasformer.getUserMrnPrefix(), is(defaultUserMrnPrefix));
    }

    @Test
    public void shouldTransformUserId() {
        String expected = defaultUserMrnPrefix + ":" + defaultUserId;
        assertThat(dataTrasformer.toUserMrn(defaultUserId), is(expected));
    }
}

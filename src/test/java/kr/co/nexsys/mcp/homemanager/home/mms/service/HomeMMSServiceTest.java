package kr.co.nexsys.mcp.homemanager.home.mms.service;

import kr.co.nexsys.mcp.homemanager.home.mms.dao.HomeMMSDao;
import kr.co.nexsys.mcp.homemanager.home.mms.dao.dvo.HomeMMSDvo;
import kr.co.nexsys.mcp.homemanager.home.mms.service.vo.HomeMMS;
import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;


import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class HomeMMSServiceTest {

    @Mock
    private HomeMMSDao homeMMSDao;

    @Mock
    private MMSDao mmsDao;

    @InjectMocks
    private HomeMMSService homeMMSService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //HomeMMS 조회
    @Test
    public void shouldFindHomeMMS(){
        //given
        String mrn = "urn:mrn:smart:test:test:test:entity";
        HomeMMSDvo homeMMSDvo = HomeMMSDvo.builder()
                                .mrn("urn:mrn:smart:test:test:test:entity")
                                .mrn_mms("urn:mrn:smart:test:test:test:mmss")
                                .build();
        MMSDvo mmsDvo = MMSDvo.builder()
                                .mrn("urn:mrn:smart:test:test:test:mmss")
                                .ip("127.0.0.1")
                                .port(123)
                                .build();
        when(homeMMSDao.findOneHomeMMSByMrn(mrn)).thenReturn(homeMMSDvo);
        when(mmsDao.findOneMMSByMrn(homeMMSDvo.getMrn_mms())).thenReturn(mmsDvo);
        //when
        MMS mms = homeMMSService.findHomeMMS(homeMMSDvo.getMrn());
        //then
        assertThat(mms.getMrn(), equalTo(mmsDvo.getMrn()));
        assertThat(mms.getIp(), equalTo(mmsDvo.getIp()));
        assertThat(mms.getPort(), equalTo(mmsDvo.getPort()));
    }

    //HomeMMS 생성
    @Test
    public void shoudCreateHomeMMS(){
        //given
        HomeMMSDvo homeMMSDvo = HomeMMSDvo.builder()
                .mrn("urn:mrn:smart:test:test:test:entity")
                .mrn_mms("urn:mrn:smart:test:test:test:mmss")
                .build();
        HomeMMS homeMMS = HomeMMS.builder()
                .mrn("urn:mrn:smart:test:test:test:entity")
                .mrn_mms("urn:mrn:smart:test:test:test:mmss")
                .build();
        when(homeMMSDao.saveAndFlush(homeMMSDvo)).thenReturn(homeMMSDvo);
        //when
        HomeMMS result =homeMMSService.createHomeMMS(homeMMS);
        //then
        assertThat(result.getMrn(),equalTo(homeMMSDvo.getMrn()));
        assertThat(result.getMrn_mms(),equalTo(homeMMSDvo.getMrn_mms()));
    }

    //HomeMMS 수정
    @Test
    public void shouldModifyHomeMMS(){
        //given
        String mrn = "urn:mrn:smart:test:test:test:entity";
        HomeMMSDvo homeMMSDvo = HomeMMSDvo.builder()
                .mrn("urn:mrn:smart:test:test:test:entity")
                .mrn_mms("urn:mrn:smart:test:test:test:mmss")
                .build();
        HomeMMS homeMMSModify = HomeMMS.builder()
                .mrn_mms("urn:mrn:smart:test:test:test:modified")
                .build();
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:modified")
                .port(123)
                .build();
        when(mmsDao.findOneMMSByMrn(homeMMSModify.getMrn_mms())).thenReturn(mmsDvo);
        when(homeMMSDao.findOneHomeMMSByMrn(mrn)).thenReturn(homeMMSDvo);
        homeMMSDvo.setMrn_mms("urn:mrn:smart:test:test:test:modified");
        when(homeMMSDao.saveAndFlush(homeMMSDvo)).thenReturn(homeMMSDvo);
        //when
        HomeMMS result = homeMMSService.modifyHomeMMS(mrn,homeMMSModify);
        //then
        assertThat(result.getMrn_mms(),equalTo(homeMMSDvo.getMrn_mms()));
    }

}

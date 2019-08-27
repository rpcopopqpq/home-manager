package kr.co.nexsys.mcp.homemanager.mms.service;


import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class MMSSeviceTest {
    @Mock
    private MMSDao mmsDao;

    @InjectMocks
    private MMSService mmsService;

    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    //전체 MMS 조회
    @Test
    public void shouldFindAllMMSs(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                              .ip("127.0.0.1")
                              .mrn("urn:mrn:smart:test:test:test:test")
                              .port(123)
                              .createDate(LocalDateTime.now())
                              .build();
        when(mmsDao.findAll()).thenReturn(Lists.list(mmsDvo));
        //when
        List<MMS> list = mmsService.findAllMMSs();
        //then
        assertThat(list.get(0).getMrn(), equalTo(mmsDvo.getMrn()));
        assertThat(list.get(0).getIp(), equalTo(mmsDvo.getIp()));
        assertThat(list.get(0).getPort(), equalTo(mmsDvo.getPort()));
    }

    //MMS조회
    @Test
    public void shouldFindOneMMSByMrn(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                              .ip("127.0.0.1")
                              .mrn("urn:mrn:smart:test:test:test:test")
                              .port(123)
                              .createDate(LocalDateTime.now())
                              .build();
        when(mmsDao.findOneMMSByMrn(mmsDvo.getMrn())).thenReturn(mmsDvo);
        //when
        MMS mms = mmsService.findMMSByMrn(mmsDvo.getMrn());
        //then
        assertThat(mms.getMrn(), equalTo(mmsDvo.getMrn()));
        assertThat(mms.getIp(), equalTo(mmsDvo.getIp()));
        assertThat(mms.getPort(), equalTo(mmsDvo.getPort()));
    }

    //MMS생성
    @Test
    public void shouldCreateMMS(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        MMS mms = MMS.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();

        when(mmsDao.saveAndFlush(mmsDvo)).thenReturn(mmsDvo);
        //when
        MMS result = mmsService.createMMS(mms);
        //then
        assertThat(result.getMrn(), equalTo(mmsDvo.getMrn()));
        assertThat(result.getIp(), equalTo(mmsDvo.getIp()));
        assertThat(result.getPort(), equalTo(mmsDvo.getPort()));
    }
}

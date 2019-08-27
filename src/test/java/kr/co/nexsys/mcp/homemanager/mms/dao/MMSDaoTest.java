package kr.co.nexsys.mcp.homemanager.mms.dao;


import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertThat;

@RunWith(SpringRunner.class)
@ActiveProfiles("test")
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class MMSDaoTest {

    @Autowired
    private MMSDao mmsDao;

    //MMS 전체조회
    @Test
    public void shouldfindAll(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        List<MMSDvo> given = mmsDao.saveAll(Lists.list(mmsDvo));
        //when
        List<MMSDvo> result = mmsDao.findAll();
        //then
        assertThat(result.get(0).getMrn(),equalTo(given.get(0).getMrn()));
        assertThat(result.get(0).getIp(),equalTo(given.get(0).getIp()));
        assertThat(result.get(0).getPort(),equalTo(given.get(0).getPort()));
    }

    //MMS 조회
    @Test
    public void findOneMMSBymrn(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        MMSDvo given = mmsDao.saveAndFlush(mmsDvo);
        //when
        MMSDvo result = mmsDao.findOneMMSByMrn(mmsDvo.getMrn());
        //then
        assertThat(result.getMrn(),equalTo(given.getMrn()));
        assertThat(result.getIp(),equalTo(given.getIp()));
        assertThat(result.getPort(),equalTo(given.getPort()));
    }

    //MMS 생성
    @Test
    public void shouldSave(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        //when
        MMSDvo result = mmsDao.saveAndFlush(mmsDvo);
        //then
        assertThat(result.getMrn(), notNullValue());
        assertThat(result.getIp(), notNullValue());
        assertThat(result.getPort(), notNullValue());
    }

    //MMS 수정
    @Test
    public void shouldModify(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        MMSDvo given = mmsDao.saveAndFlush(mmsDvo);
        //when
        mmsDvo.setIp("192.168.56.100");
        mmsDvo.setPort(100);
        MMSDvo result = mmsDao.saveAndFlush(mmsDvo);
        //then
        assertNotEquals(result.getIp(),given.getIp());
        assertNotEquals(result.getPort(),given.getPort());
    }

    //MMS 삭제
    @Test
    public void shouldDelete(){
        //given
        MMSDvo mmsDvo = MMSDvo.builder()
                .ip("127.0.0.1")
                .mrn("urn:mrn:smart:test:test:test:test2")
                .port(123)
                .build();
        mmsDao.saveAndFlush(mmsDvo);
        //when
        mmsDao.delete(mmsDvo);
        //then
        assertThat(mmsDao.findOneMMSByMrn(mmsDvo.getMrn()),nullValue());
    }


}

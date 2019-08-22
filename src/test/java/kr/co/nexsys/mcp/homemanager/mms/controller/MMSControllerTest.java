package kr.co.nexsys.mcp.homemanager.mms.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(MMSControllerTest.class)
public class MMSControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Test
    public void findMMSByMRN(){
        //TODO : MRN으로부터 MMS 조회
    }

    @Test
    public void createMMS(){
        //TODO : MMS 정보 생성
    }

    @Test
    public void modifyMMSByMRN(){
        //TODO : MMS 정보 수정
    }

    @Test
    public void removeMMSByMRN(){
        //TODO : MMS 정보 삭제
    }


}

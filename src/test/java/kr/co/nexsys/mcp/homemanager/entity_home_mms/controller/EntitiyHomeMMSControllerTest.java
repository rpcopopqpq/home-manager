package kr.co.nexsys.mcp.homemanager.entity_home_mms.controller;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@WebMvcTest(EntitiyHomeMMSControllerTest.class)
public class EntitiyHomeMMSControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    public void findHomeMMSByMRN(){
        //TODO: MRN으로부터 HomeMMS 조회
    }

    @Test
    public void findMRNsByMRN_MMS(){
        //TODO: MRN_MMS로부터 MRN 여러건 조회
    }

    @Test
    public void createHomeMMS(){
        //TODO : HomeMMS 생성
    }

    @Test
    public void modifyHomeMMSByMRN(){
        //TODO : MRN으로부터 HomeMMS 수정
    }

    @Test
    public void removeHomeMMSByMRN(){
        //TODO : MRN으로부터 HomeMMS 삭제
    }


}

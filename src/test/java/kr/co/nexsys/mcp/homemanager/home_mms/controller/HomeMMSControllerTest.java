package kr.co.nexsys.mcp.homemanager.home_mms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.nexsys.mcp.homemanager.home_mms.service.HomeMMSService;
import kr.co.nexsys.mcp.homemanager.home_mms.service.vo.HomeMMS;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(HomeMMSController.class)
public class HomeMMSControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;
    @MockBean
    HomeMMSService homeMMSService;
    @MockBean
    MMS mms;

    @Test
    public void findHomeMMS() throws Exception{
        //given
        String mrn = "urn:mrn:smart:test:test:test:test";
          mms = MMS.builder()
                                .mrn("urn:mrn:smart:test:test:test:test")
                                .ip("127.0.0.1")
                                .port(123)
                                .build();
        given(homeMMSService.findHomeMMS(mrn)).willReturn(mms);
        //when
        ResultActions resultActions =mockMvc.perform(get("/entity/"+mrn+"/home-mms")).andDo(print());
        //then
        resultActions.andExpect(status().isOk())
                     .andExpect(jsonPath("$.mrn",equalTo(mrn)))
                     .andExpect(jsonPath("$.homeMmsDto.mrn",equalTo(mms.getMrn())))
                     .andExpect(jsonPath("$.homeMmsDto.ip",equalTo(mms.getIp())))
                     .andExpect(jsonPath("$.homeMmsDto.port",equalTo(mms.getPort())));
    }

}

package kr.co.nexsys.mcp.homemanager.home_mms.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSDto;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSModifyReqDto;
import kr.co.nexsys.mcp.homemanager.home_mms.service.HomeMMSService;
import kr.co.nexsys.mcp.homemanager.home_mms.service.vo.HomeMMS;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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


    @Test
    public void findHomeMMS() throws Exception{
        //given
        String mrn = "urn:mrn:smart:test:test:test:test";
          MMS mms = MMS.builder()
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

    @Test
    public void createHomeMMS() throws Exception{
        //given
        HomeMMS homeMMS = HomeMMS.builder()
                        .mrn("urn:mrn:smart:test:test:test:test")
                        .mrn_mms("urn:mrn:smart:test:test:test:mmss")
                        .build();
        given(homeMMSService.createHomeMMS(homeMMS)).willReturn(homeMMS);
        //when
        ResultActions resultActions = mockMvc.perform(post("/entity/home-mms")
                                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                                            .content(objectMapper.writeValueAsString(homeMMS)))
                                            .andDo(print());
        //then
        resultActions.andExpect(status().isOk())
                      .andExpect(jsonPath("$.homeMmsDto.mrn",equalTo(homeMMS.getMrn())))
                      .andExpect(jsonPath("$.homeMmsDto.mrn_mms",equalTo(homeMMS.getMrn_mms())));
    }

    @Test
    public void modifyhomeMMS() throws Exception{
        //given
        String mrn = "urn:mrn:smart:test:test:test:mmss";
        //데이터 변경 전
        HomeMMS homeMMSModifyReqDto = HomeMMS.builder()
                .mrn_mms("urn:mrn:smart:test:test:test:Modify")
                .build();
        //데이터 변경 후
        HomeMMS homeMMSModified = HomeMMS.builder()
                .mrn("urn:mrn:smart:test:test:test:mmss")
                .mrn_mms("urn:mrn:smart:test:test:test:Modify")
                .build();
        given(homeMMSService.modifyHomeMMS(mrn,homeMMSModifyReqDto)).willReturn(homeMMSModified);
        //when
        ResultActions resultActions = mockMvc.perform(put("/entity/"+mrn+"/home-mms")
                                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                                            .content(objectMapper.writeValueAsString(homeMMSModifyReqDto)))
                                            .andDo(print());
        //then
        resultActions.andExpect(status().isOk())
                    .andExpect(jsonPath("$.homeMmsDto.mrn",equalTo(homeMMSModified.getMrn())))
                    .andExpect(jsonPath("$.homeMmsDto.mrn_mms",equalTo(homeMMSModified.getMrn_mms())));
    }

    @Test
    public void deletehomeMMS() throws Exception{
        //given
        String mrn = "urn:mrn:smart:test:test:test:mmss";
        String mrn_mms = "urn:mrn:smart:test:test:test:mmss";
        //when
        ResultActions resultActions = mockMvc.perform(delete("/entity/"+mrn+"/home-mms/"+mrn_mms)).andDo(print());
        //then
        resultActions.andExpect(status().isOk());
    }

}

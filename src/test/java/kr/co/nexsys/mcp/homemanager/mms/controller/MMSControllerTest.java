package kr.co.nexsys.mcp.homemanager.mms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSRequestDto;
import kr.co.nexsys.mcp.homemanager.mms.service.MMSService;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.assertj.core.util.Lists;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.LocalDateTime;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(MMSController.class)
public class MMSControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    MMSService mmsService;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void findMMSByMRN() throws Exception{
        //TODO : MRN으로부터 MMS 조회
        String mrn = "urn:home:mms:test:test-0.0.1v";

        MMSRequestDto mmsRequestDto =
                MMSRequestDto.builder()
                    .mrn(mrn)
                    .build();

        MMS mms = MMS.builder()
                .mrn(mrn)
                .ip("127.0.0.1")
                .port(1101)
                .createDate(LocalDateTime.now())
                .build();

        //given
        given(mmsService.findMMSByMRN(mrn)).willReturn(Lists.list(mms));

        mockMvc.perform(
                post("/mms/find")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(mmsRequestDto)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mmsinfo.[0].mrn",equalTo(mms.getMrn())))
                .andExpect(jsonPath("$.mmsinfo.[0].ip",equalTo(mms.getIp())))
                .andExpect(jsonPath("$.mmsinfo.[0].port",equalTo(mms.getPort())))
                .andExpect(jsonPath("$.mmsinfo.[0].createDate",equalTo(mms.getCreateDate().toString())))
                .andExpect(jsonPath("$.mmsinfo.[0].updateDate",equalTo(null)));
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

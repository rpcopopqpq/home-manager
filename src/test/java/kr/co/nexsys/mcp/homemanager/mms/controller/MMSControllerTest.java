package kr.co.nexsys.mcp.homemanager.mms.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSResponseDto;
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
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
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

    //MRN으로부터 MMS 조회
    @Test
    public void findMMS() throws Exception{
        String mrn = "urn:home:mms:test:test-0.0.1v";
        MMS mms = MMS.builder()
                .mrn(mrn)
                .ip("127.0.0.1")
                .port(1101)
                .build();

        //given
        given(mmsService.findMMSByMrn(mrn)).willReturn(Lists.list(mms));
        //when,then
        mockMvc.perform(
                get("/mms/"+mrn)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString("{}")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mmsinfo.[0].mrn",equalTo(mms.getMrn())))
                .andExpect(jsonPath("$.mmsinfo.[0].ip",equalTo(mms.getIp())))
                .andExpect(jsonPath("$.mmsinfo.[0].port",equalTo(mms.getPort())))
                //.andExpect(jsonPath("$.mmsinfo.[0].createDate",equalTo(mms.getCreateDate().toString())))
                .andExpect(jsonPath("$.mmsinfo.[0].updateDate",equalTo(null)));
    }

    //전체 MMS 조회
    @Test
    public void findAllMMSs() throws Exception{
        String mrn = "urn:home:mms:test:test-0.0.1v";
        MMS mms = MMS.builder()
                .mrn(mrn)
                .ip("127.0.0.1")
                .port(1101)
                .build();

        //given
        given(mmsService.findAllMMSs()).willReturn(Lists.list(mms));
        //when,then
        mockMvc.perform(
                get("/mms")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString("{}")))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.mmsinfo.[0].mrn",equalTo(mms.getMrn())))
                .andExpect(jsonPath("$.mmsinfo.[0].ip",equalTo(mms.getIp())))
                .andExpect(jsonPath("$.mmsinfo.[0].port",equalTo(mms.getPort())))
                //.andExpect(jsonPath("$.mmsinfo.[0].createDate",equalTo(mms.getCreateDate().toString())))
                .andExpect(jsonPath("$.mmsinfo.[0].updateDate",equalTo(null)));
    }

    //MMS 생성
    @Test
    public void createMMS() throws Exception{
        List<MMSDto> result = new ArrayList<>();
        MMSDto mmsDto = MMSDto.builder()
                //.mrn("urn:home:mms:test:test-0.0.1v")
                .ip("127.0.0.1")
                .port(1101)
                .createDate(LocalDateTime.now())
                .build();
        result.add(mmsDto);

        MMS mms = MMS.builder()
                .mrn("urn:home:mms:test:test-0.0.1v")
                .ip("127.0.0.1")
                .port(1101)
                .createDate(LocalDateTime.now())
                .build();

        //given
        //given(mmsService.createMMS(result)).willReturn(null);
        //when,then
        mockMvc.perform(
                post("/mms")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(result)))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.mmsinfo.[0].mrn",equalTo(mms.getMrn())))
//                .andExpect(jsonPath("$.mmsinfo.[0].ip",equalTo(mms.getIp())))
//                .andExpect(jsonPath("$.mmsinfo.[0].port",equalTo(mms.getPort())))
//                //.andExpect(jsonPath("$.mmsinfo.[0].createDate",equalTo(mms.getCreateDate().toString())))
//                .andExpect(jsonPath("$.mmsinfo.[0].updateDate",equalTo(null)));
    }

    //MMS 수정
    @Test
    public void modifyMMS() throws Exception{
        String mrn = "urn:home:mms:test:test-0.0.2v";
        MMS mms = MMS.builder()
                .mrn(mrn)
                .ip("127.0.0.1")
                .port(1101)
                .createDate(LocalDateTime.now())
                .build();

        //given
      //  given(mmsService.modifyMMS(mrn,mms)).willReturn(Lists.list(mms));
        //when,then
        mockMvc.perform(
                patch("/mms/"+mrn)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(mms)))
                .andDo(print())
                .andExpect(status().isOk());
//                .andExpect(jsonPath("$.mmsinfo.[0].mrn",equalTo(mrn)))
//                .andExpect(jsonPath("$.mmsinfo.[0].ip",equalTo(mms.getIp())))
//                .andExpect(jsonPath("$.mmsinfo.[0].port",equalTo(mms.getPort())))
//                //.andExpect(jsonPath("$.mmsinfo.[0].createDate",equalTo(mms.getCreateDate().toString())))
//                .andExpect(jsonPath("$.mmsinfo.[0].updateDate",equalTo(null)));

    }

    @Test
    public void removeMMS() throws Exception{
        boolean result = true;
        String mrn = "urn:home:mms:test:test-0.0.2v";
        MMS mms = MMS.builder()
                .mrn("urn:home:mms:test:test-0.0.2v")
                .ip("127.0.0.1")
                .port(1101)
                .createDate(LocalDateTime.now())
                .build();

        //given
      //  given(mmsService.removeMMS(mrn)).willReturn(true);
        //when,then
        mockMvc.perform(
                delete("/mms/" + mrn)
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString("{}")))
                .andDo(print())
                .andExpect(status().isOk());
                //.andExpect(jsonPath("$msg",equalTo("Delete Success!")))

    }


}

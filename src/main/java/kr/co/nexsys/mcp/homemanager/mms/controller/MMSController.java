package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSRequestDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSResponseDto;
import kr.co.nexsys.mcp.homemanager.mms.service.MMSService;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/mms")
public class MMSController {

    private MMSService mmsService;

    @Autowired
    private MMSController(MMSService mmsService ) {this.mmsService = mmsService;}

    @RequestMapping(value = "/{mrn}",method = RequestMethod.GET)
    public MMSResponseDto findMMS(@PathVariable String mrn){
        log.debug("mrn :" + mrn);

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.findMMSByMRN(mrn).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public MMSResponseDto createMMS(@RequestBody MMSRequestDto requestDto){
        log.debug("insert Data :"+requestDto.toString());

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.createMMS(MMSController.valueOf(requestDto)).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();

    }


    private static MMS valueOf(MMSRequestDto requestDto){
        return MMS.builder()
                .mrn(requestDto.getMrn())
                .ip(requestDto.getIp())
                .port(requestDto.getPort())
                .createDate(requestDto.getCreateDate())
                .updateDate(requestDto.getUpdateDate())
                .build();
    }

    private static MMSDto valueOf(MMS mms){
        return MMSDto.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .createDate(mms.getCreateDate())
                .updateDate(mms.getUpdateDate())
                .build();
    }

}

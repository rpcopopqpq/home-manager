package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSCreateReqDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSModifyReqDto;
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
                .MMSInfo(mmsService.findMMSByMrn(mrn).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }

    @RequestMapping(method = RequestMethod.POST)
    public MMSResponseDto createMMS(@RequestBody MMSCreateReqDto requestDto){
        log.debug("insert Data :" + requestDto.toString());

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.createMMS(requestDto).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();

    }

    @RequestMapping(value ="/{mrn}" ,method = RequestMethod.PATCH)
    public MMSResponseDto modifyMMS(@PathVariable String mrn,
                                    @RequestBody MMSModifyReqDto requestDto){
        log.debug("update Data :" + requestDto +", mrn :" + mrn);

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.modifyMMS(mrn,MMSController.valueOf(requestDto)).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }

    @RequestMapping(value = "/{mrn}", method = RequestMethod.DELETE)
    public String removeMMS(@PathVariable String mrn){
        log.debug("delete mrn :" + mrn);

        String msg = "";
       boolean result = mmsService.removeMMS(mrn);

       if(result){
           msg = "Delete Success!";
       }else{
           msg = "Delete Fail!";
       }
       return msg;
    }


    private static MMS valueOf(MMSModifyReqDto requestDto){
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

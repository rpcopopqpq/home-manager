package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.exception.NullResultException;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSRequestDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSResponseDto;
import kr.co.nexsys.mcp.homemanager.mms.service.MMSService;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/mms")
public class MMSController {

    private MMSService mmsService;

    @Autowired
    private MMSController(MMSService mmsService ) {this.mmsService = mmsService;}

    @GetMapping(value = "/{mrn}")
    public MMSResponseDto findMMS(@PathVariable String mrn){
        log.debug("mrn :" + mrn);

        List<MMS> test= new ArrayList<>();
        test= mmsService.findMMSByMrn(mrn);

        if(test.isEmpty()){
            throw new NullResultException();
        }else{
            return MMSResponseDto.builder()
                    .MMSInfo(mmsService.findMMSByMrn(mrn).stream()
                            .map(MMSController::valueOf)
                            .collect(Collectors.toList())).build();
        }
    }

    @GetMapping
    public MMSResponseDto findAllMMSs(){
        log.debug("mmsList all find");

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.findAllMMSs().stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }


    @PostMapping
    public MMSResponseDto createMMS(@RequestBody List<MMSDto> requestDto){
        log.debug("insert Data :" + requestDto.toString());

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.createMMS(requestDto).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();

    }

    @PatchMapping(value = "/{mrn}")
    public MMSResponseDto modifyMMS(@PathVariable String mrn,
                                    @RequestBody MMSRequestDto requestDto){
        log.debug("update Data :" + requestDto +", mrn :" + mrn);

        return MMSResponseDto.builder()
                .MMSInfo(mmsService.modifyMMS(mrn,MMSController.valueOf(requestDto)).stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }

    @DeleteMapping(value = "/{mrn}")
    public ResponseEntity removeMMS(@PathVariable String mrn){
        log.debug("delete mrn :" + mrn);

        String msg = "";
       boolean result = mmsService.removeMMS(mrn);

       if(result){
           msg ="Delete Success!";
       }else{
           msg ="{\"msg\":\"Delete Fail!\"}";
       }
       return ResponseEntity.ok(msg);
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

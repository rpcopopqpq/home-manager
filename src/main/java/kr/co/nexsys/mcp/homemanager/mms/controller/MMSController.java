package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.exception.DatabaseException;
import kr.co.nexsys.mcp.homemanager.exception.NullResultException;
import kr.co.nexsys.mcp.homemanager.exception.SystemException;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSRequestDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSResponseDto;
import kr.co.nexsys.mcp.homemanager.mms.service.MMSService;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @GetMapping()
    public MMSResponseDto findAllMMSs(){
        log.debug("mmsList all find");

        List<MMS> result = new ArrayList<>();
        try {
             result = mmsService.findAllMMSs();
        }catch(Exception e){
            throw new SystemException();
        }
        return MMSResponseDto.builder()
                    .MMSInfo(result.stream()
                            .map(MMSController::valueOf)
                            .collect(Collectors.toList())).build();
    }

    @GetMapping(value = "/{mrn}")
    public MMSResponseDto findMMS(@PathVariable String mrn){
        log.debug("mrn :" + mrn);

        List<MMS> result = mmsService.findMMSByMrn(mrn);

        return MMSResponseDto.builder()
                .MMSInfo(result.stream()
                        .map(MMSController::valueOf)
                        .collect(Collectors.toList())).build();
    }

    @PostMapping()
    public MMSResponseDto createMMS(@RequestBody @Valid MMSRequestDto requestDto){
        log.debug("insert Data :" + requestDto.toString());

        mmsService.createMMS(MMSController.valueOf(requestDto));

        return MMSResponseDto.builder()
                .message("Create Sucess").build();
    }

    @PatchMapping(value = "/{mrn}")
    public MMSResponseDto modifyMMS(@PathVariable String mrn,
                                    @RequestBody @Valid MMSRequestDto requestDto){
        log.debug("update Data :" + requestDto +", mrn :" + mrn);

        mmsService.modifyMMS(mrn,MMSController.valueOf(requestDto));

        return MMSResponseDto.builder()
                .message("Modify Success").build();
    }

    @DeleteMapping(value = "/{mrn}")
    public MMSResponseDto removeMMS(@PathVariable String mrn){
        log.debug("delete mrn :" + mrn);

        mmsService.removeMMS(mrn);

       return MMSResponseDto.builder()
                            .message("Delete Success").build();
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

package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.*;
import kr.co.nexsys.mcp.homemanager.mms.service.MMSService;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.stream.Collectors;

@Slf4j
@RestController
@RequestMapping("/mms")
public class MMSController {

    private MMSService mmsService;

    @Autowired
    private MMSController(MMSService mmsService ) {this.mmsService = mmsService;}

    @GetMapping
    public ResponseEntity<MMSFindAllResDto> findAllMMSs(){
        log.debug("mmsList all find");

        return ResponseEntity.ok(MMSFindAllResDto.builder()
                                    .MMSList(mmsService.findAllMMSs().stream()
                                        .map(MMSController::valueOf)
                                        .collect(Collectors.toList())).build());
    }

    @GetMapping(value = "/{mrn}")
    public ResponseEntity<MMSFindOneResDto> findMMS(@PathVariable("mrn") String mrn){
        log.debug("find mms, mrn :" + mrn);

        return ResponseEntity.ok(MMSFindOneResDto.builder()
                                    .mmsDto(MMSController.valueOf(mmsService.findMMSByMrn(mrn)))
                                    .build());
    }

    @PostMapping
    public ResponseEntity<MMSCreateResDto> createMMS(@RequestBody @Valid MMSCreateReqDto mmsCreateReqDto){
        log.debug("insert Data :" + mmsCreateReqDto.toString());

        return ResponseEntity.ok(MMSCreateResDto.builder()
                                    .mmsDto(MMSController.valueOf(mmsService.createMMS(MMSController.valueOf(mmsCreateReqDto))))
                                    .build());
    }

    @PutMapping(value = "/{mrn}")
    public ResponseEntity<MMSModifyResDto> modifyMMS(@PathVariable("mrn") String mrn,
                                      @RequestBody @Valid MMSModifyReqDto mmsModifyReqDto){
        log.debug("update Data :" + mmsModifyReqDto +", mrn :" + mrn);

        return ResponseEntity.ok(MMSModifyResDto.builder()
                                    .mmsDto(MMSController.valueOf(mmsService.modifyMMS(mrn,MMSController.valueOf(mmsModifyReqDto))))
                                    .build());

    }

    @DeleteMapping(value = "/{mrn}")
    public ResponseEntity<?> removeMMS(@PathVariable String mrn){
        log.debug("delete mrn :" + mrn);

        mmsService.removeMMS(mrn);
       return ResponseEntity.ok("OK");
    }


    private static MMS valueOf(MMSCreateReqDto mmsCreateReqDto){
        return MMS.builder()
                .mrn(mmsCreateReqDto.getMrn())
                .ip(mmsCreateReqDto.getIp())
                .port(mmsCreateReqDto.getPort())
                .build();
    }

    private static MMS valueOf(MMSModifyReqDto mmsModifyReqDto){
        return MMS.builder()
                .ip(mmsModifyReqDto.getIp())
                .port(mmsModifyReqDto.getPort())
                .build();
    }

    private static MMSDto valueOf(MMS mms){
        return MMSDto.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .build();
    }

}

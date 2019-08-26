package kr.co.nexsys.mcp.homemanager.mms.controller;

import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSFindAllResDto;
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
    public ResponseEntity<MMSDto> findMMS(@PathVariable String mrn){
        log.debug("find mms, mrn :" + mrn);

        MMS result = mmsService.findMMSByMrn(mrn);
        return ResponseEntity.ok(MMSDto.builder().mrn(result.getMrn())
                                                 .ip(result.getIp())
                                                 .port(result.getPort())
                                                 .build());
    }

    @PostMapping
    public ResponseEntity<MMSDto> createMMS(@RequestBody @Valid MMSDto mmsDto){
        log.debug("insert Data :" + mmsDto.toString());

        MMS result = mmsService.createMMS(MMSController.valueOf(mmsDto));
        return ResponseEntity.ok(MMSDto.builder().mrn(result.getMrn())
                                                 .ip(result.getIp())
                                                 .port(result.getPort())
                                                 .build());
    }

    @PutMapping(value = "/{mrn}")
    public ResponseEntity<?> modifyMMS(@PathVariable String mrn,
                                      @RequestBody @Valid MMSDto mmsDto){
        log.debug("update Data :" + mmsDto +", mrn :" + mrn);

        mmsService.modifyMMS(mrn,MMSController.valueOf(mmsDto));
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping(value = "/{mrn}")
    public ResponseEntity<?> removeMMS(@PathVariable String mrn){
        log.debug("delete mrn :" + mrn);

        mmsService.removeMMS(mrn);
       return ResponseEntity.ok("OK");
    }


    private static MMS valueOf(MMSDto mmsDto){
        return MMS.builder()
                .mrn(mmsDto.getMrn())
                .ip(mmsDto.getIp())
                .port(mmsDto.getPort())
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

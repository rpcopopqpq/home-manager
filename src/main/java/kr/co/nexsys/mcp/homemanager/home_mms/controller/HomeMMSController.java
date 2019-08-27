package kr.co.nexsys.mcp.homemanager.home_mms.controller;


import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSDto;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSFindResDto;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSModifyReqDto;
import kr.co.nexsys.mcp.homemanager.home_mms.service.HomeMMSService;
import kr.co.nexsys.mcp.homemanager.mms.controller.MMSController;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/entity")
public class HomeMMSController {

    @Autowired
    private HomeMMSService homeMMSService;

    public HomeMMSController(HomeMMSService homeMMSService){this.homeMMSService=homeMMSService;}

    @GetMapping("/{mrn}/home-mms")
    public ResponseEntity<?> findHomeMMS(@PathVariable("mrn") String mrn){
        //TODO : entity mrn의 Home MMS 조회
        return ResponseEntity.ok(HomeMMSFindResDto.builder()
                                    .mrn(mrn)
                                    .homeMmsDto(HomeMMSController.valueOf(homeMMSService.findHomeMMS(mrn)))
                                    .build());
    }

    @PostMapping("/home-mms")
    public ResponseEntity<?> createHomeMMS(@RequestBody HomeMMSDto homeMMSDto){
        //TODO : entity mrn의 Home MMS 생성
        return ResponseEntity.ok("OK");
    }

    @PutMapping("/{mrn}/home-mms")
    public ResponseEntity<?> modifyHomeMMS(@PathVariable("mrn") String mrn,
                                           @RequestBody HomeMMSModifyReqDto homeMMSModifyReqDto){
        //TODO : entity mrn의 Home MMS 수정
        return ResponseEntity.ok("OK");
    }

    @DeleteMapping("/{mrn}/home-mms/{mmsMrn}")
    public ResponseEntity<?> deleteHomeMMS(@PathVariable("mrn") String mrn,
                                           @PathVariable("mmsMrn") String mmsMrn){
        //TODO : entity mrn의 Home MMS 삭제
        return ResponseEntity.ok("OK");
    }

    private static MMSDto valueOf(MMS mms){
        return MMSDto.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .build();
    }

}

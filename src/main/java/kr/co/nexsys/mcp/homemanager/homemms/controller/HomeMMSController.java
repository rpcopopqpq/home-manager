package kr.co.nexsys.mcp.homemanager.homemms.controller;


import kr.co.nexsys.mcp.homemanager.homemms.controller.dto.HomeMMSDto;
import kr.co.nexsys.mcp.homemanager.homemms.controller.dto.HomeMMSModifyReqDto;
import kr.co.nexsys.mcp.homemanager.homemms.service.HomeMMSService;
import kr.co.nexsys.mcp.homemanager.homemms.service.vo.HomeMMS;
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
        return ResponseEntity.ok("OK");
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


}

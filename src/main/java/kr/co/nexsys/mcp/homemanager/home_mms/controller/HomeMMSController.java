package kr.co.nexsys.mcp.homemanager.home_mms.controller;


import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.*;
import kr.co.nexsys.mcp.homemanager.home_mms.service.HomeMMSService;
import kr.co.nexsys.mcp.homemanager.home_mms.service.vo.HomeMMS;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/entity")
public class HomeMMSController {

    @Autowired
    private HomeMMSService homeMMSService;

    public HomeMMSController(HomeMMSService homeMMSService){this.homeMMSService=homeMMSService;}

    @GetMapping("/{mrn}/home-mms")
    public ResponseEntity<HomeMMSFindResDto> findHomeMMS(@PathVariable("mrn") String mrn){
        return ResponseEntity.ok(HomeMMSFindResDto.builder()
                                    .mrn(mrn)
                                    .homeMmsDto(HomeMMSController.valueOf(homeMMSService.findHomeMMS(mrn)))
                                    .build());
    }

    @PostMapping("/home-mms")
    public ResponseEntity<HomeMMSCreateResDto> createHomeMMS(@RequestBody @Valid HomeMMSCreateReqDto homeMMSCreateReqDto){
        return ResponseEntity.ok(HomeMMSCreateResDto.builder()
                                    .homeMmsDto(HomeMMSController.valueOf(homeMMSService.createHomeMMS(HomeMMSController.valueOf(homeMMSCreateReqDto))))
                                    .build());
    }

    @PutMapping("/{mrn}/home-mms")
    public ResponseEntity<HomeMMSModifyResDto> modifyHomeMMS(@PathVariable("mrn") String mrn,
                                                       @RequestBody @Valid HomeMMSModifyReqDto homeMMSModifyReqDto){
        return ResponseEntity.ok(HomeMMSModifyResDto.builder()
                                    .homeMmsDto(HomeMMSController.valueOf(homeMMSService.modifyHomeMMS(mrn,HomeMMSController.valueOf(homeMMSModifyReqDto))))
                                    .build());
    }

    @DeleteMapping("/{mrn}/home-mms")
    public ResponseEntity<?> deleteHomeMMS(@PathVariable("mrn") String mrn){
        homeMMSService.deleteHomeMMS(mrn);
        return ResponseEntity.ok("DELETE OK");
    }

    private static MMSDto valueOf(MMS mms){
        return MMSDto.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .build();
    }

    private static HomeMMSDto valueOf(HomeMMS homeMMS){
        return HomeMMSDto.builder()
                    .mrn(homeMMS.getMrn())
                    .mrn_mms(homeMMS.getMrn_mms())
                    .build();
    }
    private static HomeMMS valueOf(HomeMMSCreateReqDto homeMMSCreateReqDto){
        return HomeMMS.builder()
                    .mrn(homeMMSCreateReqDto.getMrn())
                    .mrn_mms(homeMMSCreateReqDto.getMrn_mms())
                    .build();
    }
    private static HomeMMS valueOf(HomeMMSModifyReqDto homeMMSModifyReqDto){
        return HomeMMS.builder()
                .mrn_mms(homeMMSModifyReqDto.getMrn_mms())
                .build();
    }

}

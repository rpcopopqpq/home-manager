package kr.co.nexsys.mcp.homemanager.homemms.service;

import kr.co.nexsys.mcp.homemanager.homemms.dao.HomeMMSDao;
import kr.co.nexsys.mcp.homemanager.homemms.service.vo.HomeMMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class HomeMMSService {

    @Autowired
    private HomeMMSDao homeMMSDao;

    public HomeMMSService(HomeMMSDao homeMMSDao) {this.homeMMSDao = homeMMSDao;}

    //homeMMS 조회
    public HomeMMS findHomeMMS(String mrn){
        HomeMMS homeMMS = new HomeMMS();
        //TODO : entity mrn의 Home MMS 조회
        /*
        오류 처리 방식
        HM03002N : MRN이 존재하지 않습니다.
        HM02002R : 데이터 형식이 틀립니다.
         */

        return homeMMS;
    }

    //homeMMS 생성
    public HomeMMS createHomeMMS(HomeMMS homeMMS){
        HomeMMS result = new HomeMMS();
        //TODO : entity mrn의 Home MMS 생성
        /*
        오류 처리 방식
        HM02001R : 필수 입력 값이 누락 되었습니다.
        HM02002R : 데이터 형식이 틀립니다.
         */
        return result;
    }

    //homeMMS 수정
    public void modifyHomeMMS(String mrn, HomeMMS homeMMS){
        //TODO : entity mrn의 Home MMS 수정
        /*
        오류 처리 방식
        HM02001R : 필수 입력 값이 누락 되었습니다.
        HM02002R : 데이터 형식이 틀립니다.
         */
    }

    //homeMMS 삭제
    public void deleteHomeMMS(String mrn, String mmsMrn){
        //TODO : entity mrn의 Home MMS 삭제
        /*
        오류 처리 방식
        HM03002N : MRN이 존재하지 않습니다.
        HM02002R : 데이터 형식이 틀립니다.
         */
    }
}

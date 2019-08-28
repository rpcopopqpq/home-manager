package kr.co.nexsys.mcp.homemanager.home_mms.service;

import kr.co.nexsys.mcp.homemanager.exception.NullResultException;
import kr.co.nexsys.mcp.homemanager.exception.SystemException;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSCreateReqDto;
import kr.co.nexsys.mcp.homemanager.home_mms.controller.dto.HomeMMSModifyReqDto;
import kr.co.nexsys.mcp.homemanager.home_mms.dao.HomeMMSDao;
import kr.co.nexsys.mcp.homemanager.home_mms.dao.dvo.HomeMMSDvo;
import kr.co.nexsys.mcp.homemanager.home_mms.service.vo.HomeMMS;
import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.Null;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class HomeMMSService {

    @Autowired
    private HomeMMSDao homeMMSDao;

    @Autowired
    private MMSDao mmsDao;

    public HomeMMSService(HomeMMSDao homeMMSDao) {this.homeMMSDao = homeMMSDao;}

    //homeMMS 조회
    public MMS findHomeMMS(String mrn){
        try{
             return HomeMMSService.valueOf(mmsDao.findOneMMSByMrn(mrn));
        }catch(NullPointerException n){
            throw new NullResultException("HM03002N");
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //homeMMS 생성
    public HomeMMS createHomeMMS(HomeMMS homeMMS){
       try{
           return HomeMMSService.valueOf(homeMMSDao.saveAndFlush(HomeMMSService.valueOf(homeMMS)));
       }catch(NullPointerException n){
           throw new NullResultException("HM03002N");
       }catch(Exception e){
           throw new SystemException();
       }
    }

    //homeMMS 수정
    public HomeMMS modifyHomeMMS(String mrn, HomeMMS homeMMS){
        try{
            MMSDvo mmsDvo =mmsDao.findOneMMSByMrn(homeMMS.getMrn_mms());
            HomeMMSDvo homeMMSDvo =homeMMSDao.findOneHomeMMS(mrn);
            //entity mrn이나 mms mrn 중 둘중 하나라도 존재하지 않으면 HM03002N 에러 출력
            homeMMSDvo.setMrn_mms(homeMMS.getMrn_mms());
            return HomeMMSService.valueOf(homeMMSDao.saveAndFlush(HomeMMSService.valueOf(homeMMS)));
        }catch(NullPointerException n){
            throw new NullResultException("HM03002N");
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //homeMMS 삭제
    public void deleteHomeMMS(String mrn){
        try{
            HomeMMSDvo homeMMSDvo =homeMMSDao.findOneHomeMMS(mrn);
            homeMMSDao.delete(homeMMSDvo);
        }catch(NullPointerException n){
            throw new NullResultException("HM03002N");
        }catch(Exception e){
            throw new SystemException();
        }
    }

    private static MMS valueOf(MMSDvo mmsDvo){
        return MMS.builder()
                .mrn(mmsDvo.getMrn())
                .ip(mmsDvo.getIp())
                .port(mmsDvo.getPort())
                .build();
    }
    private static HomeMMSDvo valueOf(HomeMMS homeMMS){
        return HomeMMSDvo.builder()
                .mrn(homeMMS.getMrn())
                .mrn_mms(homeMMS.getMrn_mms())
                .build();
    }
    private static HomeMMS valueOf(HomeMMSDvo homeMMSDvo){
        return HomeMMS.builder()
                .mrn(homeMMSDvo.getMrn())
                .mrn_mms(homeMMSDvo.getMrn_mms())
                .build();
    }

}

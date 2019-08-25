package kr.co.nexsys.mcp.homemanager.mms.service;



import kr.co.nexsys.mcp.homemanager.exception.DatabaseException;
import kr.co.nexsys.mcp.homemanager.exception.NullResultException;
import kr.co.nexsys.mcp.homemanager.exception.SystemException;
import kr.co.nexsys.mcp.homemanager.mms.controller.MMSController;
import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.HibernateException;
import org.hibernate.engine.jdbc.spi.SqlExceptionHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ConstraintViolationException;
import java.sql.SQLClientInfoException;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class MMSService {

    private MMSDao mmsDao;

    @Autowired
    public MMSService(MMSDao mmsDao) {this.mmsDao = mmsDao;}

    //MMS 전체 조회
    @Transactional(readOnly = true)
    public List<MMS> findAllMMSs() {
        try {
            List<MMS> result = mmsDao.findAll().stream()
                                               .map(MMSService::valueOf)
                                               .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new NullResultException();
            } else {
                return result;
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new DatabaseException("find");
        }
    }

    //MMS 조회
    @Transactional(readOnly = true)
    public List<MMS> findMMSByMrn(String mrn) {
        try {
            List<MMS> result = mmsDao.findAllMMSByMrn(mrn).stream()
                    .map(MMSService::valueOf)
                    .collect(Collectors.toList());
            if (result.isEmpty()) {
                throw new NullResultException();
            } else {
                return result;
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new DatabaseException("find");
        }
    }

    //MMS 생성
    public void createMMS(MMS mms) {
        try {
            //생성
            mmsDao.saveAndFlush(MMSService.valueOf(mms));
        }catch(Exception e){
            throw new DatabaseException("create");
        }
    }

    //MMS 수정
    public void modifyMMS(String mrn, MMS mms) throws SystemException{
        try {
            //mrn에 해당하는 mms 조회
            List<MMS> mmsList = mmsDao.findAllMMSByMrn(mrn).stream()
                                                           .map(MMSService::valueOf)
                                                           .collect(Collectors.toList());

            if(mmsList.isEmpty()){
                throw new NullResultException();
            }else {
                // 수정
                for(MMS m : mmsList){
                    mmsDao.saveAndFlush(MMSService.valueOf(mms));
                }
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new DatabaseException("modify");
        }
    }

    //MMS 삭제
    public void removeMMS(String mrn) throws SystemException{
        boolean result = false;
        try {
            //mrn에 해당하는 mms 조회
            List<MMS> mmsList = mmsDao.findAllMMSByMrn(mrn).stream()
                    .map(MMSService::valueOf)
                    .collect(Collectors.toList());
            if(mmsList.isEmpty()){
                throw new NullResultException();
            }else {
                //삭제
                for (MMS mms : mmsList) {
                    mmsDao.delete(MMSService.valueOf(mms));
                }
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new DatabaseException("delete");
        }
    }


    private static MMS valueOf(MMSDvo mmsDvo){
        return MMS.builder()
                .mrn(mmsDvo.getMrn())
                .ip(mmsDvo.getIp())
                .port(mmsDvo.getPort())
                .createDate(mmsDvo.getCreateDate())
                .updateDate(mmsDvo.getUpdateDate())
                .build();
    }

    private static MMSDvo valueOf(MMS mms){
        return MMSDvo.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .createDate(mms.getCreateDate())
                .updateDate(mms.getUpdateDate())
                .build();
    }

    private static MMSDvo valueOf(MMSDto mmsDto){
        return MMSDvo.builder()
                .mrn(mmsDto.getMrn())
                .ip(mmsDto.getIp())
                .port(mmsDto.getPort())
                .createDate(mmsDto.getCreateDate())
                .updateDate(mmsDto.getUpdateDate())
                .build();
    }

}

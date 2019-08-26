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
        }catch(NullResultException n){
            throw new NullResultException();
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //MMS 조회
    @Transactional(readOnly = true)
    public MMS findMMSByMrn(String mrn) {
        try {
            MMS result = MMSService.valueOf(mmsDao.findOneMMSByMrn(mrn));
            if (result ==null) {
                throw new NullResultException();
            } else {
                return result;
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //MMS 생성
    public MMS createMMS(MMS mms) {
        try {
            MMS result = MMSService.valueOf(mmsDao.saveAndFlush(MMSService.valueOf(mms)));
            return MMS.builder().mrn(result.getMrn())
                                   .ip(result.getIp())
                                   .port(result.getPort()).build();
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //MMS 수정
    public void modifyMMS(String mrn, MMS mms) {
        try {
            //mrn에 해당하는 mms 조회
            MMS result = MMSService.valueOf(mmsDao.findOneMMSByMrn(mrn));
            if(result ==null){
                throw new NullResultException();
            }else {
                result.setIp(mms.getIp());
                result.setPort(mms.getPort());
                mmsDao.saveAndFlush(MMSService.valueOf(result));
            }
        }catch(NullResultException n) {
            throw new NullResultException();
        }catch(Exception e){
            throw new SystemException();
        }
    }

    //MMS 삭제
    public void removeMMS(String mrn) {
        try {
            //mrn에 해당하는 mms 조회
            MMS result = MMSService.valueOf(mmsDao.findOneMMSByMrn(mrn));
            if(result ==null){
                throw new NullResultException();
            }else {
                mmsDao.delete(MMSService.valueOf(result));
            }
        }catch(NullResultException n) {
            throw new NullResultException();
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

    private static MMSDvo valueOf(MMS mms){
        return MMSDvo.builder()
                .mrn(mms.getMrn())
                .ip(mms.getIp())
                .port(mms.getPort())
                .build();
    }
}

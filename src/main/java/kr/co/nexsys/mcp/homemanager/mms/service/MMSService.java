package kr.co.nexsys.mcp.homemanager.mms.service;


import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSRequestDto;
import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@Transactional(propagation = Propagation.REQUIRED, rollbackFor = {Exception.class})
public class MMSService {

    private MMSDao mmsDao;

    @Autowired
    public MMSService(MMSDao mmsDao) {this.mmsDao = mmsDao;}

    //MMS 조회
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<MMS> findMMSByMRN(String mrn){
        return mmsDao.findOneByMrn(mrn).stream()
                .map(MMSService::valueOf)
                .collect(Collectors.toList());
    }

    //MMS 생성


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




}

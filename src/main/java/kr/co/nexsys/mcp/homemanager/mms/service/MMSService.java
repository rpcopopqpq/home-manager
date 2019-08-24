package kr.co.nexsys.mcp.homemanager.mms.service;



import kr.co.nexsys.mcp.homemanager.mms.controller.dto.MMSDto;
import kr.co.nexsys.mcp.homemanager.mms.dao.MMSDao;
import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    //MMS 조회
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<MMS> findMMSByMrn(String mrn){
        return mmsDao.findAllMMSByMrn(mrn).stream()
                .map(MMSService::valueOf)
                .collect(Collectors.toList());
    }

    //MMS 전체 조회
    @Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
    public List<MMS> findAllMMSs(){
        return mmsDao.findAll().stream()
                .map(MMSService::valueOf)
                .collect(Collectors.toList());
    }


    //MMS 생성
    public List<MMS> createMMS(List<MMSDto> mmsDtoList){
        List<MMS> resultList = new ArrayList<>();
        for(MMSDto mmsDto : mmsDtoList){
            resultList.add(MMSService.valueOf(mmsDao.saveAndFlush(MMSService.valueOf(mmsDto))));
        }

        return resultList;
    }

    //MMS 수정
    public List<MMS> modifyMMS(String mrn, MMS mms){
        //mrn에 해당하는 mms 조회
        List<MMS> mmsList = mmsDao.findAllMMSByMrn(mrn).stream()
                                .map(MMSService::valueOf)
                                .collect(Collectors.toList());
        // 수정
        mmsList.stream()
                .map(m->mms)
                .collect(Collectors.toList())
                .forEach(m->mmsDao.saveAndFlush(MMSService.valueOf(m)));

        List<MMS> result = mmsDao.findAllMMSByMrn(mrn).stream()
                .map(MMSService::valueOf)
                .collect(Collectors.toList());

        return result;
    }

    //MMS 삭제
    public boolean removeMMS(String mrn){
        boolean result = false;
        //mrn에 해당하는 mms 조회
        List<MMS> mmsList = mmsDao.findAllMMSByMrn(mrn).stream()
                .map(MMSService::valueOf)
                .collect(Collectors.toList());

        //삭제
        for (MMS mms : mmsList) {
            mmsDao.delete(MMSService.valueOf(mms));
        }

        List<MMS> deleteResult = mmsDao.findAllMMSByMrn(mrn).stream()
                                    .map(MMSService::valueOf)
                                    .collect(Collectors.toList());

        if(deleteResult.isEmpty()){
             result = true;
        }else{
             result = false;
        }

        return result;
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

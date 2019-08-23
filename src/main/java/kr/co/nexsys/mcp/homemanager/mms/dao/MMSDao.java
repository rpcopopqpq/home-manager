package kr.co.nexsys.mcp.homemanager.mms.dao;

import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import kr.co.nexsys.mcp.homemanager.mms.service.vo.MMS;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface MMSDao extends JpaRepository<MMSDvo,Integer> {
    List<MMSDvo> findOneByMrn(String mrn);
}

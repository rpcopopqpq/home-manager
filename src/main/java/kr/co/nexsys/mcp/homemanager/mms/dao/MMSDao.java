package kr.co.nexsys.mcp.homemanager.mms.dao;

import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MMSDao extends JpaRepository<MMSDvo,Long> {
    List<MMSDvo> findOneByMrn(String mrn);
}

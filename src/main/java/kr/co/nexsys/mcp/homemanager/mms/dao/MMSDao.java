package kr.co.nexsys.mcp.homemanager.mms.dao;

import kr.co.nexsys.mcp.homemanager.mms.dao.dvo.MMSDvo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MMSDao extends JpaRepository<MMSDvo,Integer> {
    List<MMSDvo> findAllByMrn(String mrn);
}

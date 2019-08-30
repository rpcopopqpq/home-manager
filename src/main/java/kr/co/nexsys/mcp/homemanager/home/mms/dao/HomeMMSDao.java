package kr.co.nexsys.mcp.homemanager.home.mms.dao;

import kr.co.nexsys.mcp.homemanager.home.mms.dao.dvo.HomeMMSDvo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HomeMMSDao extends JpaRepository<HomeMMSDvo,Long> {
    HomeMMSDvo findOneHomeMMSByMrn(String mrn);
}

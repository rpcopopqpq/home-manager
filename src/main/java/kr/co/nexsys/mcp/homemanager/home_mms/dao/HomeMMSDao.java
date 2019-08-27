package kr.co.nexsys.mcp.homemanager.home_mms.dao;

import kr.co.nexsys.mcp.homemanager.home_mms.dao.dvo.HomeMMSDvo;

public interface HomeMMSDao {
    HomeMMSDvo findOneHomeMMS(String mrn);
}

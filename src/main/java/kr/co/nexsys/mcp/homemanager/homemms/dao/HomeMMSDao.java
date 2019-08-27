package kr.co.nexsys.mcp.homemanager.homemms.dao;

import kr.co.nexsys.mcp.homemanager.homemms.dao.dvo.HomeMMSDvo;

public interface HomeMMSDao {
    HomeMMSDvo findOneHomeMMS(String mrn);
}

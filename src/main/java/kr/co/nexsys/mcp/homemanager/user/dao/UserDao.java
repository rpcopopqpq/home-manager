package kr.co.nexsys.mcp.homemanager.user.dao;

import kr.co.nexsys.mcp.homemanager.user.dao.dvo.UserDvo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserDao extends JpaRepository<UserDvo, Integer> {

    UserDvo findOneByUserId(String userId);
}

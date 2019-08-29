package kr.co.nexsys.mcp.homemanager.user.service;

import kr.co.nexsys.mcp.homemanager.user.dao.UserDao;
import kr.co.nexsys.mcp.homemanager.user.dao.dvo.UserDvo;
import kr.co.nexsys.mcp.homemanager.user.service.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
@RequiredArgsConstructor
public class UserService {

    private final UserDao userDao;

    public static UserDvo toDvo(User user) {
        return UserDvo.builder()
                .userId(user.getUserId())
                .permissions(user.getPermissions())
                .password(user.getPassword())
                .lastName(user.getLastName())
                .firstName(user.getFirstName())
                .email(user.getEmail())
                .mrn(user.getMrn())
                .build();
    }

    public void createUser(User user) {
        userDao.save(toDvo(user));
    }
}

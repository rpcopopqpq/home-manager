package kr.co.nexsys.mcp.homemanager.user.controller;

import kr.co.nexsys.mcp.homemanager.common.utils.DataTrasformer;
import kr.co.nexsys.mcp.homemanager.user.controller.dto.UserDto;
import kr.co.nexsys.mcp.homemanager.user.service.UserService;
import kr.co.nexsys.mcp.homemanager.user.service.vo.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping("/user")
@Controller
@RequiredArgsConstructor
public class UserController {

    public static final String userPermissions = "MCADMIN";

    private final UserService userService;

    private final DataTrasformer dataTrasformer;

    public User toUser(UserDto dto) {
        return User.builder()
                .userId(dto.getUserId())
                .email(dto.getEmail())
                .mrn(dataTrasformer.toUserMrn(dto.getUserId()))
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .password(dto.getPassword())
                .permissions(userPermissions)
                .build();
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String register() {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String createUser(UserDto userDto) {
        User user = toUser(userDto);
        userService.createUser(toUser(userDto));
        return "redirect:/user/register?mrn=" + user.getMrn();
    }
}

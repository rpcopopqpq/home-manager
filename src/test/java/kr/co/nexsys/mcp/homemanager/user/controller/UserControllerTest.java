package kr.co.nexsys.mcp.homemanager.user.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kr.co.nexsys.mcp.homemanager.common.utils.DataTrasformer;
import kr.co.nexsys.mcp.homemanager.common.utils.TestDataGenerator;
import kr.co.nexsys.mcp.homemanager.user.controller.dto.UserDto;
import kr.co.nexsys.mcp.homemanager.user.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @Autowired
    MockMvc mockMvc;
    @MockBean
    UserService userService;
    @MockBean
    DataTrasformer dataTrasformer;
    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void shouldCreateUser() throws Exception {
        UserDto userDto = TestDataGenerator.buildUserDto();
        given(dataTrasformer.toUserMrn(userDto.getUserId())).willReturn(TestDataGenerator.defaultUserMrn);
        doNothing().when(userService).createUser(any());

        mockMvc.perform(
                post("/user/register")
                        .contentType(MediaType.APPLICATION_JSON_UTF8)
                        .content(objectMapper.writeValueAsString(userDto))
        )
                .andDo(print())
                .andExpect(status().is3xxRedirection());

    }
}

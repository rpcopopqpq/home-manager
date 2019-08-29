package kr.co.nexsys.mcp.homemanager.common.utils;

import kr.co.nexsys.mcp.homemanager.test.dao.dvo.TestDvo;
import kr.co.nexsys.mcp.homemanager.user.controller.dto.UserDto;
import kr.co.nexsys.mcp.homemanager.user.dao.dvo.UserDvo;
import kr.co.nexsys.mcp.homemanager.user.service.vo.User;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.util.Random;

@Slf4j
public class TestDataGenerator {

    public final static String defaultDepartment = "test";
    public final static String defaultUserId = "ish128@naver.com";
    public final static String defaultUserPassword = "password123";
    public final static String defaultUserPermission = "MCADMIN";
    public final static String defaultUserMrnPrefix = "urn:mrn:mcl:user:maritimecloud";
    public final static String defaultUserMrn = defaultUserMrnPrefix + defaultUserId;
    public final static String defaultOrgMrn = "urn:mrn:mcl:org:maritimecloud";

    public final static Random random = new Random(System.currentTimeMillis());


    public static TestDvo buildTestDvo() {
        return buildTestDvo(defaultDepartment);
    }

    public static TestDvo buildTestDvo(String department) {
        return TestDvo.builder()
                .department(department)
                .name("tester" + random.nextInt())
                .age(random.nextInt(100))
                .createDate(LocalDateTime.now())
                .build();
    }

    public static UserDvo buildUserDvo() {
        return buildUserDvo(defaultUserId, defaultDepartment);
    }

    public static UserDvo buildUserDvo(String userId, String mrn) {
        return UserDvo.builder()
                .userId(userId)
                .mrn(mrn)
                .email(userId)
                .firstName(userId)
                .lastName(userId)
                .password(defaultUserPassword)
                .permissions(defaultUserPermission)
                .createDate(LocalDateTime.now())
                .updateDate(LocalDateTime.now())
                .build();
    }

    public static User buildUser(String userId, String mrn) {
        return User.builder()
                .userId(userId)
                .mrn(mrn)
                .email(userId)
                .firstName(userId)
                .lastName(userId)
                .password(defaultUserPassword)
                .permissions(defaultUserPermission)
                .build();
    }

    public static User buildUser() {
        return buildUser(defaultUserId, defaultUserMrn);
    }

    public static UserDto buildUserDto(String userId, String mrn) {
        return UserDto.builder()
                .userId(userId)
                .mrn(mrn)
                .email(userId)
                .firstName(userId)
                .lastName(userId)
                .password(defaultUserPassword)
                .permissions(defaultUserPermission)
                .build();
    }

    public static UserDto buildUserDto() {
        return buildUserDto(defaultUserId, defaultUserMrn);
    }
}

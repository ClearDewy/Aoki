package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UserMapper {

    String getRoleById(Integer id);

    String getRoleByRoleId(Integer roleId);

    String getMajorByMajorId(Integer majorId);

    Integer getRoleIdByRole(String roleName);
    Integer getMajorIdByMajor(String majorName);

    UserDto getUserByUsername(String username);

    UserDto getUserByNumber(String number);
    UserDto getUserByEmail(String email);

    Integer addUser(UserDto userDto);

    Integer updateUserByUsername(UserDto userDto);
    Integer updateUserById(UserDto userDto);

    Integer deleteUserById(Integer id);

    List<String> getAllRole();

    List<String> getAllMajor();

    Integer deleteRoleByName(String roleName);

    Integer deleteMajorByName(String majorName);

    Integer addRole(String roleName);

    Integer addMajor(String majorName);

    Integer countUsername(String username);

    Integer countNumber(String number);

    Integer countEmail(String email);

}

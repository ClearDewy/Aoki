package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserMapper {

    UserDto getUserById(Integer id);
    String getMajorByMajorId(Integer majorId);

    Integer getRoleById(Integer id);
    Integer getMajorIdByMajor(String majorName);

    UserDto getUserByUsername(String username);

    UserDto getUserByEmail(String email);

    Integer addUser(UserDto userDto);

    Integer updateUserById(UserDto userDto);

    Integer deleteUserById(Integer id);


    List<String> getAllMajor();


    Integer deleteMajorByName(String majorName);


    Integer addMajor(String majorName);

    Integer countUsername(String username);

    Integer countEmail(String email);


    List<UserListVo> getUsersByUsernames(List<String> usernames);

    List<UserVo> getAllUser();
}

package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.user.UserListVo;
import com.cleardewy.aoki.entity.vo.user.UserVo;
import com.cleardewy.aoki.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Component
@Slf4j
public class UserEntityManager{
    @Autowired
    UserMapper userMapper;



    public UserDto getUserById(Integer id){
        return userMapper.getUserById(id);

    }




    public String getMajorByMajorId(Integer majorId){
        return userMapper.getMajorByMajorId(majorId);
    }




    public Integer getRoleById(Integer id){
        return userMapper.getRoleById(id);
    }


    public Integer getMajorIdByMajor(String majorName) {
        return userMapper.getMajorIdByMajor(majorName);
    }




    public UserDto getUserByUsername(String username) {
        return userMapper.getUserByUsername(username);
    }




    public UserDto getUserByEmail(String email){
        return userMapper.getUserByEmail(email);
    }



    public void addUser(UserDto userDto){
        userMapper.addUser(userDto);
    }



    public void updateUserById(UserDto userDto){
        userMapper.updateUserById(userDto);
    }


    public void deleteUserById(Integer id){
        userMapper.deleteUserById(id);
    }



    public List<String> getAllMajor(){
        return userMapper.getAllMajor();
    }


    public void deleteMajorByName(String majorName){
        userMapper.deleteMajorByName(majorName);
    }

    public void addMajor(String majorName){
        userMapper.addMajor(majorName);
    }

    public Integer countUsername(String username){
        return userMapper.countUsername(username);
    }


    public Integer countEmail(String email){
        return userMapper.countEmail(email);
    }
    public List<UserListVo> getUsersByUsernames(List<String> usernames) {
        return userMapper.getUsersByUsernames(usernames);
    }

    public List<UserVo> getAllUser() {
        return userMapper.getAllUser();
    }


}

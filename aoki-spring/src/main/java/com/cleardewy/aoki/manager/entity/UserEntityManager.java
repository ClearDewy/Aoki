package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

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
        UserDto user = userMapper.getUserById(id);
        if (user==null)throw AokiException.notFound();
        return user;
    }




    public String getMajorByMajorId(Integer majorId){
        String major = userMapper.getMajorByMajorId(majorId);
        if (major.isEmpty())throw AokiException.notFound();
        return major;
    }




    public Integer getRoleById(Integer id){
        Integer role=userMapper.getRoleById(id);
        if (role==null)throw AokiException.notFound();
        return role;
    }


    public Integer getMajorIdByMajor(String majorName) {
        Integer id= userMapper.getMajorIdByMajor(majorName);
        if (id==null)throw AokiException.notFound();
        return id;
    }




    public UserDto getUserByUsername(String username) {
        UserDto userDto = userMapper.getUserByUsername(username);
        if (userDto==null)throw AokiException.notFound();
        return userDto;
    }




    public UserDto getUserByEmail(String email){
        UserDto userDto = userMapper.getUserByEmail(email);
        if (userDto==null)throw AokiException.notFound();
        return userDto;
    }



    public void addUser(UserDto userDto){
        if (userMapper.addUser(userDto)==0){
            throw AokiException.notFound();
        }
    }



    public void updateUserById(UserDto userDto){
        if (userMapper.updateUserById(userDto)==0){
            throw AokiException.notFound();
        }
    }


    public void deleteUserById(Integer id){
        if (userMapper.deleteUserById(id)==0){
            throw AokiException.notFound();
        }
    }



    public List<String> getAllMajor(){
        return userMapper.getAllMajor();
    }


    public void deleteMajorByName(String majorName){
        if (userMapper.deleteMajorByName(majorName)==0){
            throw AokiException.notFound();
        }
    }

    public void addMajor(String majorName){
        if (userMapper.addMajor(majorName)==0){
            throw AokiException.notFound();
        }
    }

    public Integer countUsername(String username){
        return userMapper.countUsername(username);
    }


    public Integer countEmail(String email){
        return userMapper.countEmail(email);
    }

}

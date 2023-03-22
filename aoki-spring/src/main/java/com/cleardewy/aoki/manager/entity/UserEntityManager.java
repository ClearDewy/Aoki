package com.cleardewy.aoki.manager.entity;

import com.cleardewy.aoki.constant.ResultStatus;
import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.exception.AokiException;
import com.cleardewy.aoki.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ Author: ClearDewy
 * @ Description:
 **/
@Service
public class UserEntityManager{
    @Autowired
    UserMapper userMapper;


    @Cacheable(value = "roleName:id",key = "#id")
    public String getRoleById(Integer id) {
        String role = userMapper.getRoleById(id);
        if (role.isEmpty())throw new AokiException(ResultStatus.Status.FAIL);
        return role;
    }


    @Cacheable(value = "roleName:roleId",key = "#roleId")
    public String getRoleByRoleId(Integer roleId){
        String role = userMapper.getRoleByRoleId(roleId);
        if (role.isEmpty())throw new AokiException(ResultStatus.Status.FAIL);
        return role;
    }

    @Cacheable(value = "majorName:majorId",key = "#majorId")
    public String getMajorByMajorId(Integer majorId){
        String major = userMapper.getMajorByMajorId(majorId);
        if (major.isEmpty())throw new AokiException(ResultStatus.Status.FAIL);
        return major;
    }


    @Cacheable(value = "roleid:role",key = "#roleName")
    public Integer getRoleIdByRole(String roleName) {
        Integer id= userMapper.getRoleIdByRole(roleName);
        if (id==null)throw  new AokiException(ResultStatus.Status.FAIL);
        return id;
    }

    @Cacheable(value = "majorid:role",key = "#majorName")
    public Integer getMajorIdByMajor(String majorName) {
        Integer id= userMapper.getMajorIdByMajor(majorName);
        if (id==null)throw  new AokiException(ResultStatus.Status.FAIL);
        return id;
    }


    @Cacheable(value = "user:username",key = "#username")
    public UserDto getUserByUsername(String username) {
        UserDto userDto = userMapper.getUserByUsername(username);
        if (userDto==null)throw new AokiException(ResultStatus.Status.FAIL);
        return userDto;
    }

    @Cacheable(value = "user:number",key = "#number")
    public UserDto getUserByNumber(String number){
        UserDto userDto = userMapper.getUserByNumber(number);
        if (userDto==null)throw new AokiException(ResultStatus.Status.FAIL);
        return userDto;
    }


    @Cacheable(value = "user:email",key = "#email")
    public UserDto getUserByEmail(String email){
        UserDto userDto = userMapper.getUserByEmail(email);
        if (userDto==null)throw new AokiException(ResultStatus.Status.FAIL);
        return userDto;
    }

    public void addUser(UserDto userDto){
        if (userMapper.addUser(userDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }
    @CacheEvict(value = {"user:username","user:number","user:email"},allEntries = true)
    public void updateUserByUsername(UserDto userDto){
        if (userMapper.updateUserByUsername(userDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }
    @CacheEvict(value = {"user:username","user:number","user:email"},allEntries = true)
    public void updateUserById(UserDto userDto){
        if (userMapper.updateUserById(userDto)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }
    @CacheEvict(value = {"user:username","user:number","user:email"},allEntries = true)
    public void deleteUserById(Integer id){
        if (userMapper.deleteUserById(id)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    @Cacheable(value = "allrole")
    public List<String> getAllRole(){
        return userMapper.getAllRole();
    }
    @Cacheable(value = "allmajor")
    public List<String> getAllMajor(){
        return userMapper.getAllMajor();
    }

    @CacheEvict(value = {"allrole","roleName:id","roleName:roleId","roleid:role"},allEntries = true)
    public void deleteRoleByName(String roleName){
        if (userMapper.deleteRoleByName(roleName)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }
    @CacheEvict(value = {"allmajor","majorName:id","majorName:majorId","majorid:major"},allEntries = true)
    public void deleteMajorByName(String majorName){
        if (userMapper.deleteMajorByName(majorName)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    public void addRole(String roleName){
        if (userMapper.addRole(roleName)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    public void addMajor(String majorName){
        if (userMapper.addMajor(majorName)==0){
            throw new AokiException(ResultStatus.Status.FAIL);
        }
    }

    public Integer countUsername(String username){
        return userMapper.countUsername(username);
    }

    public Integer countNumber(String number){
        return userMapper.countNumber(number);
    }

    public Integer countEmail(String email){
        return userMapper.countEmail(email);
    }

}

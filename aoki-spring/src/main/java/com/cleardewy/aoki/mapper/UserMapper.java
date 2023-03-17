package com.cleardewy.aoki.mapper;

import com.cleardewy.aoki.entity.dto.UserDto;
import com.cleardewy.aoki.entity.vo.AccountVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UserMapper {

    String getRoleById(Integer id);

    List<UserDto> getUserByUsername(String username);

    String getRoleByRoleId(Integer roleId);

    String getMajorByMajorId(Integer majorId);
}

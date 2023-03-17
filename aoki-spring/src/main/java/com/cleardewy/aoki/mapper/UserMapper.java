package com.cleardewy.aoki.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.dao.DataAccessException;

import java.util.List;

@Mapper
public interface UserMapper {

    String getRoleById(Integer id);


}

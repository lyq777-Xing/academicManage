package com.manage.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.manage.entity.User;
import org.apache.ibatis.annotations.Mapper;


/**
 * @author lyq
 * @time 2024/1/24 14:57
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {

}

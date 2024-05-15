package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.User;
import com.manage.entity.vo.UserClassVo;
import com.manage.mapper.UserClassMapper;
import com.manage.mapper.UserMapper;
import com.manage.service.UserClassService;
import com.manage.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author lyq
 * @time 2024/4/10 20:38
 */

@Service
@Transactional
public class IUserClassService extends ServiceImpl<UserClassMapper, UserClassVo> implements UserClassService {

    @Autowired
    private UserClassMapper userClassMapper;

    @Autowired
    private UserService userService;


    /**
     * 插入用户班级关联
     * @param userClassVo 用户班级关联
     * @return int
     */
    @Override
    public int insert(UserClassVo userClassVo) {
        return userClassMapper.insert(userClassVo);
    }

    /**
     * 更新用户班级关联
     * @param userClassVo 用户班级关联
     * @return int
     */
    @Override
    public int updateUserClass(UserClassVo userClassVo) {
        QueryWrapper<UserClassVo> queryWrapper = new QueryWrapper<UserClassVo>().eq("user_id", userClassVo.getUserId());
        return userClassMapper.update(userClassVo, queryWrapper);
    }

    /**
     * 根据用户id查询用户班级关联
     * @param id 用户id
     * @return UserClassVo
     */
    @Override
    public UserClassVo findByUserId(Integer id) {
        QueryWrapper<UserClassVo> queryWrapper = new QueryWrapper<UserClassVo>().eq("user_id", id);
        return userClassMapper.selectOne(queryWrapper);
    }

    /**
     * 根据班级id获取学生列表
     * @param classId 班级id
     * @return List<User>
     */
    @Override
    public List<User> getStudentListByClassId(Integer classId) {
        QueryWrapper<UserClassVo> wrapper = new QueryWrapper<>();
        wrapper.eq("class_id", classId);
        ArrayList<User> users = new ArrayList<>();
        List<UserClassVo> userClassVos = userClassMapper.selectList(wrapper);
        for (UserClassVo userClassVo : userClassVos) {
            User user = userService.getById(userClassVo.getUserId());
            users.add(user);
        }
        return users;
    }
}

package com.manage.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.User;
import com.manage.entity.vo.UserClassVo;

import java.util.List;

/**
 * @author lyq
 * @time 2024/4/10 20:37
 */
public interface UserClassService extends IService<UserClassVo> {

    /**
     * 插入用户班级关联
     * @param userClassVo 用户班级关联
     * @return int
     */
    int insert(UserClassVo userClassVo);

    /**
     * 更新用户班级关联
     * @param userClassVo 用户班级关联
     * @return int
     */
    int updateUserClass(UserClassVo userClassVo);

    /**
     * 根据用户id查询用户班级关联
     * @param id 用户id
     * @return UserClassVo
     */
    UserClassVo findByUserId(Integer id);

    /**
     * 根据班级id获取学生列表
     * @param classId 班级id
     * @return List<User>
     */
    List<User> getStudentListByClassId(Integer classId);
}

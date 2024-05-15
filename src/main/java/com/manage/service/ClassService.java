package com.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.Class;
import com.manage.entity.User;

import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/4/8 23:28
 */

public interface ClassService extends IService<Class> {

    /**
     * 根据id查询班级名称
     * @param id 班级id
     * @return String
     */
//    String findClassNameById(Integer id);

    /**
     * 获取班级名称和id列表
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> getClassNameAndIdListByCollegeId(Integer collegeId);

    /**
     * 查询所有班级列表（分页）
     * @return List<Class>
     */
    IPage<Class> findAllClasses(Integer pageNum, Integer pageSize, String query);

    /**
     * 添加班级
     * @param aClass 班级
     */
    void addClass(Class aClass);

    /**
     * 更新班级
     * @param aClass 班级
     */
    void updateClass(Class aClass);

    /**
     * 删除班级
     * @param id 班级id
     */
    void deleteClass(Integer id);

    /**
     * 获取图表信息
     * @return Map<String,Object>
     */
    Map<String,Object> echartsData();

}

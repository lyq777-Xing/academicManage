package com.manage.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.manage.entity.College;

import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/29 19:17
 */
public interface CollegeService extends IService<College> {

    /**
     * 根据id查询学院名称
     * @param id 学院id
     * @return String
     */
    String findCollegeNameById(Integer id);

    /**
     * 获取学院名称和id列表
     * @return List<Map<String,String>>
     */
    List<Map<String,String>> getCollegeNameAndIdList();

    /**
     * 查询所有学院列表 分页
     * @return List<College>
     */
    Page<College> findAllColleges(Integer pageNum, Integer pageSize, String query);

    /**
     * 查询所有学院列表
     * @return List<College>
     */
    List<College> findAllColleges();

    /**
     * 添加学院
     * @param college 学院
     */
    void addCollege(College college);

    /**
     * 更新学院
     * @param college 学院
     */
    void updateCollege(College college);

    /**
     * 删除学院
     * @param id 学院id
     */
    void deleteCollege(Integer id);
}

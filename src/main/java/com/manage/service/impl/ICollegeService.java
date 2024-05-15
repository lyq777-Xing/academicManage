package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.College;
import com.manage.mapper.CollegeMapper;
import com.manage.service.CollegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/29 19:17
 */

@Transactional
@Service
public class ICollegeService extends ServiceImpl<CollegeMapper, College> implements CollegeService {

    @Autowired
    private CollegeMapper collegeMapper;

    /**
     * 根据id查询学院名称
     * @param id 学院id
     * @return String
     */
    @Override
    public String findCollegeNameById(Integer id) {
        College college = collegeMapper.selectById(id);
        return college.getName();
    }

    /**
     * 获取学院名称和id列表
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> getCollegeNameAndIdList() {
        ArrayList<Map<String, String>> list = new ArrayList<>();
        collegeMapper.selectList(null).forEach(college -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("label", college.getName());
            map.put("value", college.getId().toString());
            list.add(map);
        });
        return list;
    }

    /**
     * 查询所有学院列表 分页
     * @return List<College>
     */
    @Override
    public Page<College> findAllColleges(Integer pageNum, Integer pageSize, String query) {
        Page<College> page = new Page<>(pageNum, pageSize);
        if(query != null && !query.equals(""))
            return collegeMapper.selectPage(page, null);
        QueryWrapper<College> wrapper = new QueryWrapper<>();
        wrapper.like("name", query);
        return collegeMapper.selectPage(page, wrapper);
    }

    /**
     * 查询所有学院列表
     * @return List<College>
     */
    @Override
    public List<College> findAllColleges() {
        return collegeMapper.selectList(null);
    }


    /**
     * 添加学院
     * @param college 学院
     */
    @Override
    public void addCollege(College college) {
        college.setCreateTime(LocalDateTime.now());
        int insert = collegeMapper.insert(college);
        if(insert == 0){
            throw new RuntimeException("添加学院失败");
        }
    }

    /**
     * 更新学院
     * @param college 学院
     */
    @Override
    public void updateCollege(College college) {
        int i = collegeMapper.updateById(college);
        if(i == 0){
            throw new RuntimeException("更新学院失败");
        }
    }

    /**
     * 删除学院
     * @param id 学院id
     */
    @Override
    public void deleteCollege(Integer id) {
        int i = collegeMapper.deleteById(id);
        if(i == 0){
            throw new RuntimeException("删除学院失败");
        }
    }
}

package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.College;
import com.manage.entity.User;
import com.manage.entity.Class;
import com.manage.entity.vo.UserClassVo;
import com.manage.mapper.ClassMapper;
import com.manage.service.ClassService;
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
 * @time 2024/4/8 23:29
 */
@Service
@Transactional
public class IClassService extends ServiceImpl<ClassMapper, Class> implements ClassService {

    @Autowired
    private ClassMapper classMapper;

    @Autowired
    private CollegeService collegeService;


    /**
     * 获取班级名称和id列表
     * @param collegeId 学院id
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> getClassNameAndIdListByCollegeId(Integer collegeId) {
        QueryWrapper<Class> wrapper = new QueryWrapper<>();
        wrapper.eq("college_id", collegeId);
        ArrayList<Map<String, String>> list = new ArrayList<>();
        classMapper.selectList(wrapper).forEach(aClass -> {
            HashMap<String, String> map = new HashMap<>();
            map.put("label", aClass.getName());
            map.put("value", aClass.getId().toString());
            list.add(map);
        });
        return list;
    }

    /**
     * 查询所有班级列表（分页）
     * @param pageNum 页码
     * @param pageSize 每页数量
     * @param query 查询条件
     * @return List<Class>
     */
    @Override
    public IPage<Class> findAllClasses(Integer pageNum, Integer pageSize, String query) {
        Page<User> page = new Page<>(pageNum,pageSize);
        IPage<Class> allClasses = null;
/*        if(query == null){
            allClasses = classMapper.findAllClasses(page, null);
        }else {
            QueryWrapper<Class> wrapper = new QueryWrapper<>();
            wrapper.like("name",query);
            allClasses = classMapper.findAllClasses(page, wrapper);
        }*/
        allClasses = classMapper.findAllClasses(page, query);
        return allClasses;
    }

    /**
     * 添加班级
     * @param aClass 班级
     */
    @Override
    public void addClass(Class aClass) {
        aClass.setCreateTime(LocalDateTime.now());
        int insert = classMapper.insert(aClass);
        if(insert == 0){
            throw new RuntimeException("添加班级失败");
        }
    }

    /**
     * 更新班级
     * @param aClass 班级
     */
    @Override
    public void updateClass(Class aClass) {
        int i = classMapper.updateById(aClass);
        if(i == 0){
            throw new RuntimeException("更新班级失败");
        }
    }

    /**
     * 删除班级
     * @param id 班级id
     */
    @Override
    public void deleteClass(Integer id) {
        int i = classMapper.deleteById(id);
        if(i == 0){
            throw new RuntimeException("删除班级失败");
        }
    }

    @Override
    public Map<String, Object> echartsData() {
        HashMap<Integer, Integer> hashMap = new HashMap<>();
        classMapper.selectList(null).forEach(aClass -> {
            if(hashMap.containsKey(aClass.getCollegeId())) {
                hashMap.put(aClass.getCollegeId(), hashMap.get(aClass.getCollegeId()) + 1);
            }
            else {
                hashMap.put(aClass.getCollegeId(), 1);
            }
        });
        String[] strings = new String[hashMap.size()];
        Integer[] integers = new Integer[hashMap.size()];
        int i = 0;
        for (Map.Entry<Integer, Integer> entry : hashMap.entrySet()) {
            String name = collegeService.getById(entry.getKey()).getName();
            strings[i] = name;
            integers[i] = entry.getValue();
            i++;
        }
        HashMap<String, Object> map = new HashMap<>();
        map.put("name",strings);
        map.put("value",integers);
        return map;
    }
}

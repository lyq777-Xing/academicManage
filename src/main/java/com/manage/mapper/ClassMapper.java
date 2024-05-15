package com.manage.mapper;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.manage.entity.Class;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @author lyq
 * @time 2024/4/8 23:28
 */

@Mapper
public interface ClassMapper extends BaseMapper<Class> {

    /**
     * 查询所有班级列表
     * @return List<Class>
     */
    IPage<Class> findAllClasses(Page<?> page, @Param("query") String query);

    List<Class> selectEcharts();
}

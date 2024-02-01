package com.manage.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.College;
import com.manage.mapper.CollegeMapper;
import com.manage.service.CollegeService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author lyq
 * @time 2024/1/29 19:17
 */

@Transactional
@Service
public class ICollegeService extends ServiceImpl<CollegeMapper, College> implements CollegeService {
}

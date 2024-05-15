package com.manage.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.manage.entity.Permission;
import com.manage.entity.Role;
import com.manage.entity.User;
import com.manage.entity.vo.UserClassVo;
import com.manage.entity.vo.UserRoleVo;
import com.manage.mapper.RoleMapper;
import com.manage.mapper.UserMapper;
import com.manage.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/24 14:59
 */

@Transactional
@Service
public class IUserService extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Autowired
    private UserClassService userClassService;

    @Autowired
    private UserRoleService userRoleService;

    /**
     * 根据id查询
     * @param id
     * @return User
     */
    @Override
    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 根据用户名查询
     * @param username
     * @return User
     */
    @Override
    public User findByUsername(String username) {
        QueryWrapper<User> userQueryWrapper = new QueryWrapper<>();
        userQueryWrapper.eq("username",username);
        return userMapper.selectOne(userQueryWrapper);
    }

    /**
     * 根据用户id查询权限列表
     * @param userId 用户id
     * @return String
     */
    @Override
    public String getAuthorityInfo(Integer userId) {
        String authority = "";
//        获取用户name
        User user = userMapper.selectById(userId);
        String username = user.getUsername();
        List<Role> roles = roleService.findRolesByUserId(userId);
        if(roles.size() == 1){
            authority = "ROLE_" + roles.get(0).getKey();

        }
        Role role = roles.get(0);
        if(role != null){
            List<Permission> permissions = permissionService.findByUserId(userId);
            for (Permission permission : permissions) {
                authority += "," + permission.getName();
            }
        }
        return authority;
    }

    /**
     * 查询所有学生列表（不分页）
     * @return List<User>
     */
    @Override
    public List<User> findAllStudents() {
        List<User> students = userMapper.findAllStudents();
        return students;
    }

    /**
     * 查询所有用户列表(分页)
     * @return Page<User>
     */
    @Override
    public IPage<User> findAllUsers(Integer pageNum, Integer pageSize, String query) {
/*        Page<User> page = new Page<>(pageNum,pageSize);
        Page<User> userPage = new Page<>();
        if(query == null){
            userPage = userMapper.selectPage(page, null);
        }else{
            LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
            wrapper.like(User::getUsername,query)
                    .or()
                    .like(User::getId,query)
                    .or()
                    .like(User::getPhone,query)
                    .or()
                    .like(User::getEmail,query);
            userPage = userMapper.selectPage(page,wrapper);
            for (User user : userPage.getRecords()) {
//                user.setClassName(userMapper.findClassNameByUserId(user.getId()));
                user.setPassword(null);
                if(user.getCollegeId() != null){
                    user.setCollegeName(collegeService.findCollegeNameById(user.getCollegeId()));
                }
            }
        }*/
        Page<User> page = new Page<>(pageNum,pageSize);
/*        if(query == null){
           return userMapper.findAllUsers(page, null);
        }
        LambdaQueryWrapper<User> wrapper = new LambdaQueryWrapper<>();
        wrapper.like(User::getUsername,query)
                .or()
                .like(User::getId,query)
                .or()
                .like(User::getPhone,query)
                .or()
                .like(User::getEmail,query);*/
        return userMapper.findAllUsers(page, query);
    }

    /**
     * 添加用户
     * @param user 用户
     */
    @Override
    public void addUser(User user) {
        // 1、密码加密
        String password = user.getPassword();
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String encode = encoder.encode(password);
        user.setPassword(encode);
        // 2、添加用户表数据
        if(user.getIntroduce().isEmpty()){
            user.setIntroduce("此人很懒，什么都没有留下");
        }
        user.setCreateTime(LocalDateTime.now());
        userMapper.insert(user);
        // 3、添加用户班级关联表
        UserClassVo userClassVo = new UserClassVo();
        userClassVo.setUserId(user.getId());
        userClassVo.setClassId(user.getClassId());
        userClassService.insert(userClassVo);
        // 4、添加用户角色关联表
        UserRoleVo userRoleVo = new UserRoleVo();
        userRoleVo.setUserId(user.getId());
        userRoleVo.setRoleId(user.getRoleId());
        userRoleService.insert(userRoleVo);
    }


    /**
     * 根据Id删除用户
     * @param id 用户id
     * @return int
     */
    @Override
    public int deleteById(Integer id) {
        return userMapper.deleteById(id);
    }


    /**
     * 更新用户（不修改密码）
     * @param user 用户
     * @return int
     */
    @Override
    public int updateUser(User user) {
//        修改用户表
        User selectById = userMapper.selectById(user.getId());
        if(selectById == null){
            return 0;
        }
        if(!selectById.getUsername().equals(user.getUsername())){
            User byUsername = findByUsername(user.getUsername());
            if(byUsername != null){
                return 0;
            }
        }
        user.setUpdateTime(LocalDateTime.now());
        user.setPassword(selectById.getPassword());
        UserRoleVo userRoleVoById = userRoleService.findByUserId(user.getId());
//        修改用户角色关联表
        if (selectById.getRoleId() != userRoleVoById.getRoleId()){
            UserRoleVo userRoleVo = new UserRoleVo();
            userRoleVo.setUserId(user.getId());
            userRoleVo.setRoleId(user.getRoleId());
            int temp = userRoleService.updateUserRole(userRoleVo);
            if(temp == 0){
                return 0;
            }
        }
//        修改用户班级关联表
        UserClassVo userClassVoById = userClassService.findByUserId(user.getId());
        if(userClassVoById == null){
            UserClassVo userClassVo = new UserClassVo();
            userClassVo.setUserId(user.getId());
            userClassVo.setClassId(user.getClassId());
            int insert = userClassService.insert(userClassVo);
            if(insert == 0){
                return 0;
            }
        }else if(selectById.getClassId() != userClassVoById.getClassId()){
            UserClassVo userClassVo = new UserClassVo();
            userClassVo.setUserId(user.getId());
            userClassVo.setClassId(user.getClassId());
            int temp = userClassService.updateUserClass(userClassVo);
            if(temp == 0){
                return 0;
            }
        }
        return userMapper.updateById(user);
    }

    /**
     * 查询所有老师列表
     * @return List<User>
     */
    @Override
    public List<User> findAllTeachers() {
        return userMapper.findAllTeachers();
    }

    /**
     * 获取教师名称和id列表
     * @return List<Map<String,String>>
     */
    @Override
    public List<Map<String, String>> getTeacherNameAndIdList() {
        List<User> allTeachers = userMapper.findAllTeachers();
        ArrayList<Map<String, String>> list = new ArrayList<>();
        for (User user : allTeachers) {
            list.add(Map.of("label",user.getUsername(),"value",user.getId().toString()));
        }
        return list;
    }
}

package com.manage.controller;

import com.manage.common.ResponseResult;
import com.manage.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author lyq
 * @time 2024/1/29 19:20
 */
@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    /**
     * 获取角色id和角色名列表
     * @return ResponseResult Object
     */
    @GetMapping("/getRoleIdAndNameList")
    public ResponseResult<Object> getRoleIdAndNameList(){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            result.Success("获取角色id和角色名列表成功");
            result.setData(roleService.getRoleIdAndNameList());
        }catch (Exception e){
            result.Error("查询失败");
        }
        return result;
    }

    /**
     * 查询所有角色列表
     * @return ResponseResult Object
     */
    @GetMapping("/list")
    public ResponseResult<Object> findAllRoles(
            @RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
            @RequestParam(value = "pageSize", defaultValue = "10") Integer pageSize,
            @RequestParam(value = "query", required = false) String query
    ){
        ResponseResult<Object> result = new ResponseResult<>();
        try{
            result.Success("获取成功",roleService.findAllRolesPage(pageNum, pageSize, query));
        }catch (Exception e){
            e.printStackTrace();
            result.Error("获取失败");
        }
        return result;
    }
}

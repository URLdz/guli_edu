package com.url.aclservice.controller;


import com.url.aclservice.entity.Permission;
import com.url.aclservice.service.PermissionService;
import com.url.commonutils.CommonResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 * 权限 菜单管理
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-12
 */
@Api(description = "菜单管理")
@RestController
@RequestMapping("/admin/acl/permission")
public class PermissionController {

    @Autowired
    private PermissionService permissionService;

    //获取全部菜单
    @ApiOperation(value = "查询所有菜单")
    @GetMapping
    public CommonResult indexAllPermission() {
        List<Permission> list =  permissionService.queryAllMenuGuli();
        return CommonResult.ok().data("children",list);
    }

    @ApiOperation(value = "递归删除菜单")
    @DeleteMapping("remove/{id}")
    public CommonResult remove(@PathVariable String id) {
        permissionService.removeChildByIdGuli(id);
        return CommonResult.ok();
    }

    @ApiOperation(value = "给角色分配权限")
    @PostMapping("/doAssign")
    public CommonResult doAssign(String roleId,String[] permissionId) {
        permissionService.saveRolePermissionRealtionShipGuli(roleId,permissionId);
        return CommonResult.ok();
    }

    @ApiOperation(value = "根据角色获取菜单")
    @GetMapping("toAssign/{roleId}")
    public CommonResult toAssign(@PathVariable String roleId) {
        List<Permission> list = permissionService.selectAllMenu(roleId);
        return CommonResult.ok().data("children", list);
    }



    @ApiOperation(value = "新增菜单")
    @PostMapping("save")
    public CommonResult save(@RequestBody Permission permission) {
        permissionService.save(permission);
        return CommonResult.ok();
    }

    @ApiOperation(value = "修改菜单")
    @PutMapping("update")
    public CommonResult updateById(@RequestBody Permission permission) {
        permissionService.updateById(permission);
        return CommonResult.ok();
    }

}


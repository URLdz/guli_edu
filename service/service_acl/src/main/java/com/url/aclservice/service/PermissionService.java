package com.url.aclservice.service;

import com.alibaba.fastjson.JSONObject;
import com.url.aclservice.entity.Permission;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author xidazhen
 * @since 2022-10-12
 */
public interface PermissionService extends IService<Permission> {

    //根据角色获取菜单
    List<Permission> selectAllMenu(String roleId);


    //根据用户id获取用户菜单
    List<String> selectPermissionValueByUserId(String id);

    List<JSONObject> selectPermissionByUserId(String id);

    //获取全部菜单
    List<Permission> queryAllMenuGuli();

    //递归删除菜单
    void removeChildByIdGuli(String id);

    //给角色分配权限
    void saveRolePermissionRealtionShipGuli(String roleId, String[] permissionId);
}

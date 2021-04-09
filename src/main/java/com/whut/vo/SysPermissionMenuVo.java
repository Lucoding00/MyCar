package com.whut.vo;

import com.whut.model.SysPermission;
import lombok.Data;

import java.util.List;

/**
 * @author 鲁佳磊
 * @create 2021-04-07 20:41
 */
@Data
public class SysPermissionMenuVo extends SysPermission {
    /**
     * 子菜单
     */
    private List<SysPermissionMenuVo> children;


    private String checkArr = "0";
}

package org.tiankafei.user.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.tiankafei.user.param.RoleInfoCheckParam;
import org.tiankafei.user.param.RoleInfoCountParam;
import org.tiankafei.user.param.RoleInfoDeleteParam;
import org.tiankafei.user.param.RoleInfoListParam;
import org.tiankafei.user.param.RoleInfoPageParam;
import org.tiankafei.user.service.RoleInfoService;
import org.tiankafei.user.vo.RoleInfoVo;
import org.tiankafei.web.common.api.ApiResult;
import org.tiankafei.web.common.controller.BaseController;
import org.tiankafei.web.common.param.IdsParam;
import org.tiankafei.web.common.vo.Paging;

/**
 * <p>
 * 角色信息表 前端控制器
 * </p>
 *
 * @author tiankafei
 * @since 1.0
 */
@RestController
@RequestMapping("/roleInfo")
@Api(value = "角色信息表 API", tags = {"角色信息表"})
public class RoleInfoController extends BaseController {

    @Autowired
    private RoleInfoService roleInfoService;

    @PostMapping("/check")
    @ApiOperation(value = "校验 角色信息表 对象是否存在")
    public ApiResult<Boolean> checkRoleInfoControllerExists(@Valid @RequestBody RoleInfoCheckParam roleInfoCheckParam) throws Exception {
        Boolean flag = roleInfoService.checkRoleInfoServiceExists(roleInfoCheckParam);
        return ApiResult.ok(flag);
    }

    @PostMapping
    @ApiOperation(value = "添加 角色信息表")
    public ApiResult<Long> addRoleInfoController(@Valid @RequestBody RoleInfoVo roleInfoVo) throws Exception {
        Long id = roleInfoService.addRoleInfoService(roleInfoVo);
        return ApiResult.ok(id);
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量添加 角色信息表")
    public ApiResult<List<Long>> batchAddRoleInfoController(@Valid @RequestBody List<RoleInfoVo> roleInfoVoList) throws Exception {
        List<Long> idList = roleInfoService.batchAddRoleInfoService(roleInfoVoList);
        return ApiResult.ok(idList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 角色信息表")
    public ApiResult<Boolean> deleteRoleInfoController(@PathVariable(value = "id") String id) throws Exception {
        boolean flag = roleInfoService.deleteRoleInfoService(id);
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除 角色信息表")
    public ApiResult<Boolean> batchDeleteRoleInfoController(@Valid @RequestBody IdsParam idsParam) throws Exception {
        boolean flag = roleInfoService.batchDeleteRoleInfoService(idsParam.getIds());
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/condition")
    @ApiOperation(value = "条件删除 角色信息表")
    public ApiResult<Boolean> conditionDeleteRoleInfoController(@Valid @RequestBody RoleInfoDeleteParam roleInfoDeleteParam) throws Exception {
        boolean flag = roleInfoService.conditionDeleteRoleInfoService(roleInfoDeleteParam);
        return ApiResult.ok(flag);
    }

    @PatchMapping
    @ApiOperation(value = "更新 角色信息表")
    public ApiResult<Boolean> updateRoleInfoController(@Valid @RequestBody RoleInfoVo roleInfoVo) throws Exception {
        boolean flag = roleInfoService.updateRoleInfoService(roleInfoVo);
        return ApiResult.ok(flag);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取 角色信息表 对象")
    public ApiResult<RoleInfoVo> getRoleInfoController(@PathVariable(value = "id") String id) throws Exception {
        RoleInfoVo roleInfoVo = roleInfoService.getRoleInfoServiceById(id);
        return ApiResult.ok(roleInfoVo);
    }

    @GetMapping
    @ApiOperation(value = "获取 角色信息表 对象全部列表")
    public ApiResult<List<RoleInfoVo>> getRoleInfoControllerAllList() throws Exception {
        List<RoleInfoVo> roleInfoVoList = roleInfoService.getRoleInfoServiceList(new RoleInfoListParam());
        return ApiResult.ok(roleInfoVoList);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取 角色信息表 对象列表")
    public ApiResult<List<RoleInfoVo>> getRoleInfoControllerList(@Valid @RequestBody RoleInfoListParam roleInfoListParam) throws Exception {
        List<RoleInfoVo> roleInfoVoList = roleInfoService.getRoleInfoServiceList(roleInfoListParam);
        return ApiResult.ok(roleInfoVoList);
    }

    @PostMapping("/pageList")
    @ApiOperation(value = "获取 角色信息表 分页对象列表")
    public ApiResult<Paging<RoleInfoVo>> getRoleInfoControllerPageList(@Valid @RequestBody RoleInfoPageParam roleInfoPageParam) throws Exception {
        Paging<RoleInfoVo> roleInfoVoList = roleInfoService.getRoleInfoServicePageList(roleInfoPageParam);
        return ApiResult.ok(roleInfoVoList);
    }

    @PostMapping("/count")
    @ApiOperation(value = "求 角色信息表 对象的记录数")
    public ApiResult<Integer> countRoleInfoController(@Valid @RequestBody RoleInfoCountParam roleInfoCountParam) throws Exception {
        Integer count = roleInfoService.countRoleInfoService(roleInfoCountParam);
        return ApiResult.ok(count);
    }


}


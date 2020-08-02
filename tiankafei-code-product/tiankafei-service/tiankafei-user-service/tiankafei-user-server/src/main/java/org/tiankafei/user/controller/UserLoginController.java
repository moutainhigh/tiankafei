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
import org.tiankafei.user.param.UserLoginCheckParam;
import org.tiankafei.user.param.UserLoginCountParam;
import org.tiankafei.user.param.UserLoginDeleteParam;
import org.tiankafei.user.param.UserLoginListParam;
import org.tiankafei.user.param.UserLoginPageParam;
import org.tiankafei.user.service.UserLoginService;
import org.tiankafei.user.vo.UserLoginVo;
import org.tiankafei.web.common.api.ApiResult;
import org.tiankafei.web.common.controller.BaseController;
import org.tiankafei.web.common.param.IdsParam;
import org.tiankafei.web.common.vo.Paging;

/**
 * <p>
 * 用户登录信息表 前端控制器
 * </p>
 *
 * @author tiankafei
 * @since 1.0
 */
@RestController
@RequestMapping("/user/user-login-entity")
@Api(value = "用户登录信息表 API", tags = {"用户登录信息表"})
public class UserLoginController extends BaseController {

    @Autowired
    private UserLoginService userLoginService;

    @PostMapping("/check")
    @ApiOperation(value = "校验 用户登录信息表 对象是否存在")
    public ApiResult<Boolean> checkUserLoginControllerExists(@Valid @RequestBody UserLoginCheckParam userLoginCheckParam) throws Exception {
        Boolean flag = userLoginService.checkUserLoginServiceExists(userLoginCheckParam);
        return ApiResult.ok(flag);
    }

    @PostMapping
    @ApiOperation(value = "添加 用户登录信息表")
    public ApiResult<Long> addUserLoginController(@Valid @RequestBody UserLoginVo userLoginVo) throws Exception {
        Long id = userLoginService.addUserLoginService(userLoginVo);
        return ApiResult.ok(id);
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量添加 用户登录信息表")
    public ApiResult<List<Long>> batchAddUserLoginController(@Valid @RequestBody List<UserLoginVo> userLoginVoList) throws Exception {
        List<Long> idList = userLoginService.batchAddUserLoginService(userLoginVoList);
        return ApiResult.ok(idList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 用户登录信息表")
    public ApiResult<Boolean> deleteUserLoginController(@PathVariable(value = "id") String id) throws Exception {
        boolean flag = userLoginService.deleteUserLoginService(id);
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除 用户登录信息表")
    public ApiResult<Boolean> batchDeleteUserLoginController(@Valid @RequestBody IdsParam idsParam) throws Exception {
        boolean flag = userLoginService.batchDeleteUserLoginService(idsParam.getIds());
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/condition")
    @ApiOperation(value = "条件删除 用户登录信息表")
    public ApiResult<Boolean> conditionDeleteUserLoginController(@Valid @RequestBody UserLoginDeleteParam userLoginDeleteParam) throws Exception {
        boolean flag = userLoginService.conditionDeleteUserLoginService(userLoginDeleteParam);
        return ApiResult.ok(flag);
    }

    @PatchMapping
    @ApiOperation(value = "更新 用户登录信息表")
    public ApiResult<Boolean> updateUserLoginController(@Valid @RequestBody UserLoginVo userLoginVo) throws Exception {
        boolean flag = userLoginService.updateUserLoginService(userLoginVo);
        return ApiResult.ok(flag);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取 用户登录信息表 对象")
    public ApiResult<UserLoginVo> getUserLoginController(@PathVariable(value = "id") String id) throws Exception {
        UserLoginVo userLoginVo = userLoginService.getUserLoginServiceById(id);
        return ApiResult.ok(userLoginVo);
    }

    @GetMapping
    @ApiOperation(value = "获取 用户登录信息表 对象全部列表")
    public ApiResult<List<UserLoginVo>> getUserLoginControllerAllList() throws Exception {
        List<UserLoginVo> userLoginVoList = userLoginService.getUserLoginServiceList(null);
        return ApiResult.ok(userLoginVoList);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取 用户登录信息表 对象列表")
    public ApiResult<List<UserLoginVo>> getUserLoginControllerList(@Valid @RequestBody UserLoginListParam userLoginListParam) throws Exception {
        List<UserLoginVo> userLoginVoList = userLoginService.getUserLoginServiceList(userLoginListParam);
        return ApiResult.ok(userLoginVoList);
    }

    @PostMapping("/pageList")
    @ApiOperation(value = "获取 用户登录信息表 分页对象列表")
    public ApiResult<Paging<UserLoginVo>> getUserLoginControllerPageList(@Valid @RequestBody UserLoginPageParam userLoginPageParam) throws Exception {
        Paging<UserLoginVo> userLoginVoList = userLoginService.getUserLoginServicePageList(userLoginPageParam);
        return ApiResult.ok(userLoginVoList);
    }

    @PostMapping("/count")
    @ApiOperation(value = "求 用户登录信息表 对象的记录数")
    public ApiResult<Integer> countUserLoginController(@Valid @RequestBody UserLoginCountParam userLoginCountParam) throws Exception {
        Integer count = userLoginService.countUserLoginService(userLoginCountParam);
        return ApiResult.ok(count);
    }


}


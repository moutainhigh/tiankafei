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
import org.tiankafei.user.param.DictTableCheckParam;
import org.tiankafei.user.param.DictTableCountParam;
import org.tiankafei.user.param.DictTableDeleteParam;
import org.tiankafei.user.param.DictTableListParam;
import org.tiankafei.user.param.DictTablePageParam;
import org.tiankafei.user.service.DictTableService;
import org.tiankafei.user.vo.DictTableVo;
import org.tiankafei.web.common.api.ApiResult;
import org.tiankafei.web.common.controller.BaseController;
import org.tiankafei.web.common.param.IdsParam;
import org.tiankafei.web.common.vo.Paging;

/**
 * <p>
 * 系统数据字典的数据表 前端控制器
 * </p>
 *
 * @author tiankafei
 * @since 1.0
 */
@RestController
@RequestMapping("/user/dict-table-entity")
@Api(value = "系统数据字典的数据表 API", tags = {"系统数据字典的数据表"})
public class DictTableController extends BaseController {

    @Autowired
    private DictTableService dictTableService;

    @PostMapping("/check")
    @ApiOperation(value = "校验 系统数据字典的数据表 对象是否存在")
    public ApiResult<Boolean> checkDictTableControllerExists(@Valid @RequestBody DictTableCheckParam dictTableCheckParam) throws Exception {
        Boolean flag = dictTableService.checkDictTableServiceExists(dictTableCheckParam);
        return ApiResult.ok(flag);
    }

    @PostMapping
    @ApiOperation(value = "添加 系统数据字典的数据表")
    public ApiResult<Long> addDictTableController(@Valid @RequestBody DictTableVo dictTableVo) throws Exception {
        Long id = dictTableService.addDictTableService(dictTableVo);
        return ApiResult.ok(id);
    }

    @PostMapping("/batch")
    @ApiOperation(value = "批量添加 系统数据字典的数据表")
    public ApiResult<List<Long>> batchAddDictTableController(@Valid @RequestBody List<DictTableVo> dictTableVoList) throws Exception {
        List<Long> idList = dictTableService.batchAddDictTableService(dictTableVoList);
        return ApiResult.ok(idList);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "删除 系统数据字典的数据表")
    public ApiResult<Boolean> deleteDictTableController(@PathVariable(value = "id") String id) throws Exception {
        boolean flag = dictTableService.deleteDictTableService(id);
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/batch")
    @ApiOperation(value = "批量删除 系统数据字典的数据表")
    public ApiResult<Boolean> batchDeleteDictTableController(@Valid @RequestBody IdsParam idsParam) throws Exception {
        boolean flag = dictTableService.batchDeleteDictTableService(idsParam.getIds());
        return ApiResult.ok(flag);
    }

    @DeleteMapping("/condition")
    @ApiOperation(value = "条件删除 系统数据字典的数据表")
    public ApiResult<Boolean> conditionDeleteDictTableController(@Valid @RequestBody DictTableDeleteParam dictTableDeleteParam) throws Exception {
        boolean flag = dictTableService.conditionDeleteDictTableService(dictTableDeleteParam);
        return ApiResult.ok(flag);
    }

    @PatchMapping
    @ApiOperation(value = "更新 系统数据字典的数据表")
    public ApiResult<Boolean> updateDictTableController(@Valid @RequestBody DictTableVo dictTableVo) throws Exception {
        boolean flag = dictTableService.updateDictTableService(dictTableVo);
        return ApiResult.ok(flag);
    }

    @GetMapping("/{id}")
    @ApiOperation(value = "获取 系统数据字典的数据表 对象")
    public ApiResult<DictTableVo> getDictTableController(@PathVariable(value = "id") String id) throws Exception {
        DictTableVo dictTableVo = dictTableService.getDictTableServiceById(id);
        return ApiResult.ok(dictTableVo);
    }

    @GetMapping
    @ApiOperation(value = "获取 系统数据字典的数据表 对象全部列表")
    public ApiResult<List<DictTableVo>> getDictTableControllerAllList() throws Exception {
        List<DictTableVo> dictTableVoList = dictTableService.getDictTableServiceList(new DictTableListParam());
        return ApiResult.ok(dictTableVoList);
    }

    @PostMapping("/list")
    @ApiOperation(value = "获取 系统数据字典的数据表 对象列表")
    public ApiResult<List<DictTableVo>> getDictTableControllerList(@Valid @RequestBody DictTableListParam dictTableListParam) throws Exception {
        List<DictTableVo> dictTableVoList = dictTableService.getDictTableServiceList(dictTableListParam);
        return ApiResult.ok(dictTableVoList);
    }

    @PostMapping("/pageList")
    @ApiOperation(value = "获取 系统数据字典的数据表 分页对象列表")
    public ApiResult<Paging<DictTableVo>> getDictTableControllerPageList(@Valid @RequestBody DictTablePageParam dictTablePageParam) throws Exception {
        Paging<DictTableVo> dictTableVoList = dictTableService.getDictTableServicePageList(dictTablePageParam);
        return ApiResult.ok(dictTableVoList);
    }

    @PostMapping("/count")
    @ApiOperation(value = "求 系统数据字典的数据表 对象的记录数")
    public ApiResult<Integer> countDictTableController(@Valid @RequestBody DictTableCountParam dictTableCountParam) throws Exception {
        Integer count = dictTableService.countDictTableService(dictTableCountParam);
        return ApiResult.ok(count);
    }


}


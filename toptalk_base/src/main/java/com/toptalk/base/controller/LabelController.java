package com.toptalk.base.controller;

import com.toptalk.base.pojo.Label;
import com.toptalk.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Description:  标签控制层
 * @Author: MasterCV
 * @Date: Created in  2018/9/25 19:04
 */
@RestController
@RequestMapping("/label")
public class LabelController {
    @Autowired
    private LabelService labelService;

    /**
     * 查询全部列表
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result<List> findAll() {
        return new Result<>(true, StatusCode.OK, "查询成功",
                labelService.findAll());
    }

    /**
     * 根据ID查询标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Result<Label> findById(@PathVariable String id) {
        return new Result<>(true, StatusCode.OK, "查询成功",labelService.findById(id));
    }

    /**
     * 增加标签
     * @param label
     *
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Label label) {
        labelService.add(label);
        return new Result(true, StatusCode.OK, "增加成功",null);
    }

    /**
     * 修改标签
     *
     * @param label
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public Result update(@RequestBody Label label, @PathVariable String
            id) {
        label.setId(id);
        labelService.update(label);
        return new Result(true, StatusCode.OK, "修改成功",null);
    }

    /**
     * 删除标签
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id) {
        labelService.deleteById(id);
        return new Result(true, StatusCode.OK, "删除成功",null);
    }

    /**
     * 根据条件查询;
     * @param searchMap
     * @return
     */
    @RequestMapping(value = "/search" , method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap){
        return new Result<>(true,StatusCode.OK,"查询成功",labelService.findSearch(searchMap));
    }

    @RequestMapping(value = "/search/{page}/{size}",method = RequestMethod.POST)
    public Result<List> findSearch(@RequestBody Map searchMap,@PathVariable int page, @PathVariable int size){
        Page pageList = labelService.findSearch(searchMap,page,size);
        return new Result<>(true,StatusCode.OK,"查询成功",new PageResult<>(pageList.getTotalElements(),pageList.getContent()));
    }


}


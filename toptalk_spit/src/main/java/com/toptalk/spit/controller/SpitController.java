package com.toptalk.spit.controller;

import com.toptalk.spit.pojo.Spit;
import com.toptalk.spit.service.SpitService;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 吐槽数据控制层;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 18:07
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    /**
     * 执行点赞操作;
     * @param id
     * @return
     */
    @RequestMapping(value = "/thumbup/{id}",method = RequestMethod.PUT)
    public Result updateThumbup(@PathVariable String id){
        spitService.updateThumbup(id);
        return new Result(true,StatusCode.OK,"点赞成功!");
    }

    /**
     * 根据上级 ID 查询下级吐槽分页数据;
     * @param parentId
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/comment/{parentId}/{page}/{size}",method = RequestMethod.GET)
    public Result findByParentid(@PathVariable String parentId, @PathVariable int page, @PathVariable int size){
        Page<Spit> spitPage = spitService.findByParentid(parentId, page, size);
        return new Result(true,StatusCode.OK,"查询成功",spitPage.getContent());
    }


    /**
     * 查询所有数据;
     * @return
     */
    @RequestMapping(method = RequestMethod.GET)
    public Result findAll(){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findAll());
    }

    /**
     * 根据 ID 查询数据;
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.GET)
    public Result findOne(@PathVariable String id){
        return new Result(true,StatusCode.OK,"查询成功",spitService.findById(id));
    }

    /**
     * 新增数据;
     * @param spit
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Spit spit){
        spitService.add(spit);
        return new Result(true,StatusCode.OK,"增加成功");
    }

    /**
     * 更新数据;
     * @param spit
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.PUT)
    public Result update(@RequestBody Spit spit,@PathVariable String id){
        spitService.update(spit);
        return new Result(true,StatusCode.OK,"修改成功");
    }

    /**
     * 根据 Id 删除数据;
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteByID(@PathVariable String id){
        spitService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功");
    }
}

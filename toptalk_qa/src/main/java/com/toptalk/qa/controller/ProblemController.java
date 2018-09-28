package com.toptalk.qa.controller;

import com.toptalk.qa.client.LabelClient;
import com.toptalk.qa.pojo.Problem;
import com.toptalk.qa.service.ProblemService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
/**
 * 控制器层
 * @author Administrator
 *
 */
@RestController
@CrossOrigin
@RequestMapping("/problem")
public class ProblemController {

	@Autowired
	private ProblemService problemService;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	private LabelClient labelClient;

	@RequestMapping(value = "/label/{labelid}",method = RequestMethod.GET)
	public Result findLabelById(@PathVariable String labelid){
		Result result = labelClient.findById(labelid);
		return result;
	}

	/**
	 * 根据标签id查询等待回答的问题列表;
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/waitlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
	public Result findHotListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findHotListByLabelId(labelid,page,size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(),pageList.getContent());
		return new Result(true,StatusCode.OK,"查询成功",pageResult);

	}

	/**
	 * 根据标签id查询最热问题列表;
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/hotlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
	public Result findWaitListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findWaitListByLabelId(labelid,page,size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(),pageList.getContent());
		return new Result(true,StatusCode.OK,"查询成功",pageResult);

	}

	/**
	 * 根据标签id查询最新问题列表;
	 * @param labelid
	 * @param page
	 * @param size
	 * @return
	 */
	@RequestMapping(value = "/newlist/{labelid}/{page}/{size}",method = RequestMethod.GET)
	public Result findNewListByLabelId(@PathVariable String labelid, @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findNewListByLabelId(labelid,page,size);
		PageResult<Problem> pageResult = new PageResult<>(pageList.getTotalElements(),pageList.getContent());
		return new Result(true,StatusCode.OK,"查询成功",pageResult);

	}

	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功",problemService.findById(id));
	}


	/**
	 * 分页+多条件查询
	 * @param searchMap 查询条件封装
	 * @param page 页码
	 * @param size 页大小
	 * @return 分页结果
	 */
	@RequestMapping(value="/search/{page}/{size}",method=RequestMethod.POST)
	public Result findSearch(@RequestBody Map searchMap , @PathVariable int page, @PathVariable int size){
		Page<Problem> pageList = problemService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Problem>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功",problemService.findSearch(searchMap));
    }
	
	/**
	 * 发布问题,需要验证user权限;
	 * @param problem
	 */
	@RequestMapping(method=RequestMethod.POST)
	public Result add(@RequestBody Problem problem  ){
		//需要验证是否是用户,游客不能发布问题;
		Claims claims = (Claims)request.getAttribute("user_claims");
		if(null == claims){
			return new Result(false,StatusCode.ACCESSERROR,"访问权限不足!");
		}
		problem.setUserid(claims.getId());
		problemService.add(problem);
		return new Result(true,StatusCode.OK,"增加成功");
	}
	
	/**
	 * 修改
	 * @param problem
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Problem problem, @PathVariable String id ){
		//需要验证是否是用户,游客不能执行操作;
		Claims claims = (Claims)request.getAttribute("user_claims");
		if(null == claims){
			return new Result(false,StatusCode.ACCESSERROR,"访问权限不足!");
		}
		problem.setId(id);
		problemService.update(problem);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		//需要验证是否是用户,游客不能执行操作;
		Claims claims = (Claims)request.getAttribute("user_claims");
		if(null == claims){
			return new Result(false,StatusCode.ACCESSERROR,"访问权限不足!");
		}
		problemService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}

package com.toptalk.friend.controller;

import com.toptalk.friend.pojo.Friend;
import com.toptalk.friend.service.FriendService;
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
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendService friendService;
	
	@Autowired
	private HttpServletRequest request;
	
	
	@RequestMapping(value = "/like/{friendid}/{type}",method = RequestMethod.PUT)
	public Result addFriend(@PathVariable String friendid,@PathVariable String type){
		//先校验操作权限;
		Claims claims = (Claims)request.getAttribute("user_claims");
		if (null == claims){
			return new Result(false,StatusCode.ACCESSERROR,"访问权限不足!");
		}
		//判断前端传过来的是哪个操作指令,"1"代表点击的是"喜欢"按钮,否则是"不喜欢"按钮;
		if (type.equals("1")){
			if (friendService.addFriend(claims.getId(),friendid) == 0){
				return new Result(false,StatusCode.REMOTEERROR,"已经添加过此好友!");
			}
		}else {
			// TODO: 2018/9/29 完成"不喜欢"按钮的操作; 
		}
		return new Result(true,StatusCode.OK,"操作成功!");
	}
	
	/**
	 * 查询全部数据
	 * @return
	 */
	@RequestMapping(method= RequestMethod.GET)
	public Result findAll(){
		return new Result(true,StatusCode.OK,"查询成功", friendService.findAll());
	}
	
	/**
	 * 根据ID查询
	 * @param id ID
	 * @return
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.GET)
	public Result findById(@PathVariable String id){
		return new Result(true,StatusCode.OK,"查询成功", friendService.findById(id));
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
		Page<Friend> pageList = friendService.findSearch(searchMap, page, size);
		return  new Result(true,StatusCode.OK,"查询成功",  new PageResult<Friend>(pageList.getTotalElements(), pageList.getContent()) );
	}

	/**
     * 根据条件查询
     * @param searchMap
     * @return
     */
    @RequestMapping(value="/search",method = RequestMethod.POST)
    public Result findSearch( @RequestBody Map searchMap){
        return new Result(true,StatusCode.OK,"查询成功", friendService.findSearch(searchMap));
    }
	
	/**
	 * 修改
	 * @param friend
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.PUT)
	public Result update(@RequestBody Friend friend, @PathVariable String id ){
		friend.setUserid(id);
		friendService.update(friend);		
		return new Result(true,StatusCode.OK,"修改成功");
	}
	
	/**
	 * 删除
	 * @param id
	 */
	@RequestMapping(value="/{id}",method= RequestMethod.DELETE)
	public Result delete(@PathVariable String id ){
		friendService.deleteById(id);
		return new Result(true,StatusCode.OK,"删除成功");
	}
	
}

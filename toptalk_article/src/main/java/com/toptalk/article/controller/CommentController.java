package com.toptalk.article.controller;

import com.toptalk.article.pojo.Comment;
import com.toptalk.article.service.CommentService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.CSS;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 20:07
 */
@RestController
@CrossOrigin
@RequestMapping(value = "/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest request;

    /**
     * 根据评论id删除对应的评论;
     * @param id
     * @return
     */
    @RequestMapping(value = "/{id}",method = RequestMethod.DELETE)
    public Result deleteById(@PathVariable String id){
        commentService.deleteById(id);
        return new Result(true,StatusCode.OK,"删除成功!");
    }

    /**
     * 根据文章 id 查询评论列表;
     * @param articleid
     * @return
     */
    @RequestMapping(value = "/article/{articleid}",method = RequestMethod.GET)
    public Result findByArticleid(@PathVariable String articleid){
        return new Result(true,StatusCode.OK,"查询成功",commentService.findByArticleid(articleid));
    }

    /**
     * 新增评论;
     * @param comment
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result add(@RequestBody Comment comment){
        //需要验证是否是用户,游客不能执行操作;
        Claims claims = (Claims)request.getAttribute("user_claims");
        if(null == claims){
            return new Result(false,StatusCode.ACCESSERROR,"访问权限不足!");
        }
        comment.setUserid(claims.getId());
        commentService.add(comment);
        return new Result(true,StatusCode.OK,"提交成功");
    }
}

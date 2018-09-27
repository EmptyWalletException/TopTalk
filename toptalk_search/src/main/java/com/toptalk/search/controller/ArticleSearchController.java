package com.toptalk.search.controller;

import com.toptalk.search.pojo.Article;
import com.toptalk.search.service.ArticleSearchService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/27 15:28
 */
@RestController
@CrossOrigin
@RequestMapping("/article")
public class ArticleSearchController {

    @Autowired
    private ArticleSearchService articleSearchService;

    /**
     * 根据关键字匹配标题或内容来搜索文章(分页);
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    @RequestMapping(value = "/search/{keywords}/{page}/{size}",method = RequestMethod.GET)
    public Result findByTitleOrContentLike(@PathVariable String keywords, @PathVariable int page ,@PathVariable int size){
        Page<Article> articlePage = articleSearchService.findByTitleOrContentLike(keywords,page,size);
        return new Result(true,StatusCode.OK,"查询成功!",
                new PageResult<Article>(articlePage.getTotalElements(),articlePage.getContent()));
    }

    /**
     * 新增文章;
     * @param article
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public Result save(@RequestBody Article article){
        articleSearchService.save(article);
        return new Result(true,StatusCode.OK,"新增成功!");
    }
}

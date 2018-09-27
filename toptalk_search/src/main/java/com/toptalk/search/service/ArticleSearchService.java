package com.toptalk.search.service;

import com.toptalk.search.dao.ArticleSearchDao;
import com.toptalk.search.pojo.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * @Description:  文章搜索业务逻辑层
 * @Author: MasterCV
 * @Date: Created in  2018/9/27 15:26
 */
@Service
public class ArticleSearchService {

    @Autowired
    private ArticleSearchDao articleSearchDao;

    /**
     * 根据标题或内容的关键字搜索文章(分页);
     * @param keywords
     * @param page
     * @param size
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String keywords, int page, int size){
        PageRequest pageRequest = PageRequest.of(page-1, size);
        return articleSearchDao.findByTitleOrContentLike(keywords,keywords,pageRequest);
    }

    /**
     * 增加文章
     * @param article
     */
    public void save(Article article){
        articleSearchDao.save(article);
    }
}

package com.toptalk.search.dao;

import com.toptalk.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @Description:    文章数据访问层接口;
 * @Author: MasterCV
 * @Date: Created in  2018/9/27 15:24
 */
public interface ArticleSearchDao extends ElasticsearchRepository<Article,String> {

    /**
     * 检索文章
     * @param title
     * @param content
     * @param pageable
     * @return
     */
    public Page<Article> findByTitleOrContentLike(String title, String content, Pageable pageable);
}

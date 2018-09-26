package com.toptalk.article.dao;

import com.toptalk.article.pojo.Comment;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * @Description:    评论数据dao接口;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 20:02
 */
public interface CommentDao extends MongoRepository<Comment,String> {

    /**
     * 根据文章 id 查询评论列表;
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleid(String articleid);

}

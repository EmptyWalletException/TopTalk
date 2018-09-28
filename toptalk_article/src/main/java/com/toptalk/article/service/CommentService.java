package com.toptalk.article.service;

import com.toptalk.article.dao.CommentDao;
import com.toptalk.article.pojo.Comment;
import util.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 20:04
 */
@Service
public class CommentService {

    @Autowired
    private CommentDao commentDao;

    @Autowired
    private IdWorker idWorker;

    /**
     * 根据文章 id 查询评论列表;
     * @param articleid
     * @return
     */
    public List<Comment> findByArticleid(String articleid){
        return commentDao.findByArticleid(articleid);
    }

    /**
     * 新增评论;
     * @param comment
     */
    public void add(Comment comment){
        comment.set_id(idWorker.nextId()+"");
        commentDao.save(comment);
    }

    /**
     * 删除评论;
     * @param id
     */
    public void deleteById(String id){
        commentDao.deleteById(id);
    }
}

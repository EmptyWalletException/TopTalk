package com.toptalk.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.article.pojo.Article;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ArticleDao extends JpaRepository<Article,String>,JpaSpecificationExecutor<Article>{

    /**
     * 点赞数+1;
     * @param id
     * @return
     */
    @Modifying
    @Query("update Article set thumbup=thumbup+1 where id=?1")
    public int updateThumbup(String id);

    /**
     * 审核文章,将文章状态设置为通过
     * @param id
     */
    @Modifying
    @Query("update Article set state='1' where id=?1")
    public void examine(String id);
	
}

package com.toptalk.qa.dao;

import com.toptalk.qa.pojo.Problem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ProblemDao extends JpaRepository<Problem,String>,JpaSpecificationExecutor<Problem>{

    /**
     * 根据标签 id 查询等待回答的问题列表;
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p " +
            "where id in(select problemid from Problem_Label where labelid=?1 ) and reply=0 " +
            "order by createtime desc")
    public Page<Problem> findWaitListByLabelId(String labelId, Pageable pageable);


    /**
     * 根据标签 id 查询最热的问题列表;
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p " +
            "where id in(select problemid from Problem_Label where labelid=?1 ) " +
            "order by reply desc")
    public Page<Problem> findHotListByLabelId(String labelId, Pageable pageable);

    /**
     * 根据标签 id 查询最新问题列表;
     * @param labelId
     * @param pageable
     * @return
     */
    @Query("select p from Problem p" +
            " where id in (select problemid from Problem_Label where labelid=?1 )" +
            " order by replytime desc")
    public Page<Problem> findNewListByLabelId(String labelId, Pageable pageable);
	
}

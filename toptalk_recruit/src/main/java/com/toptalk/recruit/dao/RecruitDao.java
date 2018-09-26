package com.toptalk.recruit.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.recruit.pojo.Recruit;

import java.util.List;

/**
 * 招聘 数据访问接口
 * @author Administrator
 *
 */
public interface RecruitDao extends JpaRepository<Recruit,String>,JpaSpecificationExecutor<Recruit>{

    /**
     * 根据状态不为state的查询前12个职位列表(用于获取最新职位,即状态不为'0'的,按创建日期降序排序)
     * @param state
     * @return
     */
    public List<Recruit> findTop12ByStateNotOrderByCreatetimeDesc(String state);


    /**
     * 根据状态查询前4个职位列表(用于查询推荐职位,按创建日期降序排序)
     * @param state
     * @return
     */
    public List<Recruit> findTop4ByStateOrderByCreatetimeDesc(String state);


}

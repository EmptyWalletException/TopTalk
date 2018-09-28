package com.toptalk.user.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.user.pojo.Admin;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface AdminDao extends JpaRepository<Admin,String>,JpaSpecificationExecutor<Admin>{

    /**
     * 根据登录名查询管理员数据;
     * @param loginname
     * @return
     */
    public Admin findByLoginname(String loginname);
}

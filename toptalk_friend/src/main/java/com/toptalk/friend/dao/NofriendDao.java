package com.toptalk.friend.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.friend.pojo.Nofriend;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface NofriendDao extends JpaRepository<Nofriend,String>,JpaSpecificationExecutor<Nofriend>{
	
}

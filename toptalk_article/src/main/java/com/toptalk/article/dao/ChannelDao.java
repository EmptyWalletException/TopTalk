package com.toptalk.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.article.pojo.Channel;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ChannelDao extends JpaRepository<Channel,String>,JpaSpecificationExecutor<Channel>{
	
}

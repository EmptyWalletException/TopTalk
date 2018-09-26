package com.toptalk.article.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.toptalk.article.pojo.Column;
/**
 * 数据访问接口
 * @author Administrator
 *
 */
public interface ColumnDao extends JpaRepository<Column,String>,JpaSpecificationExecutor<Column>{
	
}

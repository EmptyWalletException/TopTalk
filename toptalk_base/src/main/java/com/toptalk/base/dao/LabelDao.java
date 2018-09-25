package com.toptalk.base.dao;

import com.toptalk.base.pojo.Label;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @Description:  标签实体类的数据访问接口;
 * @Author: MasterCV
 * @Date: Created in  2018/9/25 18:57
 */
public interface LabelDao extends JpaRepository<Label,String>,JpaSpecificationExecutor<Label> {
}

package com.toptalk.spit.dao;

import com.toptalk.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @Description:  吐槽数据的访问层;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 17:57
 */
public interface SpitDao extends MongoRepository<Spit,String> {

    /**
     * 根据上级 ID 查询吐槽列表(分页);
     * @param parentid
     * @param pageable
     * @return
     */
    public Page<Spit> findByParentid(String parentid, Pageable pageable);
}

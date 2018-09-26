package com.toptalk.spit.service;

import com.toptalk.spit.dao.SpitDao;
import com.toptalk.spit.pojo.Spit;
import entity.IdWorker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Description:  吐槽数据的业务逻辑类;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 17:59
 */
@Service
public class SpitService {

    @Autowired
    private SpitDao spitDao;

    @Autowired
    private IdWorker idWorker;

    @Autowired
    private MongoTemplate mongoTemplate;//mongoDB的模板用于操作mongoDB里的数据;

    /**
     * 新增吐槽或吐槽的子评论;
     * @param spit
     */
    public void add(Spit spit){
        //遵循尽量减少层级之间传递数值的数量原则,下面的初始化操作不放在controller层
        spit.set_id(idWorker.nextId()+"");
        spit.setPublishtime(new Date());
        spit.setVisits(0);
        spit.setShare(0);
        spit.setShare(0);
        spit.setThumbup(0);
        spit.setComment(0);
        spit.setState("1");
        //如果从controller层传过来时带了父评论的id,则将对应的父评论的评论计数加一;
        if (null != spit.getParentid() && "".equals(spit.getParentid())){
            Query query = new Query();
            query.addCriteria(Criteria.where("_id").is(spit.getParentid()));

            Update update = new Update();
            update.inc("comment",1);//将父评论的评论计数加一;
            mongoTemplate.updateFirst(query,update,"spit");
        }
        spitDao.save(spit);
    }

    /**
     * 利用 MongoDB 执行点赞操作,此方法比以往 "先查询到再将点赞数加1最后更新"的效率要高;
     * @param id
     */
    public void updateThumbup(String id){
        Query query = new Query();
        query.addCriteria(Criteria.where("_id").is(id));//匹配 id 进行查询;

        Update update = new Update();
        update.inc("thumbup",1); //将文档中的属性增加1,

        mongoTemplate.updateFirst(query,update,"spit");
    }

    /**
     * 根据上级 ID 查询下级吐槽列表(分页);
     * @param parentid
     * @param page
     * @param size
     * @return
     */
    public Page<Spit> findByParentid(String parentid, int page , int size){
        return spitDao.findByParentid(parentid, PageRequest.of(page,size));
    }

    /**
     * 查询所有记录;
     * @return
     */
    public List<Spit> findAll(){
        return spitDao.findAll();
    }

    /**
     * 根据 id 查询单条记录;
     * @param id
     * @return
     */
    public Spit findById(String id){
        Spit spit = spitDao.findById(id).get();
        return spit;
    }

    /**
     * 更新数据;
     * @param spit
     */
    public void update(Spit spit){
        spitDao.save(spit);
    }

    /**
     * 根据 id 删除数据;
     * @param id
     */
    public void deleteById(String id){
        spitDao.deleteById(id);
    }
}

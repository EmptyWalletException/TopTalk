package com.toptalk.friend.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import com.toptalk.friend.client.UserClient;
import com.toptalk.friend.dao.NofriendDao;
import com.toptalk.friend.pojo.Nofriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import util.IdWorker;

import com.toptalk.friend.dao.FriendDao;
import com.toptalk.friend.pojo.Friend;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class FriendService {

	@Autowired
	private FriendDao friendDao;

	@Autowired
	private NofriendDao nofriendDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private UserClient userClient;

	/**
	 * 删除好友;
	 * @param userid
	 * @param friendid
	 */
	@Transactional
	public void deleteFriend(String userid, String friendid){
		//删除好友表中的记录
		friendDao.deleteFriend(userid,friendid);
		//将对方关于是否是互相喜欢的记录设置为否;
		friendDao.updateLike(friendid,userid,"0");
		//将自己的关注数-1;
		userClient.incFollowcount(userid,-1);
		//将对方的粉丝数-1;
		userClient.incFanscount(friendid,-1);
	}

	/**
	 * 新增不喜欢好友;
	 * @param userid
	 * @param friendid
	 */
	public void addNoFriend(String userid, String friendid){
		Nofriend nofriend = new Nofriend();
		nofriend.setUserid(userid);
		nofriend.setFriendid(friendid);
		nofriendDao.save(nofriend);
	}

	/**
	 * 新增好友;
	 * @param userid
	 * @param friendid
	 * @return
	 */
	@Transactional
	public int addFriend(String userid, String friendid){
		//判断如果用户已经添加了此好友,则不进行任何操作,返回0;
		if (friendDao.selectCount(userid,friendid) >0){
			return 0;
		}
		Friend friend = new Friend();
		friend.setUserid(userid);
		friend.setFriendid(friendid);
		friend.setIslike("0");
		friendDao.save(friend);
		userClient.incFollowcount(userid,1);//将自己的关注数加1;
		userClient.incFanscount(friendid,1);//将对方的粉丝数加1;

		//判断对方是否喜欢你,如果是则将islike设置为1;
		if (friendDao.selectCount(friendid,userid) >0){
			friendDao.updateLike(userid,friendid,"1");
			friendDao.updateLike(friendid,userid,"1");
		}
		return 1;
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<Friend> findAll() {
		return friendDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<Friend> findSearch(Map whereMap, int page, int size) {
		Specification<Friend> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return friendDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<Friend> findSearch(Map whereMap) {
		Specification<Friend> specification = createSpecification(whereMap);
		return friendDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public Friend findById(String id) {
		return friendDao.findById(id).get();
	}

	/**
	 * 修改
	 * @param friend
	 */
	public void update(Friend friend) {
		friendDao.save(friend);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		friendDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<Friend> createSpecification(Map searchMap) {

		return new Specification<Friend>() {

			@Override
			public Predicate toPredicate(Root<Friend> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // 用户ID
                if (searchMap.get("userid")!=null && !"".equals(searchMap.get("userid"))) {
                	predicateList.add(cb.like(root.get("userid").as(String.class), "%"+(String)searchMap.get("userid")+"%"));
                }
                // 好友ID
                if (searchMap.get("friendid")!=null && !"".equals(searchMap.get("friendid"))) {
                	predicateList.add(cb.like(root.get("friendid").as(String.class), "%"+(String)searchMap.get("friendid")+"%"));
                }
                // 是否互相喜欢
                if (searchMap.get("islike")!=null && !"".equals(searchMap.get("islike"))) {
                	predicateList.add(cb.like(root.get("islike").as(String.class), "%"+(String)searchMap.get("islike")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}

package com.toptalk.user.service;

import com.toptalk.user.dao.UserDao;
import com.toptalk.user.pojo.User;
import util.IdWorker;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.*;
import java.util.concurrent.TimeUnit;

/**
 * 服务层
 * 
 * @author Administrator
 *
 */
@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private IdWorker idWorker;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RedisTemplate redisTemplate;

	@Autowired
	private RabbitTemplate rabbitTemplate;

	/**
	 * 根据手机号和密码查询用户;
	 * @param mobile
	 * @param password
	 * @return
	 */
	public User findByMobileAndPassword(String mobile,String password){
		User user = userDao.findByMobile(mobile);
		if(null != user && bCryptPasswordEncoder.matches(password,user.getPassword())){
			return user;
		}else {
			return null;
		}
	}

	/**
	 * 新增用户;
	 * @param user
	 * @param code
	 */
	public void add(User user,String code){
		//提取系统正确的验证码;
		String syscode = (String)redisTemplate.opsForValue().get("smscode_" + user.getMobile());
		//判断验证码是否正确
		if(null == syscode){
			throw new RuntimeException("请点击获取短信验证码!");
		}
		if(!syscode.equals(code)){
			throw new RuntimeException("验证码不正确!");
		}
		user.setId( idWorker.nextId()+"");
		//将用户注册时填的密码进行加密;
		String newpassword = bCryptPasswordEncoder.encode(user.getPassword());
		user.setPassword(newpassword);
		user.setFollowcount(0);//设置关注数;
		user.setFanscount(0);//设置粉丝数;
		user.setOnline(0L);//设置在线时长;
		user.setRegdate(new Date());//设置注册日期;
		user.setUpdatedate(new Date());//设置更新日期;
		user.setLastdate(new Date());//设置最后登录日期;

		userDao.save(user);
	}

	/**
	 * 发送短信验证码;
	 * @param mobile
	 */
	public void sendSms(String mobile){
		Random random = new Random();
		int code = random.nextInt(899999)+100000; //生成大于100000且小于999999的随机数;

		System.out.println(mobile + "对应的验证码为: " + code);

		//将验证码放入redis,设置过期时间为5分钟;
		redisTemplate.opsForValue().set("smscode_" + mobile, code+"" , 5, TimeUnit.MINUTES);

		//将验证码和手机号发布到rabbitMQ中;
		Map<String,String> map = new HashMap<>();
		map.put("mobile",mobile);
		map.put("code",code+"");
		rabbitTemplate.convertAndSend("sms",map);//需要启动微服务在rabbitMQ中创建名为sms的队列;
	}

	/**
	 * 查询全部列表
	 * @return
	 */
	public List<User> findAll() {
		return userDao.findAll();
	}

	
	/**
	 * 条件查询+分页
	 * @param whereMap
	 * @param page
	 * @param size
	 * @return
	 */
	public Page<User> findSearch(Map whereMap, int page, int size) {
		Specification<User> specification = createSpecification(whereMap);
		PageRequest pageRequest =  PageRequest.of(page-1, size);
		return userDao.findAll(specification, pageRequest);
	}

	
	/**
	 * 条件查询
	 * @param whereMap
	 * @return
	 */
	public List<User> findSearch(Map whereMap) {
		Specification<User> specification = createSpecification(whereMap);
		return userDao.findAll(specification);
	}

	/**
	 * 根据ID查询实体
	 * @param id
	 * @return
	 */
	public User findById(String id) {
		return userDao.findById(id).get();
	}

	/**
	 * 增加
	 * @param user
	 */
	public void add(User user) {
		user.setId( idWorker.nextId()+"" );
		userDao.save(user);
	}

	/**
	 * 修改
	 * @param user
	 */
	public void update(User user) {
		userDao.save(user);
	}

	/**
	 * 删除
	 * @param id
	 */
	public void deleteById(String id) {
		userDao.deleteById(id);
	}

	/**
	 * 动态条件构建
	 * @param searchMap
	 * @return
	 */
	private Specification<User> createSpecification(Map searchMap) {

		return new Specification<User>() {

			@Override
			public Predicate toPredicate(Root<User> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
				List<Predicate> predicateList = new ArrayList<Predicate>();
                // ID
                if (searchMap.get("id")!=null && !"".equals(searchMap.get("id"))) {
                	predicateList.add(cb.like(root.get("id").as(String.class), "%"+(String)searchMap.get("id")+"%"));
                }
                // 密码
                if (searchMap.get("password")!=null && !"".equals(searchMap.get("password"))) {
                	predicateList.add(cb.like(root.get("password").as(String.class), "%"+(String)searchMap.get("password")+"%"));
                }
                // 昵称
                if (searchMap.get("nickname")!=null && !"".equals(searchMap.get("nickname"))) {
                	predicateList.add(cb.like(root.get("nickname").as(String.class), "%"+(String)searchMap.get("nickname")+"%"));
                }
                // 性别
                if (searchMap.get("sex")!=null && !"".equals(searchMap.get("sex"))) {
                	predicateList.add(cb.like(root.get("sex").as(String.class), "%"+(String)searchMap.get("sex")+"%"));
                }
                // 头像
                if (searchMap.get("avatar")!=null && !"".equals(searchMap.get("avatar"))) {
                	predicateList.add(cb.like(root.get("avatar").as(String.class), "%"+(String)searchMap.get("avatar")+"%"));
                }
                // EMail
                if (searchMap.get("email")!=null && !"".equals(searchMap.get("email"))) {
                	predicateList.add(cb.like(root.get("email").as(String.class), "%"+(String)searchMap.get("email")+"%"));
                }
                // 兴趣
                if (searchMap.get("interest")!=null && !"".equals(searchMap.get("interest"))) {
                	predicateList.add(cb.like(root.get("interest").as(String.class), "%"+(String)searchMap.get("interest")+"%"));
                }
                // 个性
                if (searchMap.get("personality")!=null && !"".equals(searchMap.get("personality"))) {
                	predicateList.add(cb.like(root.get("personality").as(String.class), "%"+(String)searchMap.get("personality")+"%"));
                }
                // 
                if (searchMap.get("loginname")!=null && !"".equals(searchMap.get("loginname"))) {
                	predicateList.add(cb.like(root.get("loginname").as(String.class), "%"+(String)searchMap.get("loginname")+"%"));
                }
				
				return cb.and( predicateList.toArray(new Predicate[predicateList.size()]));

			}
		};

	}

}

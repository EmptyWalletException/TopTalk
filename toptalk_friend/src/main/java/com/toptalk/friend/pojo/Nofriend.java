package com.toptalk.friend.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
/**
 * 实体类
 * @author Administrator
 *
 */
@Entity
@Table(name="tb_nofriend")
public class Nofriend implements Serializable{

	@Id
	private String userid;//


	
	private String friendid;//

	
	public String getUserid() {		
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getFriendid() {		
		return friendid;
	}
	public void setFriendid(String friendid) {
		this.friendid = friendid;
	}


	
}

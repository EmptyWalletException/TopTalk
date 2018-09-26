package com.toptalk.article.pojo;

import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

/**
 * @Description:  评论实体类,映射的MongoDB的文档数据;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 19:54
 */
public class Comment implements Serializable {

    @Id
    private String _id;
    private String articleid; //所属文章id
    private String content; //评论内容
    private String userid;  //评论人id
    private String parentid;    //父评论id,为0表示是顶级评论
    private Date publishdate;   //评论发布日期

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getArticleid() {
        return articleid;
    }

    public void setArticleid(String articleid) {
        this.articleid = articleid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getParentid() {
        return parentid;
    }

    public void setParentid(String parentid) {
        this.parentid = parentid;
    }

    public Date getPublishdate() {
        return publishdate;
    }

    public void setPublishdate(Date publishdate) {
        this.publishdate = publishdate;
    }
}

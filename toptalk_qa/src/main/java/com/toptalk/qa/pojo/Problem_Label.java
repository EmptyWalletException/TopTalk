package com.toptalk.qa.pojo;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @Description: 中间表,用于记录问题与标签的关联关系;
 * @Author: MasterCV
 * @Date: Created in  2018/9/26 10:44
 */
@Entity
@Table(name = "tb_problem_label")
public class Problem_Label implements Serializable {

    @Id
    private String problemid;

    @Id
    private String lableid;

    public String getProblemid() {
        return problemid;
    }

    public void setProblemid(String problemid) {
        this.problemid = problemid;
    }

    public String getLableid() {
        return lableid;
    }

    public void setLableid(String lableid) {
        this.lableid = lableid;
    }
}

package com.toptalk.web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description:
 * @Author: MasterCV
 * @Date: Created in  2018/10/1 10:26
 */
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    /**
     * 主要用于首页跳转
     * @param registry
     */
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/acticle-aboutUs.html").setViewName("acticle/acticle-aboutUs");
        registry.addViewController("/acticle-contactus.html").setViewName("acticle/acticle-contactus");
        registry.addViewController("/acticle-editorGrammar.html").setViewName("acticle/acticle-editorGrammar");
        registry.addViewController("/acticle-feedback.html").setViewName("acticle/acticle-feedback");
        registry.addViewController("/acticle-helpCenter.html").setViewName("acticle/acticle-helpCenter");
        registry.addViewController("/acticle-joinUs.html").setViewName("acticle/acticle-joinUs");
        registry.addViewController("/acticle-service.html").setViewName("acticle/acticle-service");

        registry.addViewController("/makeFriends-edit.html").setViewName("friend/makeFriends-edit");
        registry.addViewController("/makeFriends-index.html").setViewName("friend/makeFriends-index");
        registry.addViewController("/makeFriends-list.html").setViewName("friend/makeFriends-list");
        registry.addViewController("/makeFriends-submit.html").setViewName("friend/makeFriends-submit");
        registry.addViewController("/makeFriends-talk.html").setViewName("friend/makeFriends-talk");

        registry.addViewController("/activity-detail.html").setViewName("gathering/activity-detail");
        registry.addViewController("/activity-index.html").setViewName("gathering/activity-index");

        registry.addViewController("/headline-logined.html").setViewName("headline/headline-logined");
        registry.addViewController("/headline-login.html").setViewName("headline/headline-login");
        registry.addViewController("/headline-submit.html").setViewName("headline/headline-submit");
        registry.addViewController("/headline-detail.html").setViewName("headline/headline-detail");
        registry.addViewController("/headline-column.html").setViewName("headline/headline-column");

        registry.addViewController("/").setViewName("index/index");
        registry.addViewController("/index").setViewName("index/index");
        registry.addViewController("/index.html").setViewName("index/index");

        registry.addViewController("/person-account.html").setViewName("person/person-index");
        registry.addViewController("/person-dynamic.html").setViewName("person/person-dynamic");
        registry.addViewController("/person-homepage.html").setViewName("person/person-homepage");
        registry.addViewController("/person-loginsign.html").setViewName("person/person-loginsign");
        registry.addViewController("/person-myanswer.html").setViewName("person/person-myanswer");
        registry.addViewController("/person-mycollect.html").setViewName("person/person-mycollect");
        registry.addViewController("/person-myfile.html").setViewName("person/person-myfile");
        registry.addViewController("/person-myfocus.html").setViewName("person/person-myfocus");
        registry.addViewController("/person-myfocus.html").setViewName("person/person-myfocus");
        registry.addViewController("/person-myquestion.html").setViewName("person/person-myquestion");
        registry.addViewController("/person-myreaded.html").setViewName("person/person-myreaded");
        registry.addViewController("/person-myshare.html").setViewName("person/person-myshare");

        registry.addViewController("/qa-allTag.html").setViewName("qa/qa-allTag");
        registry.addViewController("/qa-customTag.html").setViewName("qa/qa-customTag");
        registry.addViewController("/qa-detail.html").setViewName("qa/qa-detail");
        registry.addViewController("/qa-login.html").setViewName("qa/qa-login");
        registry.addViewController("/qa-logined.html").setViewName("qa/qa-logined");
        registry.addViewController("/qa-submit.html").setViewName("qa/qa-submit");
        registry.addViewController("/qa-tagDetail.html").setViewName("qa/qa-tagDetail");

        registry.addViewController("/recruit-area.html").setViewName("recruit/recruit-area");
        registry.addViewController("/recruit-company.html").setViewName("recruit/recruit-company");
        registry.addViewController("/recruit-detail.html").setViewName("recruit/recruit-detail");
        registry.addViewController("/recruit-index.html").setViewName("recruit/recruit-index");
        registry.addViewController("/recruit-job.html").setViewName("recruit/recruit-job");

        registry.addViewController("/other-notice.html").setViewName("sms/other-notice");

        registry.addViewController("/spit-detail.html").setViewName("spit/spit-detail");
        registry.addViewController("/spit-index.html").setViewName("spit/spit-index");
        registry.addViewController("/spit-submit.html").setViewName("spit/spit-submit");


    }


}

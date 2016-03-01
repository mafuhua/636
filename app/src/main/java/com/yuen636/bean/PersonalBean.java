package com.yuen636.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2016/2/26.
 */
public class PersonalBean {


    /**
     * code : 0
     * msg : User 查询成功
     * data : {"user_id":"3","user_img":"uploads/userimg/20160104/20160105145196106640.jpg","nick_name":"毓恩科技","name":"trr","phone":"345345","wechat":"3453453","company":"诺基亚公司","trade":"商业贸易","job":"try","jobshow":"1","web":"","address":"3","address2":"","intro":"","tag":["tt","yy","uuu"],"class":"苹果公司","fenweihui":"公社管理员,汽车学院,人工智能分委会"}
     * data2 : ["苹果公司","诺基亚公司"]
     */

    public String code;
    public String msg;
    /**
     * user_id : 3
     * user_img : uploads/userimg/20160104/20160105145196106640.jpg
     * nick_name : 毓恩科技
     * name : trr
     * phone : 345345
     * wechat : 3453453
     * company : 诺基亚公司
     * trade : 商业贸易
     * job : try
     * jobshow : 1
     * web :
     * address : 3
     * address2 :
     * intro :
     * tag : ["tt","yy","uuu"]
     * class : 苹果公司
     * fenweihui : 公社管理员,汽车学院,人工智能分委会
     */

    public DataEntity data;
    public List<String> data2;

    public static class DataEntity {
        public String user_id;
        public String user_img;
        public String nick_name;
        public String name;
        public String phone;
        public String wechat;
        public String company;
        public String trade;
        public String job;
        public String jobshow;
        public String web;
        public String address;
        public String address2;
        public String intro;
        @SerializedName("class")
        public String classX;
        public String fenweihui;
        public List<String> tag;
    }
}

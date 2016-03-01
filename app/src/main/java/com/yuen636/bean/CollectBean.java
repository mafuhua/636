package com.yuen636.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/26.
 */
public class CollectBean {


    /**
     * code : 0
     * msg : collect 查询成功
     * data : [{"id":"1","user_id":"3","type_id":"1","collect_id":"1","time":"2016-02-20 11:53:15","user":"人工智能分委会","user_img":"uploads/organization/20160109/20160107145213325024.png","organization":"人工智能分委会","position":"管理员","collect":{"id":"1","user_id":"","title":"研究称人工智能20年","describe":"据香港《明报》网近日报道，野村综研和两名牛津大学研究员合作，对日本601种职业（劳动人口约达4280","organization":"9","time":"2015-12-28 09:40"}},{"id":"2","user_id":"3","type_id":"1","collect_id":"2","time":"2016-02-20 11:53:15","user":"足球分委会","user_img":"uploads/organization/20160109/20160107145213325024.png","organization":"足球分委会","position":"管理员","collect":{"id":"2","user_id":"","title":"欧冠C罗靠啥PK梅西","describe":"2013和2014年，C罗连续两届FIFA金球奖称王。2015年，C罗职业生涯第8次入选金球奖前3，","organization":"10","time":"2016-01-06 08:16"}},{"id":"3","user_id":"3","type_id":"4","collect_id":"3","time":"2016-02-20 11:53:15","user":"trr","user_img":"uploads/userimg/20160104/20160105145196106640.jpg","organization":"科技研究会","position":"会员","collect":{"id":"3","user_id":"3","title":"主题标题1","describe":"主题描述1","organization":"5","time":"2016-02-18 11:17"}},{"id":"4","user_id":"3","type_id":"1","collect_id":"3","time":"2016-02-20 11:53:15","user":"股市分委会","user_img":"uploads/organization/20160109/20160107145213325025.png","organization":"股市分委会","position":"管理员","collect":{"id":"3","user_id":"","title":"今起暂停实施熔断机制","describe":"原标题：今日起暂停实施熔断机制","organization":"4","time":"2016-01-08 07:46"}}]
     */

    public String code;
    public String msg;
    /**
     * id : 1
     * user_id : 3
     * type_id : 1
     * collect_id : 1
     * time : 2016-02-20 11:53:15
     * user : 人工智能分委会
     * user_img : uploads/organization/20160109/20160107145213325024.png
     * organization : 人工智能分委会
     * position : 管理员
     * collect : {"id":"1","user_id":"","title":"研究称人工智能20年","describe":"据香港《明报》网近日报道，野村综研和两名牛津大学研究员合作，对日本601种职业（劳动人口约达4280","organization":"9","time":"2015-12-28 09:40"}
     */

    public List<DataEntity> data;

    public static class DataEntity {
        public String id;
        public String user_id;
        public String type_id;
        public String collect_id;
        public String time;
        public String user;
        public String user_img;
        public String organization;
        public String position;
        /**
         * id : 1
         * user_id :
         * title : 研究称人工智能20年
         * describe : 据香港《明报》网近日报道，野村综研和两名牛津大学研究员合作，对日本601种职业（劳动人口约达4280
         * organization : 9
         * time : 2015-12-28 09:40
         */

        public CollectEntity collect;

        public static class CollectEntity {
            public String id;
            public String user_id;
            public String title;
            public String describe;
            public String organization;
            public String time;
        }
    }
}

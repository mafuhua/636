package com.yuen636.bean;

import java.util.List;

/**
 * Created by Administrator on 2016/2/26.
 */
public class ActiveBean {


    /**
     * code : 0
     * msg : 查询成功
     * data : [{"id":"7","title":"ww","atime":"2016-03-01 00:00"}]
     */

    public String code;
    public String msg;
    /**
     * id : 7
     * title : ww
     * atime : 2016-03-01 00:00
     */

    public List<DataEntity> data;

    public static class DataEntity {
        public String id;
        public String title;
        public String atime;
    }



}

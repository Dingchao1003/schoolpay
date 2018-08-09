package com.dingchao.schoolpay.shrio.entity;

/**
 * \* Created with IntelliJ IDEA.
 * \* User: dingchao
 * \* Date: 2018/8/9
 * \* Time: 下午5:17
 * \* To change this template use File | Settings | File Templates.
 * \* Description:
 * \
 */

public class user {

    private String userid;
    private String uid;
    private String abbr;

    public user(String userid, String uid, String abbr) {
        this.userid = userid;
        this.uid = uid;
        this.abbr = abbr;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAbbr() {
        return abbr;
    }

    public void setAbbr(String abbr) {
        this.abbr = abbr;
    }

    @Override
    public String toString() {
        return "user{" +
                "userid='" + userid + '\'' +
                ", uid='" + uid + '\'' +
                ", abbr='" + abbr + '\'' +
                '}';
    }
}

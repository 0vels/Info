package cn.school.domain;

import java.util.List;

/**
 * Created by wang on 2017/5/25.
 */
public class TongzhiSendto extends TongZhi {

    private List<String> sendTolist;//接收群体


    public List<String> getSendTolist() {
        return sendTolist;
    }

    public void setSendTolist(List<String> sendTolist) {
        this.sendTolist = sendTolist;
    }
}

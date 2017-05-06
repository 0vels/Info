package cn.school.domain;

import java.io.Serializable;

/**
 * Created by wang on 2017/5/4.
 */
public class sendTo implements Serializable {

    private String sendTo;//



    public sendTo(){

    }

    public String getSendTo() {
        return sendTo;
    }

    public void setSendTo(String sendTo) {
        this.sendTo = sendTo;
    }

    @Override
    public String toString() {
        return "sendTo{" +
                "sendTo='" + sendTo + '\'' +
                '}';
    }
}

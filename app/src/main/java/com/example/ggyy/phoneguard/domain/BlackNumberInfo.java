package com.example.ggyy.phoneguard.domain;

/**
 * Created by T430 on 2016/11/28.
 */

public class BlackNumberInfo {
    /**
     * 黑名单号码,两个属性 Number和 MODE
     */
    private String number;
    private String mode;

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getMode() {
        return mode;
    }

    public void setMode(String mode) {
        this.mode = mode;
    }
}

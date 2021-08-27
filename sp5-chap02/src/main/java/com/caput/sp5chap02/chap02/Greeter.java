package com.caput.sp5chap02.chap02;

public class Greeter {
    private String format;

    public String greet(String guest){
        return String.format(format, guest);
    }

    public void setFormat(String format){
        this.format = format;
    }

}

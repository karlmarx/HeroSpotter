package com.karlmarxindustries.herospotter;

import com.karlmarxindustries.herospotter.service.ServiceImpl;

public class TestClass {


    public static void main(String[] args) {
        ServiceImpl serviceImpl = new ServiceImpl();
        String censored = serviceImpl.censorString("fuck off you fucking piece of shit");
        System.out.println(censored);
    }

}

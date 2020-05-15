package com.leaf.plugin.util;


import java.util.Random;

public class RandomUtil {
	
	public static String getRandomString(int length) { 
        String base = "0123456789abcdefghijklmnopqrstuvwxyz0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ!@#$^&*";       
        Random random = new Random();       
        StringBuffer sb = new StringBuffer();       
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(base.length());       
            sb.append(base.charAt(number));       
        }       
        return sb.toString();       
    }
	
	public static void main(String[] args) {
		//System.out.println(RandomUtil.getRandomString(18));
	}
}

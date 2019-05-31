package com.ghj.client;

import java.io.IOException;

/**
 * 增加博客访问量
 * 
 * @author 高焕杰
 */
public class Client extends Thread {
	
	static int rate = 60;//单位：秒
	static String [] ids = {
			"87709258", "88593236","88594321", "88654571",
			"82823379","81735762", "88593742", "82084167",
			"87557249", "81835546","89817433"};
	static String preffix = "https://blog.csdn.net/wang_da_bing/article/details/";
	static String path = "/opt/google/chrome/google-chrome";
	
	public static void visit(){
		for (int i = 1; i <= ids.length; i++) {
			try {
				if(i%10==0){//浏览器最多打开10个页面
					try {
						Runtime.getRuntime().exec(path+" "+preffix+ids[i-1]);
						//System.out.println("打开第"+i+"个页面。");
						Thread.sleep(1000*rate);
						Runtime.getRuntime().exec("taskkill /F /IM " + path.substring(path.lastIndexOf("\\")+1));
						Thread.sleep(1000*5);
					} catch (IOException e) {
						e.printStackTrace();
					}
				}else{
					Runtime.getRuntime().exec(path+" "+preffix+ids[i-1]);
					//System.out.println("打开第"+i+"个页面。");
					Thread.sleep(1000*2);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args){
		int i=1;
		while(true){
			System.out.println("第"+i+"次循环。");
			visit(); 
			try {
				Runtime.getRuntime().exec("taskkill /F /IM "+ path.substring(path.lastIndexOf("\\")+1));
				Thread.sleep(1000*10);
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InterruptedException e) {
				e.printStackTrace();
			} 
			i++;
	    }
	}
}
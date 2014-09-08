package com.cos.model;

import java.util.Map;

import com.cos.util.Utility;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LoginManager lm=new LoginManager();
		String logReq=lm.createcOSmoLoginRequest("jmartin", "jmartin");
		String cosRes=Utility.sendToCos(logReq);
		System.out.println(cosRes);
		try {
			Map m=lm.parsecOSLoginResponse(cosRes, "jmartin");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

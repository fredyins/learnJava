package com.coderising.ood.srp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DBUtil {
	
	/**
	 * Ӧ�ô����ݿ���� ���Ǽ�Ϊֱ�����ɡ�
	 * 
	 * @param sql
	 * @return
	 */
	public static List query(String sql){
		
		List userList = new ArrayList();
		for (int i = 1; i <= 3; i++) {
			HashMap userInfo = new HashMap();
			userInfo.put("NAME", "User" + i);			
			userInfo.put("EMAIL", "aa@bb.com");
			userList.add(userInfo);
		}

		return userList;
	}
}

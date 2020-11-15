package util;

import java.math.BigInteger;
import java.security.MessageDigest;

public class SHAUtil {
	public static final String KEY_SHA = "SHA";

	public static String process(String password) throws Exception {
		byte[] shaBytes = null;
		String shaStr = null;
		
		System.out.println("原始密码: " + password);
		byte[] passwordBytes = password.getBytes();
		try {
			MessageDigest messageDigest = MessageDigest.getInstance(KEY_SHA);
			messageDigest.update(passwordBytes);
			shaBytes = messageDigest.digest();
			shaStr = new BigInteger(shaBytes).toString();
			
			System.out.println("SHA加密后:" + shaStr);
			return shaStr;
		} catch (Exception e) {
			throw e;
		}
	}

	public static void main(String args[]) {
		try {
			String password = "HelloWorld";
			password = process(password);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}

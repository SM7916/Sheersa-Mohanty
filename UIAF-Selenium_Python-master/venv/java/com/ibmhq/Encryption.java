
package com.ibmhq;

import java.util.Base64;

public class Encryption {

	static String encodepassword = "Aep!1015";

	public static String getEncodedpassword() {
		return new String(Base64.getEncoder().encode(encodepassword.getBytes()));

	}

	public static void main(String[] args) {

		System.out.println(getEncodedpassword());
	}

}

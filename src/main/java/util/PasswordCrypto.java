package util;

import org.mindrot.jbcrypt.BCrypt;

public class PasswordCrypto {
	
	// 암호화
	public static String encrypt(String password) {
		return BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	// 복호화
	public static boolean decrypt(String password, String encryptedPassword) {
		return BCrypt.checkpw(password, encryptedPassword);
	}

}

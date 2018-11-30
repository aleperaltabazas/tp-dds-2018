package DDS.SGE.Web;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class HashProvider {

	private static String byteToHexString(byte[] input) {
		String output = "";
		for (int i = 0; i < input.length; ++i) {
			output += String.format("%02X", input[i]);
		}
		return output;
	}

	public static String hash(String password) {
		try {
			MessageDigest md = MessageDigest.getInstance("MD5");
			md.update(password.getBytes(), 0, password.length());
			return byteToHexString(md.digest());
		} catch (NoSuchAlgorithmException except) {
			except.printStackTrace();
			return null;
		}
	}

}

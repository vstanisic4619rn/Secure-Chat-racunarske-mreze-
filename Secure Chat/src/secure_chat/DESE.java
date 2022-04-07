package secure_chat;

import java.security.spec.KeySpec;

import javax.crypto.*;
import javax.crypto.spec.DESKeySpec;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;





public class DESE {

	private static final String UNICODE_FORMAT = "UTF8";
	public static final String DES_ENCRYPTION_SCHEME = "DES";
	private KeySpec keySpec;
	private SecretKeyFactory secretKeyFactory;
	private Cipher cipher;
	byte[] keyAsBytes;
	private String EncryptionKey;
	private String EncryptionScheme;
	SecretKey key;

	public DESE(String EncKey) throws Exception {
		EncryptionKey = EncKey;
		EncryptionScheme = DES_ENCRYPTION_SCHEME;
		keyAsBytes = EncryptionKey.getBytes(UNICODE_FORMAT);
		keySpec = new DESKeySpec(keyAsBytes);
		secretKeyFactory = SecretKeyFactory.getInstance(EncryptionScheme);
		cipher = Cipher.getInstance(EncryptionScheme);
		key = secretKeyFactory.generateSecret(keySpec);
	}

	public String encrypt(String unencryptedString) {
		String encryptedString = null;

		try {
			cipher.init(Cipher.ENCRYPT_MODE, key);
			byte[] plainText = unencryptedString.getBytes(UNICODE_FORMAT);
			byte[] encryptedText = cipher.doFinal(plainText);
			BASE64Encoder base64encoder = new BASE64Encoder();
			encryptedString = base64encoder.encode(encryptedText);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return encryptedString;

	}

	public String decrypt(String encryptedString) {
		String decryptedText = null;

		try {
			cipher.init(Cipher.DECRYPT_MODE, key);
			BASE64Decoder base64decoder = new BASE64Decoder();
			byte[] encryptedText = base64decoder.decodeBuffer(encryptedString);
			byte[] plainText = cipher.doFinal(encryptedText);
			decryptedText = bytes2String(plainText);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return decryptedText;
	}

	private static String bytes2String(byte[] bytes) {
		StringBuilder stringBuffer = new StringBuilder();
		for (int i = 0; i < bytes.length; i++) {
			stringBuffer.append((char) bytes[i]);
		}
		return stringBuffer.toString();
	}

}

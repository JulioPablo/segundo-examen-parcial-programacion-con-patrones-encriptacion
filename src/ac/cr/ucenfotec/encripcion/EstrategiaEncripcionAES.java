package ac.cr.ucenfotec.encripcion;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Encoder;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

public class EstrategiaEncripcionAES extends EstrategiaEncripcion{
	
	private final int KEYSIZE = 8;
	
	@Override
	public void createKey(String name) throws Exception {
		byte [] key = generatedSequenceOfBytes();
		writeBytesFile(name,key,KEY_EXTENSION);
	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.ENCRYPT_MODE, k);
		byte[] encryptedData = cipher.doFinal(message.getBytes(StandardCharsets.UTF_8));
	    Encoder oneEncoder = Base64.getEncoder();
	    encryptedData = oneEncoder.encode(encryptedData);
		writeBytesFile(messageName,encryptedData,MESSAGE_ENCRYPT_EXTENSION);
	}
	
	@Override
	public void decryptMessage(String messageName, String keyName) throws Exception {
		byte[] key = readKeyFile(keyName);
		byte[] encryptedMessage = readMessageFile(messageName);
		System.out.println(encryptedMessage.length);
		Cipher cipher = Cipher.getInstance("AES");
		SecretKeySpec k = new SecretKeySpec(key,"AES");
		cipher.init(Cipher.DECRYPT_MODE, k);
		byte[] DecryptedData = cipher.doFinal(encryptedMessage);
		String message = new String(DecryptedData, StandardCharsets.UTF_8);
		System.out.println("El mensaje era: ");
		System.out.println(message);
	}
	
	private byte[] readKeyFile(String keyName) throws FileNotFoundException, IOException {
		BufferedReader br = new BufferedReader(new FileReader(PATH + keyName + KEY_EXTENSION));
		String everything = "";
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();

		    while (line != null) {
		        sb.append(line);
		        line = br.readLine();
		    }
		    everything = sb.toString();
		} finally {
		    br.close();
		}
		return everything.getBytes(StandardCharsets.UTF_8);
	}
	
	private byte[] generatedSequenceOfBytes() throws Exception {
		StringBuilder randomkey = new StringBuilder();
		for (int i = 0;i < KEYSIZE;i++){
			randomkey.append(Integer.parseInt(Double.toString((Math.random()+0.1)*1000).substring(0,2)));
		}
		return randomkey.toString().getBytes("UTF-8");
	}

}

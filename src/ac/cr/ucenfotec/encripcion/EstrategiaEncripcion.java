package ac.cr.ucenfotec.encripcion;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.Base64.Decoder;

public abstract class EstrategiaEncripcion {
	
	protected final String MESSAGE_ENCRYPT_EXTENSION = ".encript";
	protected final String PATH = "C:/encrypt/asymetric/";
	protected final String KEY_EXTENSION = ".key";
	
	public abstract void createKey(String name) throws Exception;
	public abstract void encryptMessage(String messageName, String message, String keyName) throws Exception;
	public abstract void decryptMessage(String messageName, String keyName) throws Exception;
	
	protected void writeBytesFile(String name, byte[] content, String type) throws FileNotFoundException, IOException {
		FileOutputStream fos = new FileOutputStream(PATH + name + type);
		fos.write(content);
		fos.close();
	}
	
	protected byte[] readMessageFile(String messageName) throws Exception{
		File file = new File(PATH + messageName + MESSAGE_ENCRYPT_EXTENSION);
        int length = (int) file.length();
        BufferedInputStream reader = new BufferedInputStream(new FileInputStream(file));
        byte[] bytes = new byte[length];
        reader.read(bytes, 0, length);
        Decoder oneDecoder = Base64.getDecoder();
	    reader.close();
		return oneDecoder.decode(bytes);
	}
}

package ac.cr.ucenfotec.encripcion;

public class EstrategiaEncripcionAES extends EstrategiaEncripcionSimetrica{
	
	
	public EstrategiaEncripcionAES() {
		super(8);
	}
	
	@Override
	public void createKey(String name) throws Exception {
		simmetricCreateKey(name);
	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		simmetricEncryptMessage("AES", messageName, message, keyName);
	}

	@Override
	public String decryptMessage(String messageName, String keyName) throws Exception {
		return simmetricDecryptMessage("AES", messageName, keyName);
	}
}

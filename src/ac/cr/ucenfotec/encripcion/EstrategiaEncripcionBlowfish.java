package ac.cr.ucenfotec.encripcion;

public class EstrategiaEncripcionBlowfish extends EstrategiaEncripcionSimetrica {

	public EstrategiaEncripcionBlowfish() {
		super(9);
	}

	@Override
	public void createKey(String name) throws Exception {
		simmetricCreateKey(name);
	}

	@Override
	public void encryptMessage(String messageName, String message, String keyName) throws Exception {
		simmetricEncryptMessage("Blowfish", messageName, message, keyName);
	}

	@Override
	public String decryptMessage(String messageName, String keyName) throws Exception {
		return simmetricDecryptMessage("Blowfish", messageName, keyName);
	}

}

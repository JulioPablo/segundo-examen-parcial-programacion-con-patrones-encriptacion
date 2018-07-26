package ac.cr.ucenfotec.encripcion;

public abstract class EstrategiaEncripcionAsimetrica extends EstrategiaEncripcion {
	
	public EstrategiaEncripcionAsimetrica() {
		super("asymetric/");
	}
	
	public abstract void createKey(String name) throws Exception;
	public abstract void encryptMessage(String messageName, String message, String keyName) throws Exception;
	public abstract String decryptMessage(String messageName, String keyName) throws Exception;

}

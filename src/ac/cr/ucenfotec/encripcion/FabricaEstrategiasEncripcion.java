package ac.cr.ucenfotec.encripcion;

public class FabricaEstrategiasEncripcion {
	
	public EstrategiaEncripcion getEncripcion(Encripcion e) {
		switch(e) {
		case AES:
			return new EstrategiaEncripcionAES();
		case RSA:
			return new EstrategiaEncripcionRSA();
		case BLOWFISH:
			return new EstrategiaEncripcionBlowfish();
		default: 
			return null;
		}
	}
}

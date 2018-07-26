package ac.cr.ucenfotec.encripciontest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import ac.cr.ucenfotec.encripcion.Encripcion;
import ac.cr.ucenfotec.encripcion.EstrategiaEncripcion;
import ac.cr.ucenfotec.encripcion.FabricaEstrategiasEncripcion;

@RunWith(Parameterized.class)
public class EncripcionTest {
	
	private static String
		KEY = "_test_key",
		MSG_NAME = "_test_message_name",
		MSG = "_test_encripted_message";
				
	@Parameter
	public EstrategiaEncripcion encripcion;
	
	@Parameters
    public static Collection<EstrategiaEncripcion> data() {
		List<EstrategiaEncripcion> encripciones = new ArrayList<EstrategiaEncripcion>();
		FabricaEstrategiasEncripcion fabricaEncripciones = new FabricaEstrategiasEncripcion();
		
		for(Encripcion enc : Encripcion.values()) {
			encripciones.add(fabricaEncripciones.getEncripcion(enc));
		}
		
		return encripciones;
    }
	
	private String getEncripName() {
		return encripcion.getClass().getSimpleName().replaceAll("EstrategiaEncripcion", "");
	}
	
	@Test
    public void createKeyTest() throws Exception {
    	encripcion.createKey(getEncripName() + KEY);
    }
	
	@Test
    public void encryptMessageTest() throws Exception {
		String en = getEncripName();
    	encripcion.createKey(en + KEY);
    	encripcion.encryptMessage(en + MSG_NAME, en + MSG , en + KEY);
    }
	
	@Test
    public void decryptMessageTest() throws Exception {
		String en = getEncripName();
    	encripcion.createKey(getEncripName() + KEY);
    	encripcion.encryptMessage(en + MSG_NAME, en + MSG , en + KEY);
		Assert.assertEquals((en + MSG), encripcion.decryptMessage(en + MSG_NAME, en + KEY));
    }
}

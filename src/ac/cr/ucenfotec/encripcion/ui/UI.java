package ac.cr.ucenfotec.encripcion.ui;

import java.util.ArrayList;
import java.util.List;

import ac.cr.ucenfotec.encripcion.Encripcion;
import ac.cr.ucenfotec.encripcion.EstrategiaEncripcion;
import ac.cr.ucenfotec.encripcion.FabricaEstrategiasEncripcion;
import ac.cr.ucenfotec.encripcion.ui.input.InputUtil;
import ac.cr.ucenfotec.encripcion.ui.menu.GenericMenu;
import ac.cr.ucenfotec.encripcion.ui.menu.Menu;

public class UI {
	
	private static final String 
	ENC_PROMPT = "Choose a encryption algorithm",
	KEY_PROMPT = "Type the key name",
	MSG_NAME_PROMPT = "Type the message name", 
	MSG_PROMPT = "Type the message",
	INVALID_INPUT_MSG = "Invalid input, try again";
	
	private static List<EstrategiaEncripcion> encripciones;
	private static EstrategiaEncripcion encripcion;
	private static FabricaEstrategiasEncripcion fabricaEncripciones;
	
	public static void main(String[] args){
		
		fabricaEncripciones = new FabricaEstrategiasEncripcion();
		encripciones = new ArrayList<EstrategiaEncripcion>();
		
		for(Encripcion enc : Encripcion.values()) {
			encripciones.add(fabricaEncripciones.getEncripcion(enc));
		}
		
		GenericMenu<EstrategiaEncripcion> menuEncriptaciones = new GenericMenu<EstrategiaEncripcion>
			( e -> e.getClass().getSimpleName().replaceAll("EstrategiaEncripcion", ""),encripciones);
		
		menuEncriptaciones.setPromptText(ENC_PROMPT);
		setEncripcion(menuEncriptaciones.start());
		
		if(encripcion == null) {
			return;
		}
		
		Menu menu = new Menu();
		menu.setPreOptionsCommand(()-> System.out.format("Using: %s\n",encripcion.getClass().getSimpleName().replaceAll("EstrategiaEncripcion", "")));
		menu.addOption("Create key", UI::createKey);
		menu.addOption("Encrypt message",UI::encryptMessage);
		menu.addOption("Decrypt Message", UI::decryptMessage);
		menu.addOption("Change encryption", ()-> setEncripcion(menuEncriptaciones.start()));
		menu.start();
	}
	
	public static void createKey() {
		try {
			encripcion.createKey(InputUtil.getString(KEY_PROMPT, INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void encryptMessage() {
		try {
			encripcion.encryptMessage(InputUtil.getString(MSG_NAME_PROMPT , INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE), 
					InputUtil.getString(MSG_PROMPT, INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE),
					InputUtil.getString(KEY_PROMPT, INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void decryptMessage() {
		try {
			System.out.format("Decrypted message: %s\n",encripcion.decryptMessage(InputUtil.getString(MSG_NAME_PROMPT, INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE),
					InputUtil.getString(KEY_PROMPT, INVALID_INPUT_MSG, Integer.MAX_VALUE, Integer.MIN_VALUE)));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void setEncripcion(EstrategiaEncripcion nueva) {
		
		if(nueva == null) {
			return;
		}
		
		encripcion = nueva;
	}

	
	
}

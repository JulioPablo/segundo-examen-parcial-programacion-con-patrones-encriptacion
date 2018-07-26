package ac.cr.ucenfotec.encripcion.ui.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ac.cr.ucenfotec.encripcion.ui.input.InputUtil;

public class Menu {

	private Map<String, Command> commandsMap = new LinkedHashMap<String, Command>();
	private List<Command> commandList;
	private List<String> commandNameList;
	private String closeText;
	private String confirmText;
	private String promptText;
	private String outOfBoundsText;
	private boolean confirm;
	private Command preOptionsCommand = null;
    
	public Menu() {
		this.closeText = "Close";
		this.promptText = "Available commands:";
		this.outOfBoundsText = "Please enter the number of the command you want to execute.";
		this.confirmText = "Save";
		this.confirm = false;
	}
	
	public boolean start(){
		commandList = new ArrayList<Command>(commandsMap.values());
		commandNameList = new ArrayList<String>(commandsMap.keySet());
		int size = commandsMap.size();
		int max =  size + (confirm ? 2 : 1);
		int num = 0;
		int index = 0;
		
		try {
					
			while(true) {
				
				num = 0;
				
				if(preOptionsCommand != null) {
					preOptionsCommand.execute();
					System.out.println("");
				}
				
				System.out.println(promptText);
				
				for (String commandName : commandNameList) {
				    System.out.println(++num + "." + commandName);
				}
				
				System.out.println(++num + "." + closeText);
				
				if(confirm) {
					System.out.println(++num + "." + confirmText);
				}
								
				index = InputUtil.getInt(outOfBoundsText, max, 1);
				index--;
				
				if(index == size) {
					return false;
				}else if(index == (size + 1)) {
					return true;
				}
				
				commandList.get(index).execute();
			}
			
		}catch(IOException e) {
			//:(
		}
		
		return false;
	}

	public void setPreOptionsCommand(Command preOptionsCommand) {
		this.preOptionsCommand = preOptionsCommand;
	}
	
	public void addOption(String commandName, Command command){
		commandsMap.put(commandName, command);
	}
	
	public String getCloseText() {
		return closeText;
	}

	public void setCloseText(String closeText) {
		this.closeText = closeText;
	}

	public String getPromptText() {
		return promptText;
	}

	public void setPromptText(String promptText) {
		this.promptText = promptText;
	}

	public String getOutOfBoundsText() {
		return outOfBoundsText;
	}

	public void setOutOfBoundsText(String outOfBoundsText) {
		this.outOfBoundsText = outOfBoundsText;
	}

	public boolean isConfirm() {
		return confirm;
	}

	public void setConfirm(boolean confirm) {
		this.confirm = confirm;
	}
	
	public void setConfirm(boolean confirm, String confirmText) {
		this.confirm = confirm;
		this.confirmText = confirmText;
	}


	public String getConfirmText() {
		return confirmText;
	}

	public void setConfirmText(String confirmText) {
		this.confirmText = confirmText;
	}
	
	
	
}
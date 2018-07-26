package ac.cr.ucenfotec.encripcion.ui.menu;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ac.cr.ucenfotec.encripcion.ui.input.InputUtil;

public class GenericMenu<T>{

	private List<T> list;
	private String closeText;
	private String promptText;
	private String outOfBoundsText;
	private RowPrinter<T> rowPrinter;
	
	public GenericMenu(RowPrinter<T> rowPrinter) {
		this.closeText = "Close";
		this.promptText = "Available elements:";
		this.outOfBoundsText = "Please enter the number of the element you want to select.";
		this.rowPrinter = rowPrinter;
		this.list = new ArrayList<T>();
	}
	
	public GenericMenu(RowPrinter<T> rowPrinter,List<T> list) {
		this(rowPrinter);
		this.list = list;
	}
	
	public T start(){
		int size = list.size();
		int max = size + 1;
		int num = 0;
		int index = 0;
		
		try {
					
			while(true) {
				
				num = 0;
				
				System.out.println(promptText);
				
				for (T option : list ) {
				    System.out.println(++num + "." + rowPrinter.print(option));
				}
				System.out.println(++num + "." + closeText);
				
				index = InputUtil.getInt(outOfBoundsText, max, 1);
				index--;
				
				if(index == size) {
					return null;
				}
				
				return list.get(index);
			}
			
		}catch(IOException e) {
			//:(
		}
		
		return null;
	}

	
	public void addOption(T option) {
		list.add(option);
	}

	public List<T> getList() {
		return list;
	}

	public void setList(List<T> list) {
		this.list = list;
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

	public RowPrinter<T> getRowPrinter() {
		return rowPrinter;
	}

	public void setRowPrinter(RowPrinter<T> rowPrinter) {
		this.rowPrinter = rowPrinter;
	}
}
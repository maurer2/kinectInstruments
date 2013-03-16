package views.tabs;

import controlP5.ControlP5;
import controlP5.Controller;

public class TabNavigation {
	
	
	private ControlP5 cp;
	private int tabIndex = 1;
	public TabNavigation(ControlP5 cp){
		
		this.cp = cp;
		
	}
	
	public void addTab(Tab t){
		
		cp.addTab(t.getName()).setColorBackground(0)
	     .setColorLabel(200)
	     .setColorActive(150).setId(tabIndex);
		
		for(int i = 0; i < t.getControllers().size(); i++){
			Controller c = t.getControllers().get(i);
			c.moveTo(t.getName());
		}
		
		tabIndex++;
		
		
	}

}

package player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import main.Main;
import SimpleOpenNI.SimpleOpenNI;

public class PlayerControll {
	private Main p;
	private SimpleOpenNI context;
	private List<Player> player;

	public PlayerControll(Main p, SimpleOpenNI context) {
		this.p = p;
		this.context = context;
		this.player = new ArrayList<Player>();
	}

	public void update() {
		int[] userList = context.getUsers();
		
		System.out.println(userList.length);	

		// Collections.addAll(player, userList);
	}

}

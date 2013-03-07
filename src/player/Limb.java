package player;

import processing.core.PVector;

public class Limb extends PVector{		
	private float acceleration;
	private float velocity;
	private float accelerationLastFrame;	
	private float velocityLastFrame;
	
	private PVector positionLastFrame;
	
	public Limb(){
		super();
	}

}

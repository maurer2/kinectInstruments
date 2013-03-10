package instruments;

import kinect.Kinect;
import main.Main;

public class Instrument {
	private Main p;
	private Kinect kinect;	
	private IKinectInstrument currentInstrument;

	public Instrument(Main p, Kinect kinect) {
		this.p = p;
		this.kinect = kinect;
	}
	
	public void setCurrentInstrument(int number){
		switch (number) {
		case 0:
			currentInstrument = new DebugInstrument();
			break;

		default:
			break;
		} 
		
	}
}

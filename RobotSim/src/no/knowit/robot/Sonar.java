package no.knowit.robot;

public class Sonar {
	int angle;
	int distance;
	Robot robot;
	int[] distances = new int[180];
	public Sonar(Robot r){
		robot = r;
	}
	public void tick(){
		for(int i = 0; i < 180;i++){
			
			angle = i;
			distances[angle] = measureDistance();
		}
	}
	private int measureDistance(){
		return 0;
		//TODO: implement
	}
}

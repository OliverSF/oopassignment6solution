package weather.presentation;

import weather.data.Observer;
import weather.data.Subject;
import weather.data.InitProperties;



public class StatisticsDisplay implements Observer, DisplayElement {
	
	
	private float maxTemp = InitProperties.INIT_MAX_TEMP;
	private float minTemp = InitProperties.INIT_MIN_TEMP;
	private float tempSum= InitProperties.INIT_TEMP_SUM;
	private int numReadings;
	private Subject subject;

	public StatisticsDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
		this.tempSum += temp;
		this.numReadings++;

		if (temp > maxTemp) {
			this.maxTemp = temp;
		}
 
		if (temp < minTemp) {
			this.minTemp = temp;
		}

		display();
	}

	public void display() {
		System.out.println("Avg/Max/Min temperature = " + getAveTemp()
			+ "/" + getMaxTemp() + "/" + getMinTemp());
	}

	@Override
	public void setSubject(Subject data) {
		this.subject.removeObserver(this);
		data.registerObserver(this);
		this.subject = data;
		this.reset();
	}

	@Override
	public void close() {
		this.subject.removeObserver(this);
		
	}
	
	public float getAveTemp(){
		return this.tempSum/numReadings;
	}
	
	public float getMaxTemp(){
		return this.maxTemp;
	}
	
	public float getMinTemp(){
		return this.minTemp;
	}
	@Override
	public void reset(){
		this.maxTemp = InitProperties.INIT_MAX_TEMP;
		this.minTemp = InitProperties.INIT_MIN_TEMP;
		this.tempSum= InitProperties.INIT_TEMP_SUM;
		this.numReadings=0;
	}
}

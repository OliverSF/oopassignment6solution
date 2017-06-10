package weather.presentation;

import weather.data.InitProperties;
import weather.data.Observer;
import weather.data.Subject;
	
public class CurrentConditionsDisplay//
		implements Observer, DisplayElement {
	private float temperature;
	private float humidity;
	private Subject subject;
	
	public CurrentConditionsDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}
	
	@Override
	public void update(float temperature,//
			float humidity, float pressure) {
		this.temperature = temperature;
		this.humidity = humidity;
		display();
	}
	@Override
	public void display() {
		System.out.println("Current conditions: " + getTemp()
			+ "F degrees and " + getHumidity() + "% humidity");
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
	
	public float getTemp(){
		return this.temperature;
		
	}
	
	public float getHumidity(){
		return this.humidity;
		
	}
	 
	public void reset(){
		this.temperature = InitProperties.INIT_MAX_TEMP;
		this.humidity = InitProperties.INIT_HUMIDITY;
	}
}



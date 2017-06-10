package weather.presentation;

import weather.data.Observer;
import weather.data.Subject;
import weather.data.InitProperties;

public class ForecastDisplay implements Observer, DisplayElement {
	public static String INIT_FORECAST = "no change";
	private float currentPressure = InitProperties.INIT_PRESSURE;  
	private float lastPressure;
	private Subject subject;
	private String forecast  = INIT_FORECAST;

	public ForecastDisplay(Subject data) {
		this.subject = data;
		data.registerObserver(this);
	}

	public void update(float temp, float humidity, float pressure) {
        lastPressure = currentPressure;
		currentPressure = pressure;

		analyse();
		display();
	}

	private void analyse() {
		
		if (currentPressure > lastPressure) {
			this.forecast= "sunshine";
		} else if (currentPressure == lastPressure) {
			this.forecast = "no change";
		} else if (currentPressure < lastPressure) {
			this.forecast = "rain";
		}
		
	}
	@Override
	public void display() {
		System.out.print("Forecast: " + this.forecast);
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
	
	public float getCurrentPressure(){
		return this.currentPressure;
	}
	
	@Override
	public void reset(){
		
		this.currentPressure = InitProperties.INIT_PRESSURE;  
		this.lastPressure = 0.0f;
		this.forecast = INIT_FORECAST;
	}
	
	public String getForecast(){
		return this.forecast;
	}
}

package weather.data;

public interface WeatherData {
	
	public void dataIn(float temperature,float humidity, float pressure);
	
	public float getTemperature();
	
	public float getHumidity();
	
	public float getPressure();
	
}

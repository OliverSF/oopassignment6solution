package weather.presentation;

import static org.junit.Assert.*;

import org.junit.Test;

import weather.data.InitProperties;
import weather.data.WeatherData_East;
import weather.data.WeatherData_North;
import weather.data.WeatherData_South;
import weather.data.WeatherData_West;

public class DisplayUpdateTest {
	
	@Test
	public void testCurrentConditionsUpdate() {	
		/**
		 * 2 sources of Data
		 */
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_West weatherWest = new WeatherData_West();
	
		/**
		 * CurrentConditionsDisplay display widgets observing 
		 * the WeatherData_East object
		 */
		CurrentConditionsDisplay currentDisplay = 
			new CurrentConditionsDisplay(weatherEast);
		
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		
		//set new data
		weatherEast.dataIn(temp, humidity, pressure);
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		
		// new data
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		
		//set new data
		weatherEast.dataIn(temp, humidity, pressure);
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		
		//change data source
		currentDisplay.setSubject(weatherWest);
		
		//new data
		temp = 65;
		humidity = 46;
		pressure =36.5f;
		
		//set new data
		weatherWest.dataIn(temp, humidity, pressure);
		assertEquals(temp, currentDisplay.getTemp(), 0.001f);
		assertEquals(humidity, currentDisplay.getHumidity(), 0.001f);
		

	}

	@Test
	public void testStatsUpdate() {	
		/**
		 * 2 sources of Data
		 */
		WeatherData_North weatherNorth = new WeatherData_North();
		WeatherData_West weatherWest = new WeatherData_West();
	
		/**
		 * StatisticsDisplay display widget observing 
		 * the WeatherData_East object
		 */
		StatisticsDisplay statsDisplay = 
			new StatisticsDisplay(weatherNorth);
		
		//new data
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		
		//expected data
		int readings=1;
		float maxTemp = 80;
		float minTemp = 80;
		float aveTemp = maxTemp/readings;
		
		//set new data
		weatherNorth.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		
		//change data
		temp = 24;
		humidity = 32;
		pressure = 20.2f;
		
		readings++;

		minTemp = 24;
		aveTemp = (maxTemp+temp)/readings;
		
		//set new data
		weatherNorth.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
		
		/**
		 * change data source to weatherWest
		 */
		statsDisplay.setSubject(weatherWest);
		
		//new data
		temp = 65;
		humidity = 46;
		pressure =36.5f;
		
		readings = 1;
		maxTemp = 65;
		minTemp=65;
		aveTemp = maxTemp/readings;
		
		//set new data
		weatherWest.dataIn(temp, humidity, pressure);
		assertEquals(aveTemp, statsDisplay.getAveTemp(), 0.001f);
		assertEquals(maxTemp, statsDisplay.getMaxTemp(), 0.001f);
		assertEquals(minTemp, statsDisplay.getMinTemp(), 0.001f);
	}
	
	@Test
	public void testForeCastUpdate() {	
		
		/**
		 * 2 sources of Data
		 */
		WeatherData_East weatherEast = new WeatherData_East();
		WeatherData_South weatherSouth = new WeatherData_South();
		
		/**
		 * ForecastDisplay display widgets observing 
		 * the WeatherData_East object
		 */
		ForecastDisplay foreCastDisplay = 
			new ForecastDisplay(weatherEast);
		
		//new data
		float temp = 80;
		float humidity =65;
		float pressure = 30.4f;
		
		String expected = "sunshine";
		
		weatherEast.dataIn(temp, humidity, pressure);
		
		assertEquals(expected, foreCastDisplay.getForecast());
		
		//new pressure data arrives
		pressure = 28.1f;
		expected = "rain"; // change expectation
		
		weatherEast.dataIn(temp, humidity, pressure);
		assertEquals(expected, foreCastDisplay.getForecast());
		
		//Change source of data to weatherWest
		foreCastDisplay.setSubject(weatherSouth);
		pressure = InitProperties.INIT_PRESSURE;
		
		weatherSouth.dataIn(temp, humidity, pressure);
		
		expected = "no change"; // change expectation
		
		assertEquals(expected, foreCastDisplay.getForecast());
		
		
	}
	
	
}

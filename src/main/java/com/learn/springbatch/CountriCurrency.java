package com.learn.springbatch;

public class CountriCurrency {
	
	private String countryName;
	private String countryCoinName;
	private String countrySigla;
	private float countryCoinValue;
	
	public CountriCurrency() {
		
	}
	
	public CountriCurrency(String countryName, String countryCoinName, String countrySigla, float countryCoinValue) {
		this.countryName = countryName;
		this.countryCoinName = countryCoinName;
		this.countrySigla = countrySigla;
		this.countryCoinValue = countryCoinValue;
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCountryCoinName() {
		return countryCoinName;
	}

	public void setCountryCoinName(String countryCoinName) {
		this.countryCoinName = countryCoinName;
	}

	public String getCountrySigla() {
		return countrySigla;
	}

	public void setCountrySigla(String countrySigla) {
		this.countrySigla = countrySigla;
	}

	public float getCountryCoinValue() {
		return countryCoinValue;
	}

	public void setCountryCoinValue(float countryCoinValue) {
		this.countryCoinValue = countryCoinValue;
	}

	@Override
	public String toString() {
		return "Country Name: " + countryName + ", Country Coin: " + countryCoinName
				+ ", Country Coin Sigla: " + countrySigla + ", Country Coin Value: " + countryCoinValue;
	}
	
	
}

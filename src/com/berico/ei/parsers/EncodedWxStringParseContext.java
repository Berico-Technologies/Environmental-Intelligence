package com.berico.ei.parsers;

import java.util.Calendar;

import com.berico.ei.Observation;

/**
 * Context of the Weather String being parsed.
 * @author Richard Clayton
 */
public class EncodedWxStringParseContext {
	
	@SuppressWarnings("unused")
	private EncodedWxStringParseContext(){}
	
	public EncodedWxStringParseContext(String weatherString){
		this.encodedWxString = weatherString;
		this.elements = weatherString.split("\\s+");
		this.currentPosition = 0;
		this.currentElement = this.elements[0];
		this.observation = new Observation();
	}
	
	public EncodedWxStringParseContext(String weatherString, int year, int month){
		this(weatherString);
		this.observationMonth = month;
		this.observationYear = year;
	}
	
	protected String currentElement = null;
	protected int currentPosition = 0;
	protected String[]  elements = null;
	protected String encodedWxString = null;
	protected Observation observation = null;
	
	protected int observationYear = Calendar.getInstance().get(Calendar.YEAR);
	//Java is stupid and uses array indexes for months of year (so January is month zero)
	protected int observationMonth = Calendar.getInstance().get(Calendar.MONTH) + 1;
	
	public String getCurrentElement() {
		return currentElement;
	}
	
	public int getCurrentPosition() {
		return currentPosition;
	}
	
	public String[] getElements() {
		return elements;
	}
	
	public String getEncodedWxString() {
		return encodedWxString;
	}
	
	public Observation getObservation() {
		return observation;
	}
	
	public int getObservationYear() {
		return observationYear;
	}

	public void setObservationYear(int observationYear) {
		this.observationYear = observationYear;
	}

	public int getObservationMonth() {
		return observationMonth;
	}

	public void setObservationMonth(int observationMonth) {
		this.observationMonth = observationMonth;
	}

	public boolean hasNextElement(){
		return (this.currentPosition + 1) != this.elements.length;
	}
	
	public boolean hasPreviousElement(){
		return this.currentPosition != 0;
	}
	
	public String getNextElement(){
		if(hasNextElement()){
			return this.elements[this.currentPosition + 1];
		}
		return null;
	}
	
	public String getPreviousElement(){
		if(hasPreviousElement()){
			return this.elements[this.currentPosition - 1];
		}
		return null;
	}
	
	public void moveToNextElement(){
		if(hasNextElement()){
			this.currentPosition++;
			this.currentElement = this.elements[this.currentPosition];
		}
	}
	
	public void reset(){
		
		this.currentPosition = 0;
		this.currentElement = this.elements[0];
	}
	
	public void foreachElement(ContextHandler handler){
		
		reset();
		
		for(int i = 0; i < this.elements.length; i++){
			
			handler.handleElement(this);
			
			this.moveToNextElement();
		}
		
		reset();
	}
	
	public interface ContextHandler {
		
		void handleElement(EncodedWxStringParseContext context);
	}
}
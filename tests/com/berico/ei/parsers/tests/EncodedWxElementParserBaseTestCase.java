package com.berico.ei.parsers.tests;

import org.junit.Before;

import com.berico.ei.parsers.EncodedWxElementParser;
import com.berico.ei.parsers.EncodedWxStringParseContext;


public abstract class EncodedWxElementParserBaseTestCase {
	
	private EncodedWxElementParser parser = null;
	
	protected int getObservationYear(){ return 2011; }
	
	protected int getObservationMonth(){ return 12; }
	
	protected abstract EncodedWxElementParser createParserInstance();
	
	public EncodedWxElementParser getParser(){
		
		return this.parser;
	}
	
	@Before
	public void setUp() throws Exception {
	
		this.parser = this.createParserInstance();
	}
	
	public EncodedWxStringParseContext createContext(String wxString){
		return createContext(wxString, 0);
	}
	
	public EncodedWxStringParseContext createContext(String wxString, int moveToElement){
		
		EncodedWxStringParseContext context = 
				new EncodedWxStringParseContext(
						wxString, 
						this.getObservationYear(), 
						this.getObservationMonth());
		
		if(moveToElement > 0){
			for(int i = 0; i <= moveToElement; i++){
				
				if(!context.hasNextElement()){
					break;
				}
				
				context.moveToNextElement();
			}
		}
		
		return context;
	}
}
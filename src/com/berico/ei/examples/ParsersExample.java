package com.berico.ei.examples;

import com.berico.ei.Observation;
import com.berico.ei.parsers.WxParsers;
//import com.thoughtworks.xstream.XStream;
//import com.thoughtworks.xstream.io.json.JsonHierarchicalStreamDriver;

public class ParsersExample {

	public static void main(String[] args){
		
		//String metar = "KNFG 242154Z AUTO 21005KT 10SM CLR 22/01 A3025 RMK AO2 SLP243 T02220006 TSNO $";
		String metar = "KNFG 242154Z AUTO 21015G25KT 10SM CLR 22/01 A3025 RMK AO2 SLP243 T02220006 TSNO $";
		
		Observation ob = WxParsers.parseMetar(metar);
		
		//XStream xstream = new XStream(
		//		new JsonHierarchicalStreamDriver());
		
		System.out.println(ob);
	}
	
}

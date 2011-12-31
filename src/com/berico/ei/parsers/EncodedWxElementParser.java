package com.berico.ei.parsers;

/**
 * Defines the requirements of any Parser participating in the Parser chain.
 * @author Richard Clayton 
 */
public interface EncodedWxElementParser {

	/**
	 * Can the Parser handle the current element (context.getCurrentElement())?
	 * @param context Parser Context
	 * @return true if the parser can handle the element.
	 */
	boolean canParseCurrentElement(EncodedWxStringParseContext context);
	
	/**
	 * Perform the Parse on the targetElement of the ParseContext.  The rest
	 * of the encoded weather string is provided if the parser needs to 
	 * traverse the rest of the weather elements.
	 * @param context Context of the parsing operation against a Weather String.
	 * @throws EncodedWxElementParseException
	 */
	void performParse(EncodedWxStringParseContext context) throws EncodedWxElementParseException;
	
}

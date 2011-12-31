package com.berico.ei.parsers;

public class EncodedWxElementParseException extends Exception {

	private static final long serialVersionUID = -2612140185086795175L;

	private EncodedWxElementParser failingParser = null;
	
	private EncodedWxStringParseContext failedContext = null;
	
	public EncodedWxElementParseException(String message,
			EncodedWxElementParser failingParser, EncodedWxStringParseContext failedContext) {
		super(message);
		this.failingParser = failingParser;
		this.failedContext = failedContext;
	}

	public EncodedWxElementParser getFailingParser() {
		return failingParser;
	}

	public EncodedWxStringParseContext getFailedContext() {
		return failedContext;
	}
	
}

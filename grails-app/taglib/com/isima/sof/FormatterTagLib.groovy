package com.isima.sof

class FormatterTagLib {

	static namespace = "ff"
	
	def formatterService

	def formatNumberScaled = {attrs, body ->
		
		Double doubleNumber 
		String stringNumber = StringUtils.cleanSpaceCharAndComma( attrs.'number'.toString() )
		doubleNumber = Double.parseDouble( stringNumber );
		if(doubleNumber >= 1000)
			out << formatterService.coolFormat(doubleNumber, 0)
		else
			out << doubleNumber.toInteger()
	}
}

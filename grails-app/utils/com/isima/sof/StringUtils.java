package com.isima.sof;

public class StringUtils {

	// Space can be very special (unicode characters and so on) so we have to use isSpaceChar !!
	// For example 'replaceAll " ",""' doest not work !
	// Delete comma (for english langage)
	public static String cleanSpaceCharAndComma(final String toClean) {
		String cleanedString = "";
		int l = toClean.length();
		char c;
		for(int i = 0 ; i < l ; i++){
			c = toClean.charAt(i);
			// Space deletion
			if(! Character.isSpaceChar(c) && c != ',')
				cleanedString += c;
		}
		return cleanedString;
	}
}

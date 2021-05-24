package com.ibmhq;

public class TestUtil extends hqdriver{

	/**
	 * This method is to add value to a given variable.
	 * @param value The value which needs to be stored in a temporary variable
	 * @return success or failure message
	 * @throws Exception
	 */
	  public String AddValuetoTempVariable(String value) throws Exception {
	  	try {
		  		TempVariable = value;
		  		return "The value : "+value+" has been added to a temporary variable.";
		    }
			catch(Exception e) {
				return e.toString();
			}
	  }
	  
}
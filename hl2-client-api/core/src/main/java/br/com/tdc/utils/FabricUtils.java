package br.com.tdc.utils;

public class FabricUtils {

	public static String getDisplayMessage(String errorMessage) {
		return errorMessage.substring(errorMessage.lastIndexOf("Error:")+7);
	}

}

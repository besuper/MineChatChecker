package com.besuper.minechatchecker.fr.api.string;

public class Converter {

	public static String a(String s){
		return s.replace(Char._.name(), " ").replace(Char.ZZXZ1.name(), "?").replace(Char.ZZXZ2.name(), ".")
		.replace(Char.ZZXZ3.name(), ":").replace(Char.ZZXZ4.name(), "/")
		.trim();
	}
	
}

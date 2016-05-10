package br.com.rino.util;

public class MathUtil {
	public static int rand(int Str, int End) {  
	    return (int) Math.ceil(Math.random() * (End  - Str + 1)) - 1 + Str;  
	}
}

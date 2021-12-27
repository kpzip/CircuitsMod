package com.kpzip.circuits.util;

public final class typefixer {
	
	public static int toInt(Short high, Short low) {
		return (high << 16) | (low & 0xFFFF);
	}
	
	public static short getLow(int value) {
		return (short)value;
	}
	
	public static short getHigh(int value) {
		return (short)(value >> 16);
	}

}

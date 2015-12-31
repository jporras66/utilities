package com.indarsoft.utl;

import java.util.Arrays;

/**
 * Array utilities.
 *
 * @author fjavier.porras@gmail.com
 */

public class ArrayUtl {

	/**
	 * Concatenate 2 arrays of same type T[].
	 * <p>
	 * Note: To be improved !!
	 * @param fc  input array 
	 * @param sc input array
	 * @return  T[]
	 * 
	 */	
	public static <T> T[] concat(T[] fc, T[] sc) {
		T[] result = Arrays.copyOf(fc, fc.length + sc.length);
		System.arraycopy(sc, 0, result, fc.length, sc.length);
		return result;
	}
	/**
	 * Concatenate 2 arrays of bytes
	 * <p>
	 * @param first  input array 
	 * @param second input array 
	 * @return concatenated byte array
	 * 
	 */		
	public static byte[] concat( byte[] first, byte[] second) {
		byte[] result = Arrays.copyOf(first, first.length + second.length);
		System.arraycopy(second, 0, result, first.length, second.length);
		return result;
	}
	
}

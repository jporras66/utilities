package com.indarsoft.utl;

/**
 * EBCDIC Data utilities
 * 
 * @author fjavier.porras@gmail.com
 * 
 */
public final class Ebcdic
{
    
    /**
	 * Convert an int to a numeric ebcdic array of bytes.
	 * Example : 6553 --{@literal>} (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 
	 * <p>
	 * @param 	value to be converted
	 * @return	EBCDIC coded byte array 
	 */
	public static byte[] int2byteArray ( int value ){
		
	
		String str = String.valueOf( value ) ;
		byte[] bar = new byte[ str.length() ];
		for (int i=0; i<str.length(); i++){
			char z = str.charAt( i );
			byte b = (byte) ( z - 0x30 + 0xF0);
			bar[i] = b ; 
		}		
		return bar ; 
	}	

	/** Check if a byte is EBCDIC numeric Coded.
	 * <p>
	 * EBCDIC numeric are between 0xF0 and 0xF9
	 * @param 	abyte     byte to be checked
	 * @return  boolean 
	 */	
	public static boolean isNumeric( byte abyte ) {
	
		if ( abyte < (byte)0xF0 || abyte > (byte)0xF9 ) return false ;
		
		return true ;
	}     
    
    /** Check if a byte array is EBCDIC numeric Coded.
	 * EBCDIC numeric are between 0xF0 and 0xF9 
	 * <p>
	 * @param 	abytearr     byte array to be checked
	 * @return  boolean 
	 *  
	 */	
	public static boolean isNumeric( byte[] abytearr ) {
	
		for (int i=0;i< abytearr.length;i++){
			if ( ! isNumeric( abytearr[i] ) ) return false ;
		}
		return true ;
	}

	/**
     * Convert an input EBCDIC byte array to int.
     * <p>
     * Example : (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 --{@literal>} 6553
     * @param 	abytearr EBDCDIC coded
     * @return 	int result 
     */	
	public static int toInt ( byte[] abytearr ) throws IllegalArgumentException {
		
		long value = toLong ( abytearr); 
		if ( value < Integer.MIN_VALUE ) throw new IllegalArgumentException("int data underflow");
		if ( value > Integer.MAX_VALUE ) throw new IllegalArgumentException("int data overflow") ;
		int returned = (int) value; 
		return returned ; 
	}
	/**
     * Convert an input numeric EBCDIC byte array (up to 8 bytes) to long.
     * <p>
     * @param 	abytearr numeric EBDCDIC coded
     * @return 	long	value 
     */	
	public static long toLong ( byte[] abytearr ) throws IllegalArgumentException {
		
		long value = 0L;
		int num = abytearr.length;
		if ( ! isNumeric( abytearr ) ) throw new IllegalArgumentException("only 8 bytes EBCDIC numeric data allowed !!") ;
		if ( num > 8 ) throw new IllegalArgumentException("only 8 bytes EBCDIC numeric data allowed !!") ;
		byte[] ab = new byte[num];
		for (int i=0; i<num;i++){
			ab[i] = (byte)( abytearr[i] - 0xF0  );
			value = value + (long) (  ab[i] *  Math.pow( 10 , num - i - 1  ) ) ;
		}
		return value ; 
	}
}

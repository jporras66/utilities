package com.indarsoft.utl;
import java.lang.Long ;

/**
 * BCD data utilities
 * 
 * @author fjavier.porras@gmail.com
 * 
 */
public final class Bcd {

	
	public static final byte POSITIVESIGN = 0x0C ;
	public static final byte NEGATIVESIGN = 0x0D ; 

	/** Returns a byte array copying numnibbles from input and discarding first upper nibble (from input)
	 *  The output array is BCD codded.
	 * <p>	
	 * @param 	abytearr input byte array of data
	 * @param 	startpointer to copy FROM
	 * @param 	num odd number of bytes to be copied
	 * @return  Copied BCD byte array. 	First nibble from first byte 0000 (binary) 
	 *  
	 */
	public static byte[] CopyBcdArrWithoutFirstNibble(  byte[] abytearr, int startpointer, int num  ){
		
		
		if ( num % 2  == 0 ) return CopyEvenBcdArrWithoutUpperNibble ( abytearr, startpointer, num ) ;
		if ( num % 2  != 0 ) return CopyOddBcdArrWithoutUpperNibble  ( abytearr, startpointer, num ) ;
		return null ; 
//		 
	}
	/** Returns a byte array copying numnibbles from input and discarding last lower nibble (from input)
	 *  The output array is BCD codded.
	 * <p>	
	 * @param 	abytearr     input byte array of data
	 * @param 	startpointer to copy FROM
	 * @param 	num odd number of bytes to be copied
	 * @return  Copied BCD byte array. 	First nibble from first byte 0000 (binary) 
	 */
	public static byte[] CopyBcdArrWithoutLastNibble(  byte[] abytearr, int startpointer, int num  ){
			
		byte bar[] 		= new byte[ num / 2 + 1 ];
		int byteid   	= 0 ; 
	//
		byte uppernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0xF0 ) >> 4 );
		uppernibble = (byte) ( uppernibble & 0x0F) ;
		byte lowernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0x0F )  );
		int nibbelid 			= 1 ;
//			
		for (int i=startpointer ; i < startpointer + bar.length  ; i++){
//				
			
			bar[byteid] = (byte) ( ( bar[byteid] | uppernibble ) );
			nibbelid 	=  nibbelid + 1 ;
			byteid 		=  nibbelid / 2 ;
			
			if ( nibbelid > num ) break ;			
				bar[byteid] = (byte) ( ( bar[byteid] | lowernibble )  << 4 );
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;
				
				int index = i + 1 ;
				uppernibble = (byte) ( ( abytearr[ index ] & (byte) 0xF0 ) >> 4 );
				uppernibble = (byte) ( uppernibble & 0x0F) ;
				lowernibble = (byte) ( ( abytearr[ index ] & (byte) 0x0F )  );
				
			}
		return  bar ;	 
	}
//	
	private static byte[] CopyEvenBcdArrWithoutUpperNibble(  byte[] abytearr, int startpointer, int numnibbles  ){
		
		byte bar[] = new byte[ numnibbles / 2 ];
		//int endpointer = startpointer + numnibbles  ;
		int nibbelid = 0 ;
		int byteid   = 0 ; 
//
		byte uppernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0xF0 ) >> 4 );
		uppernibble = (byte) ( uppernibble & 0x0F) ;
		byte lowernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0x0F )  );
//			
		for (int i=startpointer ; i <=startpointer+bar.length  ; i++){
//			
			
			if ( nibbelid != 0 ){	

				bar[byteid] = (byte) ( ( bar[byteid] | uppernibble )  );
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;
				if ( nibbelid == numnibbles ) break ; 
				
				bar[byteid] = (byte) ( ( bar[byteid] | lowernibble ) << 4);
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;
				if ( nibbelid == numnibbles ) break ;
				
			}else{ //--> discard uppernibble
			
				bar[byteid] = (byte) ( ( bar[byteid] | lowernibble ) << 4 );
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;
				
			}
			
			int index = i+1 ; 
			if ( index >= abytearr.length ) break ; 
			uppernibble = (byte) ( ( abytearr[ i+1 ] & (byte) 0xF0 ) >> 4 );
			uppernibble = (byte) ( uppernibble & 0x0F) ;
			lowernibble = (byte) ( ( abytearr[ i+1 ] & (byte) 0x0F )  );
			
		}
		return  bar ; 
	}	

	private static byte[] CopyOddBcdArrWithoutUpperNibble(  byte[] abytearr, int startpointer, int numnibbles  ){
			
		byte bar[] = new byte[ numnibbles / 2 + 1 ];
		int nibbelid = 0 ;
		int byteid   = 0 ; 
//
		byte uppernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0xF0 ) >> 4 );
		uppernibble = (byte) ( uppernibble & 0x0F) ;
		byte lowernibble = (byte) ( ( abytearr[ startpointer ] & (byte) 0x0F )  );
//			
		for (int i=startpointer ; i < startpointer+bar.length  ; i++){
//			
			
			if ( nibbelid != 0 ){	
					
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;				
				bar[byteid] = (byte) ( ( bar[byteid] | uppernibble ) << 4 );
				if ( nibbelid == numnibbles ) break ; 
				
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;				
				bar[byteid] = (byte) ( ( bar[byteid] | lowernibble ) );
				if ( nibbelid == numnibbles ) break ; 
				
			}else{					//--> discard uppernibble
				
				nibbelid 	=  nibbelid + 1 ;
				byteid 		=  nibbelid / 2 ;
				bar[byteid] = (byte) ( ( bar[byteid] | lowernibble )  );
				
			}
			
			int index = i+1 ; 
			if ( index >= abytearr.length ) break ; 
			
				uppernibble = (byte) ( ( abytearr[ index ] & (byte) 0xF0 ) >> 4 );
				uppernibble = (byte) ( uppernibble & 0x0F) ;
				lowernibble = (byte) ( ( abytearr[ index ] & (byte) 0x0F )  );
				
			}
			return  bar ;
//			 
		}		

	/** Check if a byte is BCD Coded.
	 * <p>
	 * @param 	abyte     byte to be checked
	 * @return  boolean  
	 */	
	public static boolean isBcd( byte abyte ) {
	
		byte lowernibble 	= (byte)  ( abyte & 0x0F ) ;
		byte upernibble 	= (byte)  ( ( abyte & 0xF0 ) >> 4  );
		if ( lowernibble < 0x00 || lowernibble > (byte) 0x09 ) return false ;
		if ( lowernibble < 0x00 || upernibble  > (byte) 0x09 ) return false ;
		
		return true ;
	}	
	/** Check if a byte array is BCD Coded.
	 * <p>
	 * @param 	abytearr     byte array to be checked
	 * @return  boolean 
	 */	
	public static boolean isBcd( byte[] abytearr ) {
		
		for ( int i = 0; i < abytearr.length ; i++){
			if ( ( isBcd( abytearr[i] )  == false) ) return false ;
		}
		return true ; 
	}	
	//	
	/** Transform an input long to a signed BCD byte array.
	 * <p>
	 * @param 	num long input
	 * @return  BCD byte array   
	 */			
	public static byte[] long2SignedBcdArray(long num) {
		
		int digits = 0;
		byte SIGN  = 0x00; 
		boolean isNegative 	= num < 0 ;
		
		if (isNegative) { 
			digits++ ;
			num = num * (-1);
			SIGN = NEGATIVESIGN ;		// Negative SIGN 
		}else
		{
			digits++ ;
			SIGN  = POSITIVESIGN; 		// Positive SIGN
		}
		
		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}
			
		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
		boolean isOdd 		= digits % 2 != 0;

		byte bcd[] = new byte[byteLen];

		for (int i = 0; i < digits; i++) {
			
			if ( i == 0 ){
				bcd[0] = SIGN ;
			}else {
				byte tmp = (byte) (num % 10);

				if (i == digits - 1 && isOdd)
					bcd[i / 2] = tmp;
				else if (i % 2 == 0 )
					bcd[i / 2] = tmp;
				else {
					byte foo = (byte) (tmp << 4);
					bcd[i / 2] |= foo;
				}
				num /= 10;
			}
		}

		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}

		return bcd;
	}
	/** Transform an input long to an unsigned BCD byte array.
	 * <p>
	 * @param 	num long input
	 * @return  BCD byte array   
	 */	
	public static byte[] long2UnsignedBcdArray(long num) {
		
		int digits = 0;
		boolean isNegative 	= num < 0 ;
		
		if (isNegative) { 
			num = num * (-1);
		}
		
		long temp = num;
		while (temp != 0) {
			digits++;
			temp /= 10;
		}
			
		int byteLen = digits % 2 == 0 ? digits / 2 : (digits + 1) / 2;
		boolean isOdd 		= digits % 2 != 0;

		byte bcd[] = new byte[byteLen];

		for (int i = 0; i < digits; i++) {
			
			byte tmp = (byte) (num % 10);

			if (i == digits - 1 && isOdd)
				bcd[i / 2] = tmp;
			else if (i % 2 == 0 )
				bcd[i / 2] = tmp;
			else {
				byte foo = (byte) (tmp << 4);
				bcd[i / 2] |= foo;
			}
			num /= 10;
		}

		for (int i = 0; i < byteLen / 2; i++) {
			byte tmp = bcd[i];
			bcd[i] = bcd[byteLen - i - 1];
			bcd[byteLen - i - 1] = tmp;
		}

		return bcd;
	}
	
	/** Convert an input numeric BCD byte array (up to 4 bytes) to int.
	 * <p>
	 * @param 	abytearr  byte to be converted
	 * @return  integer   
	 *  
	 */		
	public static int toInt( byte[] abytearr ) {

		int value = 0 ;
		int num = abytearr.length;
		if ( ! isBcd( abytearr ) ) throw new IllegalArgumentException("only 4 bytes BCD numeric data allowed !!") ;
		if ( num > 8 ) throw new IllegalArgumentException("only 4 bytes BCD numeric data allowed !!") ;
		String  str = toString ( abytearr );
		value = Integer.parseInt( str, 10 );
		return value ; 
	}	
	/** Convert an input numeric BCD byte array (up to 8 bytes) to long.
	 * <p>
	 * @param 	abytearr     byte to be converted
	 * @return  long   
	 *  
	 */			
	public static long toLong( byte[] abytearr ) {

		long value = 0L ;
		int num = abytearr.length;
		if ( ! isBcd( abytearr ) ) throw new IllegalArgumentException("only 8 bytes BCD numeric data allowed !!") ;
		if ( num > 8 ) throw new IllegalArgumentException("only 8 bytes BCD numeric data allowed !!") ;
		String  str = toString ( abytearr );
		value = Long.parseLong( str, 10 );
		return value ; 
	}
	
	/** Numerical string representation of a BCD coded byte.
	 * <p>
	 * @param 	abyte     byte to be converted
	 * @return  numerical string representation with leading sing 
	 *  
	 */	
	public static String toString (byte abyte) {
				
		StringBuffer sb = new StringBuffer();
		
		byte high = (byte) (abyte & 0xf0 );
		high >>= (byte) 4;	
		high = (byte) (high & 0x0f);
		byte low = (byte) (abyte & 0x0f);	

		if ( low == POSITIVESIGN ){
			sb.append(high);
			sb.append("+");
		}else
		if ( low == NEGATIVESIGN ){
			sb.append(high);
			sb.append("-");
		}else{
			sb.append(high);
			sb.append(low);
		}
				
			return sb.toString();
	}
//	
	/** Numerical string representation of a BCD coded byte array.
	 * <p>
	 * @param 	abytearr  byte array to be converted
	 * @return  numerical string representation with leading sing 
	 *  
	 */	
	public static String toString( byte[] abytearr ) {

		StringBuffer sb = new StringBuffer();

		for (int i = 0; i < abytearr.length; i++) {
			sb.append( toString ( abytearr[i] ) );
		}
		
		int SIZE = sb.length() - 1 ;
		if ( sb.charAt( SIZE ) == '+' ){
			String str = "+" + sb.substring (0, SIZE) ;
			return str ;
		}
		else if ( sb.charAt( SIZE ) == '-' ){
			String str = "-" + sb.substring (0, SIZE) ;
			return str ;
		}else {
			return sb.toString();
		}
	}
}

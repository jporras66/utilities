package com.indarsoft.utl;

import java.nio.ByteBuffer;

/**
 * Binary Data utilities
 * 
 * @author fjavier.porras@gmail.com
 * 
 */
public final class Binary
{
    private static final String hexDigits = "0123456789ABCDEF";

    
    /**
     * Convert input binary byte array (up to 4 bytes) to int.
     * <p>
     * Example : (byte)0xFF,(byte)0xFF --> 65535
     * @param 	abytearr	input data byte array
     * @return 	int result value
     * @exception java.lang.IllegalArgumentException 
     */
    
	public static int toInt ( byte[] abytearr ) throws IllegalArgumentException {
		
		int num = abytearr.length ;
		if ( num > 4 ) throw new IllegalArgumentException("up to 4 length arrays !!") ;
		byte[] dummy = new byte[4];
		for (int i=num-1;i>=0;i--){
			dummy[ i + (4 -  num)] = abytearr[i];
		}
		ByteBuffer buf = ByteBuffer.wrap(dummy);
		int z = buf.getInt();
		return z;
	}  


    /**
     * Convert input binary byte array (up to 8 bytes) to long.
     * <p>
     * Example : (byte)0x00,(byte)0x00,(byte)0xFF,(byte)0xFF --> 65535
     * @param 	abytearr	input data byte array
     * @return 	int result value
     * @exception java.lang.IllegalArgumentException 
     */
    
	public static long toLong ( byte[] abytearr ) throws IllegalArgumentException {
		
		int num = abytearr.length ;
		if ( num > 8 ) throw new IllegalArgumentException("up to 8 length arrays !!") ;
		byte[] dummy = new byte[8];
		for (int i=num-1;i>=0;i--){
			dummy[ i + (8 -  num)] = abytearr[i];
		}
		ByteBuffer buf = ByteBuffer.wrap(dummy);
		long z = buf.getLong();
		return z;
	}     	
 	
    /** 
     * Convert input binary byte array to long.
     * <p>
     * @param 	abytearr input data byte array
     * @param  	radix 10 Decimal 16 Hexadecimal 8 Octal
     * @return 	int result value
     * @exception java.lang.IllegalArgumentException  
     * 
     */
	public static long toLongBaseRadix ( byte[] abytearr , int radix ) throws IllegalArgumentException {
		
		long value = 0 ;
		int num = abytearr.length ;
		if ( num > 8 ) throw new IllegalArgumentException("up to 8 length arrays !!") ;
		
		int k = num * 2 -  1  ;
		for (int i=0; i< num ; i++){
			int uppernibble = (int) (  ( abytearr[i] & 0xF0 ) >> 4 ) ;
			int lowernibble = (int) (  abytearr[i]   & 0x0F ) ;
			value =   (int) ( uppernibble *  Math.pow( radix , k ) ) + value ;
			k--;
			value =   (int) ( lowernibble *  Math.pow( radix , k ) ) + value ;
			k--;
		}
		
		return value ; 
	}  
	
	/**
	 * Convert an input integer to a binary array. 
	 * <p>
	 * Example : 65535 --> (byte)0x00, (byte)0x00, (byte)0xFF , (byte)0xFF	
	 * @param  	value	integer to be converted
	 * @return	byte[]  binary coded byte array
	 * 
	 */
	public static byte[] int2byteArray ( int value ){
		
		byte[] bytes = ByteBuffer.allocate(4).putInt(value).array();
		return bytes;
	}  

	/**
	 * Convert an input long to a binary array 
	 * <p>	
	 * @param  	value	long to be converted
	 * @return	byte[]  binary coded byte array
	 * 
	 */
	public static byte[] long2byteArray ( long value ){
	
		byte[] bytes = ByteBuffer.allocate(8).putLong(value).array();
		return bytes;
	}
	/**
     * Return input byte as a bit string representation.
     * <p>
     * Example :  (byte)0xEA --> "11101010" 
     * @param data the byte to be converted.
     * @return a string representation of bits (bit by bit) 
     */
    public static String toBitStr( byte data) {
    	
        StringBuffer  bufu = new StringBuffer();
        StringBuffer  bufl = new StringBuffer();
        String result = "" ;
        
    	byte uppernibble = (byte)( ( data & 0xF0 ) >> 4 );
    	byte lowernibble = (byte)( ( data & 0x0F )) ;

    	byte nibble		 = 0x00 ;
    	byte ab = 0x00 ;
//    	
    	nibble = uppernibble ;
    	for ( int i=1; i<=4; i++){
    		ab = (byte)( nibble & 0x01 ) ;
    		nibble = (byte) (nibble >> 1)  ;
    		bufu.append( hexDigits.charAt( ab) );
    	}
    	result = bufu.reverse().toString() ; 
    	  
    	nibble = lowernibble ;
    	for ( int i=1; i<=4; i++){
    		ab = (byte)( nibble & 0x01 ) ;
    		nibble = (byte) (nibble >> 1)  ;
    		bufl.append( hexDigits.charAt( ab) );
    	}
    	
    	result = result + bufl.reverse().toString() ;  	
        return  result ;
    }

	/**
     * Return input byte array as a bit string.
     * <p>
     * Example :  (byte)0xEA,(byte)0x07 --> "1110101000000111"  
     * @param abytearr byte array to be converted.
     * @return a string representation of bits (bit by bit) 
     */
    public static String toBitStr( byte[] abytearr) {
    	
    	String result = "";
    	for ( int i=0; i<abytearr.length; i++){
    		result = result + toBitStr ( abytearr[i] );
    	}
    	
    	return result ; 
    }

	/**
	 * Return input byte as a hex string.
	 * <p>
	 * Example :  0xFA --> "FA"
	 * @param abyte to be converted.
	 * @return a hex representation of data.
	 */
	public static String toHexStr( byte abyte) {
	    int v = abyte & 0xff;
	    StringBuffer  buf = new StringBuffer();; 
	    buf.append(hexDigits.charAt(v >> 4));
	    buf.append(hexDigits.charAt(v & 0xf));
	    return buf.toString();
	}
	/**
	 * Return input byte array as a hex string.
	 * <p>
	 * Example :  0xFA,0xBC --> "FABC"
	 * @param abytearr byte array to be converted.
	 * @return a hex representation of data.
	 */
	public static String toHexStr(byte[] abytearr ) {
	    StringBuffer    buf = new StringBuffer();
	
	    for (int i = 0; i != abytearr.length; i++)
	    {
	        int v = abytearr[i] & 0xff;
	
	        buf.append(hexDigits.charAt(v >> 4));
	        buf.append(hexDigits.charAt(v & 0xf));
	    }
	
	    return buf.toString( );
	}
	/**
	 * Convert an input byte array into a String Non-printable characters are replaced by '.'
	 * <p>
	 * @param abytearr the array containing the characters
	 * @return a String representation for the byte array
	 */
	public static String toPrintableString( byte[] abytearr) {
		
		return toPrintableString ( abytearr , '.');
	}
    
    /**
	 * Convert an input byte array into a String Non-printable characters are replaced by char c.
	 * <p>
	 * Example: toPrintableString when replacing char is '*' for<br>
	 * input  : <br>
	 * (byte)0x00,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,(byte)0x37,<br>
	 * (byte)0x38,(byte)0xFF,(byte)0x00,(byte)0x42,(byte)0x43,(byte)0x44,(byte)0x45,(byte)0xFF<br>
	 * output : <br>"*12345678**BCDE*"
	 * @param  abytearr the array containing the characters
	 * @param  c substitution char for non-printable characters
	 * @return a String representation for the byte array
	 */
    public static String toPrintableString( byte[] abytearr, char c ) {
    	
    	char[]	chars = new char[abytearr.length ];
        
        for (int i = 0; i != chars.length; i++) {
        	char ch = (char) abytearr[i] ;
        	if( Ascii.isPrintable(ch) ) {
        		 chars[i] = ch ;	 
        	}else{
        		 chars[i] = c ;
        	}
        }
        return new String ( chars );
    }



	/**
	 * TODO 
	 * <p>
	 * @param 	blkIN  byte array
	 * @return 	byte array
	 */
	public static byte[] compressRigthNibbles(byte[] blkIN ){
		
		byte[] blkOUT	= new byte[blkIN.length/2];
		for (int i = 0 ; i < blkIN.length  ; i++ ){
			int nibble = 0 , index = 0;
			index = ( i / 2) ;
			if ( (i % 2)  == 0) { 		// i is even			
				nibble = (byte) (  ( blkIN[i] << 4 ) & 0xF0 );			// Shift Rigth most 4 bits of each character to position 1 to 4														
				blkOUT[index] = (byte) ( nibble ) ;						// AND with 0xF0 -->  xxxx0000
			}else{ 						// i is odd
				nibble = (byte) (  ( blkIN[i] ) & 0x0F );				// AND with 0xF0 	-->  0000zzzz
				blkOUT[index] = (byte) ( blkOUT[index] | nibble ) ;		// blkOUT OR nibble -->  xxxxzzzz
			}
		}
		return blkOUT ;
	}



	/**
	 * Uncompress a BCD numeric byte array (array_length mod 4 = 0), to an ASCII byte array
	 * <p>
	 * Example :
	 * <pre>
	 *  Input  : (byte)0x12,(byte)0x34,(byte)0x56,(byte)0x78  BCD compressed
	 *  Output : (byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,(byte)0x37,(byte)0x38 ( i.e. 12345678 )  
	 * </pre> 
	 * @param blkIN input Ascii numeric byte array
	 * @return compressed BCD byte array
	 * @exception java.lang.IllegalArgumentException
	 * 
	 */
	public static byte[] uncompressBlock(byte[] blkIN ){
		
		//if ( ! Bcd.isBcd( blkIN ) ) throw new IllegalArgumentException("is not a BCD numeric byte array") ;
		//int remainder = blkIN.length % 4 ;
		//if (  remainder != 0  ) throw new IllegalArgumentException("is not a modulus 4 array") ;
		
		byte[] blkOUT	= new byte[blkIN.length * 2];
		 for (int i = 0 ; i < blkIN.length  ; i++ ){
			 int index = ( i * 2  ) ;
			 byte aBytea = 0, aByteb = 0;
			 aBytea = (byte) ( ( blkIN[i] & 0xF0 ) >> 4 );
			 aByteb = (byte) (   blkIN[i] & 0x0F ) ;
			 blkOUT[index] 		= (byte) ( aBytea ) ;
			 blkOUT[index + 1] 	= (byte) ( aByteb ) ;
		}
		return blkOUT ;
	}



	/**
	 * Compress an Binary byte array.
	 * <p>
	 * Example :
	 * <pre>
	 *  Input  : 
	 *  Output : 
	 * </pre> 
	 * @param blkIN input Ascii numeric byte array
	 * @return compressed byte array
	 * 
	 */
	public static byte[] compressBlock(byte[] blkIN)  {
	
		byte[] blkOUT = new byte[blkIN.length / 2];
		for (int i = 0; i < blkIN.length; i++) {
			int nibble = 0, index = 0;
			index = (i / 2);
			if ((i % 2) == 0) { // i is even
				nibble = (byte) (blkIN[i] << 4);
				blkOUT[index] = (byte) (blkOUT[index] | nibble);
			} else { // i is odd
				blkOUT[index] = (byte) (blkOUT[index] | blkIN[i]);
			}
		}
		return blkOUT;
	}  
}

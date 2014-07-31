package es.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class AsciiTest  {
	public String className = this.getClass().getSimpleName() + "." ;
   
    
	@Test
	public void string2byteArray() {
		
	    String hexDigits = "0123456789ABCDEF";
		byte[] hexResult = { (byte)0x30,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,
				  			 (byte)0x37,(byte)0x38,(byte)0x39,
				  			 (byte)0x41,(byte)0x42,(byte)0x43,(byte)0x44,(byte)0x45,(byte)0x46}; 
		
		byte[]	abytearr = Ascii.string2byteArray(hexDigits) ; 
		for (int i=0; i< abytearr.length;i++){
			char c = hexDigits.charAt(i);
			if ( abytearr[i] == hexResult[i]  ){
				System.out.printf( className + "string2byteArray : TRUE  %c 0x%02X-->0x%02X \n", c, abytearr[i] , hexResult[i] );
			}else{  
				System.out.printf( className + "string2byteArray : FALSE %c 0x%02X-->0x%02X \n", c, abytearr[i] , hexResult[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 
	}

	@Test
	public void int2asciiArray() {
	    int num = 1234567890;
		byte[] hexResult = { (byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,
				  			 (byte)0x37,(byte)0x38,(byte)0x39,(byte)0x30 }; 
		
		byte[]	abytearr = Ascii.int2byteArray(num); 
		for (int i=0; i< abytearr.length;i++){
			if ( abytearr[i] == hexResult[i]  ){
				System.out.printf( className + "int2asciiArray : TRUE  0x%02X-->0x%02X \n", abytearr[i] , hexResult[i] );
			}else{  
				System.out.printf( className + "int2asciiArray : FALSE 0x%02X-->0x%02X \n", abytearr[i] , hexResult[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 
	}

	@Test
	public void getNumericValue() {
		byte[] hexarr = { (byte)0x61,(byte)0x62,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,(byte)0x41,(byte)0x42  }; 
		byte[] result = { (byte)0x0A,(byte)0x0B,(byte)0x03,(byte)0x04,(byte)0x05,(byte)0x06,(byte)0x0A,(byte)0x0B  };
		
		byte[]	abytearr = Ascii.getNumericValue(hexarr); 
		for (int i=0; i< abytearr.length;i++){
			if ( abytearr[i] == result[i]  ){
				System.out.printf( className + "getNumericValue : TRUE  0x%02X-->0x%02X \n", abytearr[i] , result[i] );
			}else{  
				System.out.printf( className + "getNumericValue : FALSE 0x%02x-->0x%02X \n", abytearr[i] , result[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 
	}
	
	@Test
	public void isNumeric() {
		byte[] abytearr = { (byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,
	  			            (byte)0x37,(byte)0x38,(byte)0x39,(byte)0x30 }; 
 
		for (int i=0; i< abytearr.length;i++){
			boolean abool = Ascii.isNumeric( abytearr[i]);
			if ( abool ){
				System.out.printf( className + "isNumericAscii : TRUE  0x%02X %c\n",  abytearr[i], (char)abytearr[i] );
			}else{  
				System.out.printf( className + "isNumericAscii : FALSE 0x%02X %c\n",  abytearr[i], (char)abytearr[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 		
	}

	@Test
	public void isNumeric_02() {
		byte[] abytearr = { (byte)0x29,(byte)0x40 }; 
 
		for (int i=0; i< abytearr.length;i++){
			boolean abool = Ascii.isNumeric( abytearr[i]);
			if ( ! abool ){
				System.out.printf( className + "isNumericAscii_02 : TRUE  0x%02X %c\n",  abytearr[i], (char)abytearr[i] );
			}else{  
				System.out.printf( className + "isNumericAscii_02 : FALSE 0x%02X %c\n",  abytearr[i], (char)abytearr[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 		
	}
	
	@Test
	public void isNumeric_03() {
		char c = '9' ;
 
		boolean abool = Ascii.isNumeric( c );
		if ( abool ){
			System.out.printf( className + "isNumericAscii_03 : TRUE  0x%02X --> %c \n", (byte)c, c );
			assertTrue( true) ; 
		}else{  
			System.out.printf( className + "isNumericAscii_03 : FALSE 0x%02X --> %c \n", (byte)c, c );
			assertFalse( true) ;
		}
		
	}
	
	@Test
	public void isNumericHex() {
		byte[] abytearr = { (byte)0x41,(byte)0x42,(byte)0x43,(byte)0x44,(byte)0x45,(byte)0x46,
						    (byte)0x61,(byte)0x62,(byte)0x63,(byte)0x64,(byte)0x65,(byte)0x66}; 
 
		for (int i=0; i< abytearr.length;i++){
			boolean abool = Ascii.isNumericHex( abytearr[i]);
			if ( abool ){
				System.out.printf( className + "isNumericHex : TRUE  0x%02x %c \n",  abytearr[i], (char) abytearr[i] );
			}else{  
				System.out.printf( className + "isNumericHex : FALSE 0x%02x %c \n",  abytearr[i], (char) abytearr[i] );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 		
	}
	
	@Test
	public void isNumericHex_02() {
		byte[] abytearr = { (byte)0x29,(byte)0x40 }; 
 
		for (int i=0; i< abytearr.length;i++){
			boolean abool = Ascii.isNumericHex( abytearr[i]);
			if ( ! abool ){
				System.out.printf( className + "isNumericHex_02 : TRUE  0x%02x %c\n",  abytearr[i], (char) abytearr[i]  );
			}else{  
				System.out.printf( className + "isNumericHex_02 : FALSE 0x%02x %c\n",  abytearr[i], (char) abytearr[i]  );
				assertFalse( true) ;
			}
		}	
		assertTrue( true) ; 		
	}
	
	@Test
	public void isNumericHex_03() {
		char c = 'f' ;
 
		boolean abool = Ascii.isNumericHex( c );
		if ( abool ){
			System.out.printf( className + "isNumericHex_03 : TRUE  0x%02X --> %c \n", (byte)c, c );
			assertTrue( true) ; 
		}else{  
			System.out.printf( className + "isNumericHex_03 : FALSE 0x%02X --> %c \n", (byte)c, c );
			assertFalse( true) ;
		}
		
	}

	@Test
	public void toInt() {
		byte[] abytearr = { (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 };
		int value = Ascii.toInt(abytearr);
		if ( value == 6553 ){
			System.out.println( className + "toInt : TRUE  (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 --> " + value) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toInt : FALSE (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 --> " + value ) ;
			assertFalse( true) ;
		}
	}

	@Test
	public void toLong() {
	
		byte[] abytearr = { (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 };
		long value = Ascii.toLong(abytearr);
		if ( value == 6553L ){
			System.out.println( className + "toLong  : TRUE  (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 --> " + value) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toLong  : FALSE (byte)0x36,(byte)0x35,(byte)0x35,(byte)0x33 --> " + value ) ;
			assertFalse( true) ;
		}		
		
	}	
}

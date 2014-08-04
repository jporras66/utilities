package es.indarsoft.utl;

import static org.junit.Assert.*;
import org.junit.Test;

public class BinaryTest {

	public String className = this.getClass().getSimpleName() + "." ;
    private static final String hexDigits = "0123456789ABCDEF";
	byte[] hexResult = { (byte)0x30,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,
			  			 (byte)0x37,(byte)0x38,(byte)0x39,
			  			 (byte)0x41,(byte)0x42,(byte)0x43,(byte)0x44,(byte)0x45,(byte)0x46};    
     
	
	@Test
	public void toInt () {
		byte[] hexarr = {  (byte)0xFF, (byte)0xFF};    
		int ai = Binary.toInt(hexarr);
		
		if ( ai == 65535  ){
			System.out.println( className + "toInt : TRUE  (byte)0xFF , (byte)0xFF --> "  + ai ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toInt : FALSE (byte)0xFF , (byte)0xFF --> " +  ai) ;
			assertFalse( true) ;
		}
	}
	
	@Test
	public void toInt_02 () {
		byte[] hexarr = {  (byte)0x00, (byte)0xFF, (byte)0xFF};    
		int ai = Binary.toInt(hexarr);
		
		if ( ai == 65535  ){
			System.out.println( className + "toInt_02 : TRUE  (byte)0x00, (byte)0xFF, (byte)0xFF --> "  + ai ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toInt_02 : FALSE (byte)0x00, (byte)0xFF, (byte)0xFF --> " +  ai) ;
			assertFalse( true) ;
		}
	}
	
	@Test
	public void toLong () {
		byte[] hexarr = {  (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xFF};    
		long ai = Binary.toLong(hexarr);
		
		if ( ai == 65535  ){
			System.out.println( className + "toLong : TRUE  (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xFF --> "  + ai ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toLong : FALSE (byte)0x00, (byte)0x00, (byte)0xFF, (byte)0xFF --> " +  ai) ;
			assertFalse( true) ;
		}
	}
	
	@Test
	public void int2byteArray () { 
		int value = 65535 ;
		byte[] abytearr = Binary.int2byteArray(value);
		
		if ( abytearr[0] == (byte)0x00 && abytearr[1] == (byte)0x00 &&
			 abytearr[2] == (byte)0xFF && abytearr[3] == (byte)0xFF	){
			System.out.println( className + "int2byteArray : TRUE  " + value + "-->" +"(byte)0x00,(byte)0x00,(byte)0xFF,(byte)0xFF ") ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "int2byteArray : FALSE " ) ;
			assertFalse( true) ;
		}
	}
	@Test
	public void long2byteArray () { 
		long value = 65535L ;
		byte[] abytearr = Binary.long2byteArray(value);
		
		if ( abytearr[0] == (byte)0x00 && abytearr[1] == (byte)0x00 &&
			 abytearr[2] == (byte)0x00 && abytearr[3] == (byte)0x00 &&
			 abytearr[4] == (byte)0x00 && abytearr[5] == (byte)0x00 &&
			 abytearr[6] == (byte)0xFF && abytearr[7] == (byte)0xFF	){
			System.out.println( className + "long2byteArray : TRUE  " + value + "-->" +"(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0x00,(byte)0xFF,(byte)0xFF ") ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "long2byteArray : FALSE " ) ;
			assertFalse( true) ;
		}
	}	
	@Test
	public void toBitStr () {
		byte data = (byte) 0xEA;  
		String str = Binary.toBitStr(data);
		
		if ( str.equals("11101010")){
			System.out.println( className + "toBitStr : TRUE  0xEA --> " + str) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toBitStr : FALSE 0xEA --> " + str) ; 
			assertFalse( true) ;
		}
	}
	
	@Test
	public void toBitStr_02 () {
		byte[] data = {(byte) 0xEA, (byte) 0x07} ;  
		String str = Binary.toBitStr(data);
		
		if ( str.equals("1110101000000111")){
			System.out.println( className + "toBitStr_02 : TRUE  0xEA,0x07 --> " + str) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toBitStr_02 : FALSE 0xEA,0x07 --> " + str) ; 
			assertFalse( true) ;
		}
	}
	
	@Test
	public void toHexStr () {
		byte data = (byte) 0xEA;  
		String str = Binary.toHexStr(data);
		
		if ( str.equals("EA")){
			System.out.println( className + "toHexStr : TRUE  0xEA --> " + str) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toHexStr : FALSE 0xEA --> " + str) ; 
			assertFalse( true) ;
		}
	}	
	
	@Test
	public void toHexStr_02 () {
		byte[] data = {(byte) 0xEA, (byte) 0xFB};  
		String str = Binary.toHexStr(data);
		
		if ( str.equals("EAFB")){
			System.out.println( className + "toHexStr_02 : TRUE  0xEA,0xFB --> " + str) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toHexStr_02 : FALSE 0xEA,0xFB --> " + str) ; 
			assertFalse( true) ;
		}
	}	
	@Test
	public void toPrintableString() {
		String astr = Binary.toPrintableString(hexResult);
		if ( astr.equals(hexDigits)  ){
			System.out.println( className + "toPrintableString : TRUE  " + astr + "-->" + hexDigits ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toPrintableString : FALSE " + astr + "-->" + hexDigits ) ;
			assertFalse( true) ;
		}
	}
	
	@Test
	public void toPrintableString_02() {
		String result = "*12345678**BCDE*";
		byte[] hexarr = { (byte)0x00,(byte)0x31,(byte)0x32,(byte)0x33,(byte)0x34,(byte)0x35,(byte)0x36,
	  			          (byte)0x37,(byte)0x38,(byte)0xFF,
	  			          (byte)0x00,(byte)0x42,(byte)0x43,(byte)0x44,(byte)0x45,(byte)0xFF};    
		String astr = Binary.toPrintableString(hexarr,'*');
		if ( astr.equals(result)  ){
			System.out.println( className + "toPrintableString_02 : TRUE  " + astr + "-->" + result ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toPrintableString_02 : FALSE " + astr + "-->" + result ) ;
			assertFalse( true) ;
		}
	}	
}

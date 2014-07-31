package es.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class EbcdicTest {

	public String className = this.getClass().getSimpleName() + "." ;

	@Test
	public void int2byteArray() {
		int value = 6553 ;
		byte[] abytearr = Ebcdic.int2byteArray(value);
		if ( abytearr[0] == (byte)0xF6 && abytearr[1] == (byte)0xF5 &&
			 abytearr[2] == (byte)0xF5 &&abytearr[3] == (byte)0xF3 ){
			System.out.println( className + "int2byteArray : TRUE  " + value + "-->" +"(byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 ") ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "int2byteArray : FALSE " ) ;
			assertFalse( true) ;
		}
	}

	@Test
	public void isNumericEbcdicByte() {
		
		byte abyte = (byte)0xF6 ;
		if ( Ebcdic.isNumericEbcdic(abyte) ){
				System.out.printf( className + "isNumericEbcdicByte : TRUE  - 0xF0 <=  0x%X <= 0xF9 "  , abyte ) ; 
				assertTrue( true) ; 
		}else{  
				System.out.printf( className + "isNumericEbcdicByte : FALSE - 0xF0 <=  0x%X <= 0xF9 " , abyte) ;
				assertFalse( true) ;
		}
	}

	@Test
	public void isNumericEbcdicByteArray() {
		
		byte[] abytearr = { (byte)0xF0 ,  (byte)0xF5, (byte)0xF9} ;
		if ( Ebcdic.isNumericEbcdic(abytearr) ){
			for (int i=0; i<abytearr.length;i++){
				System.out.printf( className + "isNumericEbcdicByte : TRUE  - 0xF0 <=  0x%X <= 0xF9 \n"  , abytearr[i] ) ;	
			} 
			assertTrue( true) ; 
		}else{  
			for (int i=0; i<abytearr.length;i++){
				System.out.printf( className + "isNumericEbcdicByte : FALSE - 0xF0 <=  0x%X <= 0xF9 \n"  , abytearr[i] ) ;	
			} 
			assertFalse( true) ;
		}
	}

	@Test
	public void toInt() {
		byte[] abytearr = { (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 };
		int value = Ebcdic.toInt(abytearr);
		if ( value == 6553 ){
			System.out.println( className + "toInt : TRUE  (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 --> " + value) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toInt : FALSE (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 --> " + value ) ;
			assertFalse( true) ;
		}
	}

	@Test
	public void toLong() {

		byte[] abytearr = { (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 };
		long value = Ebcdic.toInt(abytearr);
		if ( value == 6553L ){
			System.out.println( className + "toLong  : TRUE  (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 --> " + value) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toLong  : FALSE (byte)0xF6,(byte)0xF5,(byte)0xF5,(byte)0xF3 --> " + value ) ;
			assertFalse( true) ;
		}		
		
	}

}

package es.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;

public class BcdTest {

	public String className = this.getClass().getSimpleName() + "." ;
	
	@Test
	public void binaryArray2intBaseDec () {
		byte[] hexarr = { (byte)0x00, (byte)0x99, (byte)0x99 , (byte)0x99};    
		int ai = Bcd.binaryArray2intBaseDec(hexarr);
		
		if ( ai == 999999  ){
			System.out.println( className + "binaryArray2int : TRUE  (byte)0x00, (byte)0x99, (byte)0x99 , (byte)0x99 --> "  + ai ) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "binaryArray2int : FALSE (byte)0x00, (byte)0x99, (byte)0x99 , (byte)0x99 --> " +  ai) ;
			assertFalse( true) ;
		}
	}

	@Test
	public void toInt() {
		byte[] abytearr = { (byte)0x65,(byte)0x53 };
		int value = Bcd.toInt(abytearr);
		if ( value == 6553 ){
			System.out.println( className + "toInt  : TRUE  (byte)0x65,(byte)0x53 --> " + value) ; 
			assertTrue( true) ; 
		}else{   
			System.out.println( className + "toInt  : FALSE (byte)0x65,(byte)0x53 --> " + value ) ;
			assertFalse( true) ;
		}
	}

	@Test
	public void toLong() {
	
		byte[] abytearr = { (byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53 };
		long value = Bcd.toLong(abytearr);
		if ( value == 655365536553L ){
			System.out.println( className + "toLong : TRUE  (byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53 --> " + value) ; 
			assertTrue( true) ; 
		}else{  
			System.out.println( className + "toLong : FALSE (byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53,(byte)0x65,(byte)0x53 --> " + value ) ;
			assertFalse( true) ;
		}		
		
	}
}

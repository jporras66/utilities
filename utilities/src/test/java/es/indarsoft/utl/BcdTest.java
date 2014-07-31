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
}

package com.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;

import com.indarsoft.utl.Bcd;

public class BcdTest {

	public String className = this.getClass().getSimpleName() + "." ;
	

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

package es.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;

import es.indarsoft.utl.ArrayUtl;

public class ArrayUtlTest {

	public String className = this.getClass().getSimpleName() + "." ;
	public static byte[] ab1 = { (byte)0x30, (byte)0x31 , (byte)0x32 , (byte)0x33 } ;
	public static byte[] ab2 = { (byte)0x34, (byte)0x35 , (byte)0x36 , (byte)0x37 } ;	
	
	@Test
	public void testConcatByteArray() {

		String expectedResult = "01234567";
		byte[] result = ArrayUtl.concat ( ab1, ab2 );
		String s3  = new String(result);
		if ( s3.equals( expectedResult)){
			System.out.println( className + "testConcatByteArray : TRUE  - " + expectedResult+" --> "+s3);
			assertTrue( true) ;
		}else{ 
			System.out.println( className + "testConcatByteArray : FALSE - " + expectedResult+" --> "+s3);
			assertFalse( true) ;	
		}	
	}

}

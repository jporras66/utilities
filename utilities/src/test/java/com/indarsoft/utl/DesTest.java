package com.indarsoft.utl;

import static org.junit.Assert.*;

import org.junit.Test;
import com.indarsoft.utl.ArrayUtl;
import com.indarsoft.utl.Binary;
import com.indarsoft.utl.Des;

public class DesTest {

	public String className = this.getClass().getSimpleName() + "." ;
	byte[] deskeyA = { (byte) 0x01,(byte) 0x23,(byte) 0x45,(byte) 0x67,
					   (byte) 0x89,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
	byte[] deskeyB = { (byte) 0x01,(byte) 0x23,(byte) 0x45,(byte) 0x67,
			  		   (byte) 0x89,(byte) 0xAB,(byte) 0xCD,(byte) 0xEF};
	String clearText ="HI WORLD";
	byte[] cipher  = { (byte) 0x0E,(byte) 0xAA,(byte) 0x58,(byte) 0xBF,
					   (byte) 0x52,(byte) 0x2C,(byte) 0x95,(byte) 0xC4};
			
	@Test
	public void decrypt() {
		byte[] clear = null;
		byte[] abytearr = clearText.getBytes() ;
		try {
			clear = Des.decrypt( cipher,  deskeyA);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		for (int i=0;i<clear.length;i++){
			if ( clear[i] == abytearr[i]){
				System.out.printf( className + "decrypt : TRUE  0x%02X --> 0x%02X \n",clear[i], abytearr[i]  ) ;
			}else{
				System.out.printf( className + "decrypt : FALSE 0x%02X --> 0x%02X \n",clear[i], abytearr[i]  ) ;
				assertFalse( true) ;				
			}
		}
		System.out.println( className + "decrypt : TRUE  " +  Binary.toPrintableString( clear )  ) ;
		assertTrue( true) ; 
	}

	@Test
	public void encrypt() {
		byte[] abytearr = clearText.getBytes() ;
		byte[] ciphertxt = null;
		try {
			ciphertxt = Des.encrypt( abytearr,  deskeyA);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i=0;i<ciphertxt.length;i++){
			if ( ciphertxt[i] == cipher[i]){
				System.out.printf( className + "encrypt : TRUE  0x%02X --> 0x%02X \n",ciphertxt[i], cipher[i]  ) ;
			}else{
				System.out.printf( className + "encrypt : FALSE 0x%02X --> 0x%02X \n",ciphertxt[i], cipher[i]  ) ;
				assertFalse( true) ;				
			}
		}
		System.out.println( className + "encrypt : TRUE  " +  Binary.toPrintableString( ciphertxt )  ) ;
		assertTrue( true) ; 
	}

	@Test
	public void tdesDecrypt() {
		byte[] clear = null;
		byte[] abytearr = clearText.getBytes();
		try {
			byte[] deskey = ArrayUtl.concat( deskeyA , deskeyB );
			clear = Des.tdesDecrypt( cipher,  deskey);
		} catch (Exception e) {
			e.printStackTrace();
		}		
		for (int i=0;i<clear.length;i++){
			if ( clear[i] == abytearr[i]){
				System.out.printf( className + "tdesDecrypt : TRUE  0x%02X --> 0x%02X \n",clear[i], abytearr[i]  ) ;
			}else{
				System.out.printf( className + "tdesDecrypt : FALSE 0x%02X --> 0x%02X \n",clear[i], abytearr[i]  ) ;
				assertFalse( true) ;				
			}
		}
		System.out.println( className + "tdesDecrypt : TRUE  " +  Binary.toPrintableString( clear )  ) ;
		assertTrue( true) ; 
	}

	@Test
	public void tdesEncrypt() {
		byte[] abytearr = clearText.getBytes();
		byte[] ciphertxt = null;
		try {
			byte[] deskey = ArrayUtl.concat( deskeyA , deskeyB );
			ciphertxt = Des.tdesEncrypt( abytearr,  deskey);
		} catch (Exception e) {
			e.printStackTrace();
		}
		for (int i=0;i<ciphertxt.length;i++){
			if ( ciphertxt[i] == cipher[i]){
				System.out.printf( className + "tdesEncrypt : TRUE  0x%02X --> 0x%02X \n",ciphertxt[i], cipher[i]  ) ;
			}else{
				System.out.printf( className + "tdesEncrypt : FALSE 0x%02X --> 0x%02X \n",ciphertxt[i], cipher[i]  ) ;
				assertFalse( true) ;				
			}
		}
		System.out.println( className + "tdesEncrypt : TRUE  " +  Binary.toPrintableString( ciphertxt )  ) ;
		assertTrue( true) ;
	}

}

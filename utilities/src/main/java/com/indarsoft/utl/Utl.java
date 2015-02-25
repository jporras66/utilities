package com.indarsoft.utl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.lang.management.ManagementFactory;
import java.lang.management.RuntimeMXBean;
/**
 * Some utilities
 * 
 * @author fjavier.porras@gmail.com
 * 
 */
public class Utl {
	
	private static final String separator = File.separator;
	
	public static String getPwd() {

		File file;
		int index;
		String cwd = null;

		file = new File( "." );
		index = file.getAbsolutePath().lastIndexOf( separator );
				
		try {
			cwd = file.getAbsolutePath().substring( 0, index );
		}
		catch ( StringIndexOutOfBoundsException e ){
			System.err.println( "Caught Exception: " + e.getMessage() + "\n" );
		}

		return cwd;
	}
	
	public static long getProcessId() {

		RuntimeMXBean bean = ManagementFactory.getRuntimeMXBean(); 
        //
        // Get name representing the running Java virtual machine.
        // It returns something like 6460@AURORA. Where the value
        // before the @ symbol is the PID.
        //
        String jvmName = bean.getName();
        System.out.println("Name = " + jvmName);
 
        //
        // Extract the PID by splitting the string returned by the
        // bean.getName() method.
        //
        long pid = Long.valueOf(jvmName.split("@")[0]);
        //System.out.println("PID  = " + pid);
        return pid  ;
    }	

	public static byte[] loadBinary ( String filename ){
//		
		byte[] bytearr 		= null ; 
		FileInputStream fin = null;
		int k = 0;
//
    	try { 
    		fin = new FileInputStream(filename);
    		bytearr = new byte[ fin.available() ] ; 
    		
			int data= 0 ;
    		 while( (  data=fin.read() ) != -1)
             {
    			 bytearr[k] = (byte) data ;
    			 k++;
             }
    		return Arrays.copyOfRange ( bytearr , 0, k );
    	} catch (IOException ex) {
    		ex.printStackTrace();
    	} finally {
    		if (fin != null) {
    			try {
    				fin.close();
    			} catch (IOException e) {
    				e.printStackTrace();
    			}
       		}
    	}
    	return Arrays.copyOfRange ( bytearr , 0, k );
    }
	
	public static void writeBinary ( String filename , byte[] bar){
		
		try {
			FileOutputStream fout = new FileOutputStream ( filename );
			fout.write( bar );
			fout.close() ;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	public static String getSeparator() {
		return separator;
	}
}

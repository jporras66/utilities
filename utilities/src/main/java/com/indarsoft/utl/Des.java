package com.indarsoft.utl;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
/**
 * DES and TDES DES/ECB/NoPadding functions
 * 
 * @author fjavier.porras@gmail.com
 * 
 */ 
public class Des {

	private static final int BLK24 = 24; 	// (24 * 8 = 196 bits )
	private static final int BLK16 = 16; 	// (16 * 8 = 128 bits )
	private static final int BLK08 = 8; 	// (8 * 8 = 64 bits )
	private static final String DES_ECB_NOPPADING = "DES/ECB/NoPadding";
    /**
     * Decrypt an input binary array
     * <p>
     * @param 	cipherText input data byte array
     * @param 	desKey input key
     * @return 	clear text
     * @exception java.lang.Exception 
     */
	public static byte[] decrypt(byte[] cipherText, byte[] desKey) throws Exception {

		byte[] aDecryptedBlock;
		aDecryptedBlock = decryptNoPadding(cipherText, desKey);
		return aDecryptedBlock;
	}

	private static byte[] decryptNoPadding(byte[] encryptedText, byte[] desKey) throws Exception {

		SecretKeySpec key = new SecretKeySpec(desKey, "DES");
		Cipher cipher = Cipher.getInstance(DES_ECB_NOPPADING);
		cipher.init(Cipher.DECRYPT_MODE, key);
		byte[] clearText = new byte[cipher.getOutputSize(encryptedText.length)];

		int ptLength = cipher.update(encryptedText, 0, encryptedText.length,clearText, 0);
		ptLength += cipher.doFinal(clearText, ptLength);
		return clearText;
	}
    /**
     * Encrypt an input binary array
     * <p>
     * @param 	clearText input data byte array
     * @param 	desKey input key
     * @return 	cipher text
     * @exception java.lang.Exception 
     */
	public static byte[] encrypt(byte[] clearText, byte[] desKey) throws Exception {

		byte[] aEncryptedBlock;

		aEncryptedBlock = encryptNoPadding(clearText, desKey);
		return aEncryptedBlock;
	}

	private static byte[] encryptNoPadding(byte[] clearText, byte[] desKey) throws Exception {

		SecretKeySpec key = new SecretKeySpec(desKey, "DES");
		Cipher cipher = Cipher.getInstance(DES_ECB_NOPPADING);
		cipher.init(Cipher.ENCRYPT_MODE, key);
		byte[] encryptedText = new byte[cipher.getOutputSize(clearText.length)];

		int ctLength = cipher.update(clearText, 0, clearText.length,encryptedText, 0);
		ctLength += cipher.doFinal(encryptedText, ctLength);
		return encryptedText;
	}

	public static byte[] tdesDecrypt(byte[] aText, byte[] desKey) throws Exception {

		byte[] aDecryptedBlock = new byte[BLK08];
		byte[] aDecryptedBlockA = new byte[BLK08];
		byte[] aDecryptedBlockB = new byte[BLK08];

		byte[] keyA = new byte[BLK08];
		byte[] keyB = new byte[BLK08];
		byte[] keyC = new byte[BLK08];

		if (desKey.length == BLK16) { // DOUBLE key
			for (int i = 0; i < BLK08; i++) {
				keyA[i] = (byte) desKey[i];
				keyB[i] = (byte) desKey[i + BLK08];
				keyC[i] = (byte) desKey[i];
			}
		} else if (desKey.length == BLK24) { // TRIPLE key
			for (int i = 0; i < BLK08; i++) {
				keyA[i] = (byte) desKey[i];
				keyB[i] = (byte) desKey[i + BLK08];
				keyC[i] = (byte) desKey[i + BLK16];
			}
		}

		aDecryptedBlockA = decrypt(aText, keyA);
		aDecryptedBlockB = encrypt(aDecryptedBlockA, keyB);
		aDecryptedBlock = decrypt(aDecryptedBlockB, keyC);

		return aDecryptedBlock;
	}

	public static byte[] tdesEncrypt(byte[] aText, byte[] desKey)
			throws Exception {

		byte[] aEncryptedBlock = new byte[BLK08];
		byte[] aEncryptedBlockA = new byte[BLK08];
		byte[] aEncryptedBlockB = new byte[BLK08];

		byte[] keyA = new byte[BLK08];
		byte[] keyB = new byte[BLK08];
		byte[] keyC = new byte[BLK08];

		if (desKey.length == BLK16) { // DOUBLE key
			for (int i = 0; i < BLK08; i++) {
				keyA[i] = (byte) desKey[i];
				keyB[i] = (byte) desKey[i + BLK08];
				keyC[i] = (byte) desKey[i];
			}
		} else if (desKey.length == BLK24) { // TRIPLE key
			for (int i = 0; i < BLK08; i++) {
				keyA[i] = (byte) desKey[i];
				keyB[i] = (byte) desKey[i + BLK08];
				keyC[i] = (byte) desKey[i + BLK16];
			}
		}

		aEncryptedBlockA = encrypt(aText, keyA);
		aEncryptedBlockB = decrypt(aEncryptedBlockA, keyB);
		aEncryptedBlock = encrypt(aEncryptedBlockB, keyC);

		return aEncryptedBlock;
	}
}

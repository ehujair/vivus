package org.vivus.adb.md5;

import com.sun.jna.Library;
import com.sun.jna.Native;

public interface BSEncrypt extends Library {
	BSEncrypt INSTANCE = (BSEncrypt) Native.loadLibrary("BSEncrypt", BSEncrypt.class);
	
	boolean MD5String();
	/*
	Function boolean MD5String(ref string instr, long inlen, ref string outstr, long outlen) library "BSEncrypt.dll"
	Function boolean MD5File(ref string filename, ref string outstr, long outlen) library "BSEncrypt.dll"
	Function boolean GenUniqueID(ref string guid, boolean random) library "BSEncrypt.dll"
	Function boolean GetDiskSN(long index, ref string sn) library "BSEncrypt.dll"
	Function boolean GetNetCardIPAdd(long index, ref string ipstr) library "BSEncrypt.dll"
	Function boolean GetNetCardMacAdd(long index, ref string ipstr) library "BSEncrypt.dll"
	Function boolean GetNetCardName(long index, ref string ipstr) library "BSEncrypt.dll"
	Function int GetNetCardCount() library "BSEncrypt.dll"
	*/
}

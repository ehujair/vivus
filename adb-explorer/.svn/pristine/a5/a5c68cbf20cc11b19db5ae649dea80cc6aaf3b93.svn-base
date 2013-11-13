/*
		Author : Ahmet DEMIR
		Version 1.0
		Date : May 2011
		Description : Java ADB Explorer allows you to explore your Anroid Phone.
		under License GPL: http://www.gnu.org/copyleft/gpl.html
		-----------------------------------------------------------
		Copyright (C) 2011 Ahmet DEMIR

 		This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.

*/

package adbexplorer.util;

public class TestBeforeStart {
	
	adbexplorer.util.Log4jInit log4jInit = new adbexplorer.util.Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(TestBeforeStart.class);

	
	public TestBeforeStart() {}
	
	/**
	 * Test if adb run with correct privilege 
	 * @return -2 if no adb run, -1 if adb don't run as root, 0 if unidentified and 1 if ok
	 */
	public int adbRun() {
		int returnValue = 0;
		try {
			
			java.lang.Runtime rt = java.lang.Runtime.getRuntime();
			
			// Start two processes: ps aux | grep adb
			java.lang.Process p1 = rt.exec("ps aux");
			
			// grep will wait for input on stdin
			java.lang.Process p2 = rt.exec("grep adb");
			
			// Create and start Piper
			Piper pipe = new Piper(p1.getInputStream(), p2.getOutputStream());
			new Thread(pipe).start();
			
			// Wait for second process to finish
			p2.waitFor();
			
			// Show output of second process
			java.io.BufferedReader r = new java.io.BufferedReader(new java.io.InputStreamReader(p2.getInputStream()));
			String s = null;
			while ((s = r.readLine()) != null) {
				// Search the process
				if(s.contains("adb fork-server server")) {
					String [] col = s.split(" ");
					if(!col[0].equals("root")) return -1;
					else return 1;
				}
				else returnValue = -2;
			}
		}
		catch (java.io.IOException e) {
			log.error(e);
		}
		catch(java.lang.InterruptedException ie) {
			log.error(ie);
		}
		return returnValue;
	}
	
	/**
	 * @param errorCode the code returned by adbRun()
	 * @return A String text for the error returned by adbRun() function
	 */
	public String errorToString(int errorCode) {
		switch(errorCode) {
			case -2: 
				return ("Error : ADB don't run, please run it with root privilege.\nadb kill-server\nsudo adb start-server");
			case -1: 
				return ("Error : Run ADB Server with root privilege.\nadb kill-server\nsudo adb start-server");
			case 1: 
				return ("Success : ADB run with correct privileges");
			default:
				return ("Error : Unidentified");
		}
	}
	
	public int connectedDevices() {
		ADBCommand abdc = new ADBCommand();
		String[] listADBDevices = abdc.getDevices();
		if(listADBDevices == null) return 0;
		else return listADBDevices.length;
	}
	
	public boolean linkToUsrBin() {
		LocalCommand lc = new LocalCommand();
		String ret = lc.exec("adb version");
		if(ret.isEmpty()) return false;
		else return true;
	}
	
	public static void main(String[] args) {
		TestBeforeStart test = new TestBeforeStart();
		int errorCode = test.adbRun();
		System.out.println(test.errorToString(errorCode));
		System.out.println(test.connectedDevices());
		test.linkToUsrBin();	
	}
}

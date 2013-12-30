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

/**
 * @author Ralf Bensmann
 * http://blog.bensmann.com/?tag=java&page=2
 */

public 	class Piper implements java.lang.Runnable {
	
	private java.io.InputStream input;
	private java.io.OutputStream output;
	adbexplorer.util.Log4jInit log4jInit = new adbexplorer.util.Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(Piper.class);

	
	public Piper(java.io.InputStream input, java.io.OutputStream output) {
		this.input = input;
		this.output = output;
	}
	
	public void run() {
		try {
			// Create 512 bytes buffer
			byte[] b = new byte[512];
			int read = 1;
			// As long as data is read; -1 means EOF
			while (read > -1) {
				// Read bytes into buffer
				read = input.read(b, 0, b.length);
				if (read > -1) {
					// Write bytes to output
					output.write(b, 0, read);
				}
			}
		} catch (Exception e) {
			// Something happened while reading or writing streams; pipe is broken
			log.error(e);
		} finally {
			try {
				input.close();
			} catch (Exception e) {
				log.error(e);
			}
			try {
				output.close();
			} catch (Exception e) {
				log.error(e);
			}
		}
	}
}
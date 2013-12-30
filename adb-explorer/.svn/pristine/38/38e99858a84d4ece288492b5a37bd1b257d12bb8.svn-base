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

public class LocalCommand {
	adbexplorer.util.Log4jInit log4jInit = new adbexplorer.util.Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(LocalCommand.class);
	
	private Runtime runtime;
	
	/**
	 * Default constructor, just get the runtime
	 */
	public LocalCommand() {
		runtime = Runtime.getRuntime();
	}
	
	/**
	 * Execute a command in a shell
	 * @param command command to execute
	 * @return the return of the command
	 */
	public String exec(String command) {
		try {
			Process p = runtime.exec(command);
			java.io.DataInputStream in = new java.io.DataInputStream(p.getInputStream());
			
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			if(len > 0)
				return new String(buf,0,len);
		}
		catch (java.io.IOException e) {
			log.error(e);
		}
		
		return "";
	}

	public java.util.Vector<adbexplorer.util.FileType> showDirectoryContent(String directory) {
		
		java.util.Vector<adbexplorer.util.FileType> obj = null;
		
		try {
			Process p = runtime.exec("ls -la "+directory);
			//java.io.DataInputStream in = new java.io.DataInputStream(p.getInputStream());
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream())); 
			String line = "";
			
			while((line = in.readLine()) != null) {
				
				if(obj==null) 
					obj=new java.util.Vector<adbexplorer.util.FileType>();
				
				String [] ligneContent = line.split(" ");
				String name="", path ="", permissions="";
				int type=0;
				
				// If more than 5 columns
				if(ligneContent.length >= 5) { 
					//  
					if(!(ligneContent[ligneContent.length - 1]).equals(".") && !(ligneContent[ligneContent.length - 1]).equals("..")) {
						name = ligneContent[ligneContent.length - 1];
						permissions = ligneContent[0].substring(1, ligneContent[0].length());
						path = directory.equals("/") || directory.isEmpty()? "/" + name: directory+ "/" + name;
						path = path.replace("//", "/");
						
						// If is directory
						if(ligneContent[0].substring(0, 1).equals("d")) {
							type = 1;
						}
						// If is a symbolic link
						else if(ligneContent[0].substring(0, 1).equals("l")) {
							// TODO Check if the symlink link to a file or a directory
							type = 2;
							name = ligneContent[ligneContent.length - 3];
							path = directory.equals("/") || directory.isEmpty()? "/" + name+"/": directory+ "/" + name +"/";
							path = path.replace("//", "/");
						}
						// Other i.e. a file
						else {
							type = 0;
						}
						adbexplorer.util.FileType adb = new FileType(name, path, type, permissions);
						obj.add(adb);
					}
				}
			}
		}
		catch (java.io.IOException e) { 
			log.error(e);
		}
		return obj;
	}
	
	public static void main(String[] args) {
		LocalCommand cmd = new LocalCommand();
		String retour = cmd.exec("adb");
		System.out.println(retour);
		
		for(String s : retour.split(" "))
			System.out.println(s);
	}
	
}

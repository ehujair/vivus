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

public class ADBCommand {
	
	adbexplorer.util.Log4jInit log4jInit = new adbexplorer.util.Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ADBCommand.class);

	private Runtime runtime;
	private String device;
	
	/**
	 * Default constructor, just get the runtime
	 */
	public ADBCommand() {
		runtime = Runtime.getRuntime();
	}
	
	/**
	 * Constructor with device initialization
	 * @param device attached device
	 */
	public ADBCommand(String device) {
		this();
		this.device = device;
	}
	
	/**
	 * Set device
	 * @param device
	 */
	public void setDevice(String device) {
		this.device = device;
	}
	
	/**
	 * Execute a command in a shell
	 * @param command command to execute
	 * @return the return of the command
	 */
	public String exec(String command) {
		String retour="";
		try {
			Process p = runtime.exec("adb -s "+device+" shell "+command);
			log.debug("adb -s "+device+" shell "+command);
			
			java.io.BufferedReader standardIn = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream())); 
			java.io.BufferedReader errorIn = new java.io.BufferedReader(new java.io.InputStreamReader(p.getErrorStream())); 
			String line="";
			while((line=standardIn.readLine()) != null) {
				retour += line+"\n";
			}
			while((line=errorIn.readLine()) != null) {
				retour += line+"\n";
			}
		}
		catch (java.io.IOException e) { log.error(e); }
		
		return retour;
	}
	
	public String customExec(String cmd) {
		String retour="";
		try {
			cmd = "adb -s " + device + " " + cmd;
			Process p = runtime.exec(cmd);
			log.debug("adb -s "+device+" shell "+cmd);
			
			java.io.BufferedReader standardIn = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream())); 
			java.io.BufferedReader errorIn = new java.io.BufferedReader(new java.io.InputStreamReader(p.getErrorStream())); 
			String line="";
			while((line=standardIn.readLine()) != null) {
				retour += line+"\n";
			}
			while((line=errorIn.readLine()) != null) { // When success ADB return success state to std.err !
				retour += line+"\n";
			}
		}
		catch (java.io.IOException e) { log.error(e); }
		
		return retour;
	}
	
	public String copyToLocal(String src, String dest) {
		String cmd = "pull "+src + " " + dest+"";
		return "Copy to Local from "+src+" to "+dest+" "+customExec(cmd);
	}
	
	public String copyToRemote(String src, String dest) {
		String cmd = "push "+src + " " + dest;
		return "Copy to Remote from "+src+" to "+dest+" "+customExec(cmd);
	}
	
	public String rename(String oldName, String newName) {
		String cmd = "shell mv "+oldName + " " + newName;
		return "Rename "+oldName+" to "+newName+" "+customExec(cmd);
	}
	
	public String rm(String file) {
		String cmd = "shell rm "+file;
		return "Remove "+file+" "+customExec(cmd);
	}
	
	public String rmdir(String file) {
		String cmd = "shell rmdir "+file;
		return "Remove directory "+file+" "+customExec(cmd);
	}
	
	public java.util.Vector<adbexplorer.util.FileType> showDirectoryContent(String directory) {
		
		java.util.Vector<adbexplorer.util.FileType> obj = null;
		
		try {
			Process p = runtime.exec("adb -s "+device+" shell ls -l "+directory);
			//java.io.DataInputStream in = new java.io.DataInputStream(p.getInputStream());
			java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(p.getInputStream())); 
			String line = "";
			
			while((line = in.readLine()) != null) {
				if( obj == null ) obj = new java.util.Vector<adbexplorer.util.FileType>();
				String [] ligneContent = line.split(" ");
				String name="", path ="", permissions="";
				int type=0;
				if(ligneContent.length >= 5) { // If more than 5 columns
					
					// TODO check the permissions of the file and if the user can read the file
					// Must check the owner, the group and other perm (x x x)
					// adbexplorer.util.FileType file = new FileType(); int perm = file.permissionToInteger(permissions);
					
					
					name = ligneContent[ligneContent.length - 1];
					permissions = ligneContent[0].substring(1, ligneContent[0].length());
					path = directory.equals("/") || directory.isEmpty()? "/" + name: directory+ "/" + name;
					path = path.replace("//", "/");
					
					// If is directory
					if(ligneContent[0].substring(0, 1).equals("d")) {
						type = 2;
					}
					// If is a symbolic link
					else if(ligneContent[0].substring(0, 1).equals("l")) {
						// TODO Check if the symlink link to a file or a directory
						type = 3;
						name = ligneContent[ligneContent.length - 3];
						path = directory.equals("/") || directory.isEmpty()? "/" + name+"/": directory+ "/" + name +"/";
						path = path.replace("//", "/");
					}
					// Other i.e. a file
					else {
						type = 1;
					}
					//log.info(name + " " + path + " " + type + " " + permissions);
					adbexplorer.util.FileType adb = new FileType(name, path, type, permissions);
					obj.add(adb);
				}
			}
		}
		catch (java.io.IOException e) { 
			log.error(e);
		}
		return obj;
	}
	
	public String[] getDevices() {
		
		try {
			Process p = runtime.exec("adb devices");
			java.io.DataInputStream in = new java.io.DataInputStream(p.getInputStream());
			
			byte[] buf = new byte[1024];
			int len = in.read(buf);
			
			String[] ligne = new String(buf,0,len).split("\n");
			String[] retour = new String[ligne.length - 1]; // We don't take the first line "List of devices attached"
			
			for(int i=1; i<ligne.length; i++) {
				retour[i-1] = ligne[i].split("\t")[0];
			}
			
			return retour;
		}
		catch (java.io.IOException e) { log.error(e); }
		
		return null;
	}
	
	@Override
	public String toString() {
		return "ADBCommand [device=" + device + "]";
	}
	
	public static void main(String[] args) {
		ADBCommand adb = new ADBCommand("HT0BHRX12402");
		
		System.out.println("");
		System.out.println("$ " + adb.customExec("push /home/ademir/example.log /sdcard/example.log"));
		
		System.out.println("");
		java.util.Vector<adbexplorer.util.FileType> objList = adb.showDirectoryContent("/mnt");
		for(adbexplorer.util.FileType obj : objList)
			System.out.println("# " + obj.toFullString());
	}
	
	
	
	
}

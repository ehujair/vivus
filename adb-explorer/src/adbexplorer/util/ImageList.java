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

public class ImageList
{
	public javax.swing.DefaultListModel listModel = new javax.swing.DefaultListModel();
	public ADBCommand adb;
	public LocalCommand lc;
	
	private String workingDir;
	private String path = "/images/";
	
	Log4jInit log4jInit = new Log4jInit();
	private org.apache.log4j.Logger log = org.apache.log4j.Logger.getLogger(ImageList.class);

	private String images(int fileType) {
		String retour = "";
		switch(fileType) {
			case -3:
				retour = "dsymlink.gif";
				break;
			case -2:
				retour = "ddirectory.gif";
				break;
			case -1:
				retour = "dfile.gif";
				break;
			case 1:
				retour = "file.gif";
				break;
			case 2:
				retour = "directory.gif";
				break;
			case 3:
				retour = "symlink.gif";
				break;
			default:
					retour = "inconnu.gif";
		}
		return retour;
	}
	
	public ImageList(ADBCommand adb, String workingDir) {
		this.workingDir = workingDir;
		this.adb = adb;
		java.util.Vector<FileType> vft = adb.showDirectoryContent(this.workingDir);
		
		if(vft != null) {
			for(adbexplorer.util.FileType line : vft) {
				try {
					listModel.addElement(new ListImageText(javax.imageio.ImageIO.read(getClass().getResource(path + images(line.getType()))), line));
				}
				catch (java.io.IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	public ImageList(LocalCommand lc, String workingDir) {
		this.workingDir = workingDir;
		this.lc = lc;
		
		java.util.Vector<adbexplorer.util.FileType> vft = lc.showDirectoryContent(this.workingDir);
		if(vft != null) {
			for(adbexplorer.util.FileType line : vft) {
				try {
					listModel.addElement(new ListImageText(javax.imageio.ImageIO.read(getClass().getResource(path + images(line.getType()))), line));
				}
				catch (java.io.IOException e) {
					log.error(e);
				}
			}
		}
	}
	
	
}




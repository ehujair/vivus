package org.vivus.vfs;

import org.apache.commons.vfs2.FileObject;
import org.apache.commons.vfs2.FileSystemException;
import org.apache.commons.vfs2.FileSystemManager;
import org.apache.commons.vfs2.FileSystemOptions;
import org.apache.commons.vfs2.VFS;
import org.apache.commons.vfs2.auth.StaticUserAuthenticator;
import org.apache.commons.vfs2.impl.DefaultFileSystemConfigBuilder;
import org.apache.commons.vfs2.impl.StandardFileSystemManager;

public class VfsExample {
	public void read() {
		try {
			FileSystemManager fsManager = VFS.getManager();
			FileObject jarFile = fsManager
					.resolveFile("jar:/D:/m2/repository/org/apache/commons/commons-vfs2/2.0/commons-vfs2-2.0.jar");
			FileObject[] children = jarFile.getChildren();
			System.out.println("Children of " + jarFile.getName().getURI());
			for (int i = 0; i < children.length; i++) {
				System.out.println(children[i].getName().getBaseName());
			}
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	public void readByBase() {
		try {
			FileSystemManager fsManager = VFS.getManager();
			StandardFileSystemManager baseFsManager = new StandardFileSystemManager();
			baseFsManager.init();
			String userDir = System.getProperty("user.dir");
			System.out.println(userDir);
			FileObject jarFile = fsManager.resolveFile(baseFsManager
					.resolveFile("/D:/m2/repository/org/apache/commons/commons-vfs2/2.0/"),
					"jar:commons-vfs2-2.0.jar");
			FileObject[] children = jarFile.getChildren();
			System.out.println("Children of " + jarFile.getName().getURI());
			for (int i = 0; i < children.length; i++) {
				System.out.println(children[i].getName().getBaseName());
			}
			baseFsManager.close();
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	public void auth() {
		try {
			StaticUserAuthenticator auth = new StaticUserAuthenticator("username", "password", null);
			FileSystemOptions opts = new FileSystemOptions();
			DefaultFileSystemConfigBuilder.getInstance().setUserAuthenticator(opts, auth);
			FileObject fo = VFS.getManager().resolveFile("smb://host/anyshare/dir", opts);
		} catch (FileSystemException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new VfsExample().read();
	}
}

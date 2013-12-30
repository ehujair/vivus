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

public class ImageRenderer extends javax.swing.DefaultListCellRenderer
{
	private static final long serialVersionUID = 1L; // Auto-generated

	public java.awt.Component getListCellRendererComponent(javax.swing.JList list,
			Object value,
			int index,
			boolean isSelected,
			boolean cellHasFocus)
	{
		// for default cell renderer behavior
		java.awt.Component c = super.getListCellRendererComponent(list, value,
				index, isSelected, cellHasFocus);
		// set icon for cell image
		ListImageText l = (ListImageText)value;
		
    ((javax.swing.JLabel)c).setIcon(new javax.swing.ImageIcon(l.getImages()));
		((javax.swing.JLabel)c).setText(l.getFile().getName());
		return c;
	}
}

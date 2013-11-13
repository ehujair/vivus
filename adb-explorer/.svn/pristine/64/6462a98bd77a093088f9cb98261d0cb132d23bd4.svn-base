package adbexplorer.util;

public class MessageBoxes {
 
  public static void ShowMessage(String title, String text, int messageType)
  {
  	javax.swing.JOptionPane.showMessageDialog(null, text, title, messageType);
  }
  
  public static void ShowMessage(String text)
  {
    ShowMessage("Java ADB Explorer", text);
  }
 
  public static void ShowMessage(String title, String text)
  {
    ShowMessage(title, text, javax.swing.JOptionPane.NO_OPTION);
  }
 
  public static void ShowError(String title, String text)
  {
    ShowMessage(title, text, javax.swing.JOptionPane.ERROR_MESSAGE);
  }
 
  public static void ShowWarning(String title, String text)
  {
    ShowMessage(title, text, javax.swing.JOptionPane.WARNING_MESSAGE);
  }
 
  public static void ShowInfo(String title, String text)
  {
    ShowMessage(title, text, javax.swing.JOptionPane.INFORMATION_MESSAGE);
  }
}

import java.io.File;
import java.io.IOException;

import com.android.ddmlib.AdbCommandRejectedException;
import com.android.ddmlib.AndroidDebugBridge;
import com.android.ddmlib.IDevice;
import com.android.ddmlib.RawImage;
import com.android.ddmlib.AndroidDebugBridge.IDeviceChangeListener;
import com.android.ddmlib.TimeoutException;

import java.awt.Dimension;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;


public class Screenshot implements IDeviceChangeListener {
	
	private static boolean mConnected = false;
	
	public static void main(String[] args) {
		try {
			// Create the Android Debug Bridge
			AndroidDebugBridge.init(false);
			AndroidDebugBridge adb = AndroidDebugBridge.createBridge();
			
			// Add an instance of this class as a device change listener
			// so we know when a device is connected
			Screenshot ss = new Screenshot();
			AndroidDebugBridge.addDeviceChangeListener(ss);

			System.out.print("Waiting for device connection...\n");

			// wait until something happens
			synchronized (ss) {
				ss.wait();
			}
			
			// Get the list of connected devices
			IDevice[] devices = adb.getDevices();
			if(devices.length > 0) {
				// Take a screenshot of the first connected device
				getScreenshots(ss, devices[0]);
			}
			
			System.out.print("Done\n");
			
		} catch (InterruptedException e) {
			
		} finally {
			// Close the Android Debug Bridge
			AndroidDebugBridge.terminate();
		}
	}
	
	private static void getScreenshots(Screenshot ss, IDevice device) {
		// Get the device's serial #, which  we'll use for the directory
		String thisDevice = device.getSerialNumber();
		String dir = String.format("%s", thisDevice);
		
		// Set up the directory
		if (new File(dir).exists()){
			System.out.printf("Writing files to an existing directory %s\n", dir);
		} else if (new File(dir).mkdir() == false) {
			System.out.printf("Couldn't create directory %s\n", dir);
			return;
		} else {
			System.out.printf("Writing files to directory %s\n", dir);
		}		
		
		if (mConnected) {			
			RawImage raw = null;
			try {
				raw = device.getScreenshot();
			} catch (IOException e) {
				System.out.printf("Error getting image: %s\n", e.getMessage());
			} catch (TimeoutException e) {
				e.printStackTrace();
			} catch (AdbCommandRejectedException e) {
				e.printStackTrace();
			}
			
			
			Boolean landscape = false; 
			BufferedImage image = null;
	        
			Dimension size = new Dimension();
			int width2 = landscape ? raw.height : raw.width;
			int height2 = landscape ? raw.width : raw.height;
	        if (image == null) {
				image = new BufferedImage(width2, height2,
						BufferedImage.TYPE_INT_RGB);
				size.setSize(image.getWidth(), image.getHeight());
			} else {
				if (image.getHeight() != height2 || image.getWidth() != width2) {
					image = new BufferedImage(width2, height2,
							BufferedImage.TYPE_INT_RGB);
					size.setSize(image.getWidth(), image.getHeight());
				}
			}
			int index = 0;
			int indexInc = raw.bpp >> 3;
			for (int y = 0; y < raw.height; y++) {
				for (int x = 0; x < raw.width; x++, index += indexInc) {
					int value = raw.getARGB(index);
					if (landscape)
						image.setRGB(y, raw.width - x - 1, value);
					else
						image.setRGB(x, y, value);
				}
			}
			
			String filename = String.format("%s%soutput.png", dir, File.separator);
			
	        try {
				ImageIO.write(image, "png", new File(filename));
				System.out.printf("Saved picture as %s\n", filename);
			} catch (IOException e) {
				System.out.println("Failed to find png writer");
			}
			
		}
	}

	@Override
	public void deviceConnected(IDevice device) {
		System.out.printf("Device %s connected\n", device.getSerialNumber());
		mConnected = true;

		synchronized (this) {
			notify();
		}
	}

	@Override
	public void deviceDisconnected(IDevice device) {
		System.out.printf("Device  %s disconnected\n", device.getSerialNumber());
		mConnected = false;
		
		synchronized (this) {
			notify();
		}
	}

	@Override
	public void deviceChanged(IDevice device, int changeMask) {
		// don't care
	}
}
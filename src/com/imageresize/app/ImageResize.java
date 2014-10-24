package com.imageresize.app;

import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URI;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;
import java.nio.file.WatchEvent.Kind;
import java.nio.file.WatchEvent.Modifier;
import java.util.Iterator;

import javax.activation.MimetypesFileTypeMap;
import javax.imageio.ImageIO;

public class ImageResize {
	
	
	
/*
 * resizeImageByPixel method resizes the image by using height and width in pixel passed to it.
 * It takes arguments like source path, destination path, height, width and resize type(fast and smooth)
 * 
 */
	public void resizeImageByPixel(String sourcePath, String descPath,
		
		int height, int width, int resizeType) throws IOException {
		try{
			File inputfiles = new File(sourcePath);
			if(!inputfiles.exists()){
				throw new IOException();
			}
			int imageCounter = 0;
			ImageResizeApp.textAreaProcess.append("Source Folder: " + sourcePath + "\n");
			File[] fileArrayFiles = inputfiles.listFiles();
			for (File file : fileArrayFiles) {

				ImageResizeApp.textAreaProcess.append("Resizing image: " + file.getName() + "...");

				if(file.isDirectory())
					continue;
				
				BufferedImage toResizeImg = ImageIO.read(new FileInputStream(
						file.getAbsoluteFile()));

				if(height > toResizeImg.getHeight()){
					throw new IllegalArgumentException("height");
				}
				
				if(width > toResizeImg.getWidth()){
					throw new IllegalArgumentException("width");
				}
				
				int resizeHeight = height;
				int resizeWidth = width;
				
				Image tempImage = toResizeImg.getScaledInstance(resizeWidth,
						resizeHeight, Image.SCALE_FAST);

				BufferedImage resizedImg = new BufferedImage(resizeWidth,
						resizeHeight, toResizeImg.getType());

				Graphics2D g = resizedImg.createGraphics();

				g.drawImage(tempImage, 0, 0, null);

				g.dispose();

				File outputFile = new File(descPath);
				if(!outputFile.exists()){
					outputFile.mkdir();
				}
				
				
				ImageIO.write(resizedImg, "JPG",
						new FileOutputStream(outputFile  + File.separator + file.getName()));	
				
				ImageResizeApp.textAreaProcess.append("Successfully Resized" + "\n");
				imageCounter++;
			}
			ImageResizeApp.textAreaProcess.append(imageCounter + " images Resized");
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
		catch (IllegalArgumentException e) {
			e.printStackTrace();
			throw e;
		}

	}

/*
 * resizeImageByPercentage method resizes the image by percentage values passed to it.
 * It takes arguments like source path, destination path, percentage and resize type
 * 
 */
	public void resizeImageByPercentage(String sourcePath, String descPath,
			double percentage, int resizeType) throws IOException {
		
		try{
			File inputfiles = new File(sourcePath);
			if(!inputfiles.exists()){
				throw new IOException();
			}
			ImageResizeApp.textAreaProcess.append("Source Folder: " + sourcePath + "\n");
			percentage = percentage/100;
			File[] fileArrayFiles = inputfiles.listFiles();
			int imageCounter = 0;
			for (File file : fileArrayFiles) {
				
				if(file.isDirectory())
					continue;
				
				ImageResizeApp.textAreaProcess.append("Resizing image: " + file.getName() + "...");
				
				BufferedImage toResizeImg = ImageIO.read(new FileInputStream(
						file.getAbsoluteFile()));

				int resizeHeight = (int) (toResizeImg.getHeight() * percentage);
				int resizeWidth = (int) (toResizeImg.getWidth() * percentage);

				Image tempImage = toResizeImg.getScaledInstance(resizeWidth,
						resizeHeight, Image.SCALE_FAST);

				BufferedImage resizedImg = new BufferedImage(resizeWidth,
						resizeHeight, toResizeImg.getType());

				Graphics2D g = resizedImg.createGraphics();

				g.drawImage(tempImage, 0, 0, null);

				g.dispose();

				File outputFile = new File(descPath);
				if(!outputFile.exists()){
					outputFile.mkdir();
				}
				
				
				ImageIO.write(resizedImg, "JPG",
						new FileOutputStream(outputFile  + File.separator + file.getName()));	
				
				ImageResizeApp.textAreaProcess.append("Successfully Resized" + "\n");
				imageCounter++;
			}
			ImageResizeApp.textAreaProcess.append(imageCounter + " images Resized");
		
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}


}

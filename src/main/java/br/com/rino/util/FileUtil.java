package br.com.rino.util;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Date;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.lang.RandomStringUtils;

public class FileUtil {
	
	public static byte[] convertBase64ToByte(String base64String) throws IOException {
		byte[] decodedBytes = Base64.decodeBase64(base64String);

		return decodedBytes;
	}

	
	public static void convertBase64ToImage(String base64ImageString, String path) throws IOException {
		//String base64Image = base64ImageString.split(",")[1];

		byte[] decodedBytes = Base64.decodeBase64(base64ImageString);
		
		BufferedImage bfi = ImageIO.read(new ByteArrayInputStream(decodedBytes));
		File outputfile = new File(path);
		ImageIO.write(bfi, "png", outputfile);
		bfi.flush();
	}
	
	public static String convertByteToBase64(byte[] file) {
		String encodedBase64 = null;
		try {
			encodedBase64 = new String(Base64.encodeBase64URLSafeString(file));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return encodedBase64;
	}

	public static String convertImageToBase64(String path) {
		File originalFile = new File(path);
		String encodedBase64 = null;
		try {
			FileInputStream fileInputStreamReader = new FileInputStream(originalFile);
			byte[] bytes = new byte[(int) originalFile.length()];
			fileInputStreamReader.read(bytes);
			encodedBase64 = new String(Base64.encodeBase64URLSafeString(bytes));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return encodedBase64;
	}

	public static void copyFile(String fileName, InputStream in) {
		try {

			File directory = new File("C:\\rino\\uploads\\images");
			if (!directory.isDirectory()) {
				directory.mkdirs();
			}

			// write the inputStream to a FileOutputStream
			OutputStream out = new FileOutputStream(new File(directory.getPath() + "\\" + fileName));

			int read = 0;
			byte[] bytes = new byte[1024];

			while ((read = in.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			in.close();
			out.flush();
			out.close();

			System.out.println("New file created!");
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	public static String generateUniqueFileName() {
		String filename = "";
		long millis = System.currentTimeMillis();
		String datetime = new Date().toGMTString();
		datetime = datetime.replace(" ", "");
		datetime = datetime.replace(":", "");
		String rndchars = RandomStringUtils.randomAlphanumeric(16);
		filename = rndchars + "_" + datetime + "_" + millis;
		return filename;
	}
}

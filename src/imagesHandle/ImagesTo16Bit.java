package imagesHandle;

import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

import javax.imageio.ImageIO;

import org.apache.tools.ant.types.resources.Files;

public class ImagesTo16Bit {
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(
				new FileInputStream("/home/zipe/tmp/2.jpg")));
		StringBuffer sb = new StringBuffer();
		String tmp = "";
		while ((tmp = br.readLine()) != null) {
			sb.append(tmp);
		}
	}
}

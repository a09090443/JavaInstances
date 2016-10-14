package imagesHandle;

import java.awt.image.BufferedImage;
import java.awt.image.ColorConvertOp;
import java.io.File;

import javax.imageio.ImageIO;

public class TestCmykToRgb {
	public static void main(String[] args) throws Exception {
		BufferedImage cmykImage = ImageIO.read(new File(
				"/home/zipe/文件/image/21_上海小館蔥燒鯽魚.jpg"));

		BufferedImage rgbImage = new BufferedImage(cmykImage.getWidth(),
				cmykImage.getHeight(), BufferedImage.TYPE_INT_RGB);

		ColorConvertOp op = new ColorConvertOp(null);
		op.filter(cmykImage, rgbImage);

		ImageIO.write(rgbImage, "JPEG", new File("/home/zipe/tmp/teeeee.jpg"));

	}
}

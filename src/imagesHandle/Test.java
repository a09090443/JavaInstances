package imagesHandle;

import com.sun.image.codec.jpeg.JPEGImageEncoder;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import javax.imageio.metadata.IIOMetadata;
import javax.imageio.metadata.IIOMetadataNode;
import java.util.*;
import java.io.*;
import java.awt.image.*;
import java.awt.*;
import java.awt.color.ColorSpace;

import org.w3c.dom.NodeList;

public class Test {

	public static void main(String[] args) throws Exception {

		BufferedImage i1 = readImage(new File("/home/zipe/文件/image/21_上海小館蔥燒鯽魚.jpg"));

		BufferedOutputStream out = new BufferedOutputStream(
				new FileOutputStream(new File("/home/zipe/tmp/21_上海小館蔥燒鯽魚.jpg")));
		JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
		JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(i1);
		param.setQuality(1, false);
		encoder.setJPEGEncodeParam(param);
		encoder.encode(i1);

	}

	public Test() {
	}

	// extract metadata
	public static BufferedImage readImage(File file) throws IOException {

		// Get an ImageReader.
		ImageInputStream input = ImageIO.createImageInputStream(file);
		Iterator readers = ImageIO.getImageReaders(input);
		if (readers == null || !readers.hasNext()) {
			throw new RuntimeException("No ImageReaders found");
		}
		//
		ImageReader reader = (ImageReader) readers.next();
		reader.setInput(input);
		String format = reader.getFormatName();

		if ("JPEG".equalsIgnoreCase(format) || "JPG".equalsIgnoreCase(format)) {
			IIOMetadata metadata = reader.getImageMetadata(0);
			String metadataFormat = metadata.getNativeMetadataFormatName();
			IIOMetadataNode iioNode = (IIOMetadataNode) metadata
					.getAsTree(metadataFormat);

			NodeList children = iioNode.getElementsByTagName("app14Adobe");
			if (children.getLength() > 0) {
				iioNode = (IIOMetadataNode) children.item(0);
				int transform = Integer.parseInt(iioNode
						.getAttribute("transform"));
				Raster raster = reader.readRaster(0,
						reader.getDefaultReadParam());

				if (input != null) {
					input.close();
				}
				reader.dispose();

				return createJPEG4(raster, transform);
			}
		}
		throw new RuntimeException("No ImageReaders found");
	}

	/**
	 * Java's ImageIO can't process 4-component images
	 * <p/>
	 * and Java2D can't apply AffineTransformOp either,
	 * <p/>
	 * so convert raster data to RGB.
	 * <p/>
	 * Technique due to MArk Stephens.
	 * <p/>
	 * Free for any use.
	 */
	private static BufferedImage createJPEG4(Raster raster, int xform) {
		int w = raster.getWidth();
		int h = raster.getHeight();
		byte[] rgb = new byte[w * h * 3];

		// if (Adobe_APP14 and transform==2) then YCCK else CMYK
		if (xform == 2) { // YCCK -- Adobe

			float[] Y = raster.getSamples(0, 0, w, h, 0, (float[]) null);
			float[] Cb = raster.getSamples(0, 0, w, h, 1, (float[]) null);
			float[] Cr = raster.getSamples(0, 0, w, h, 2, (float[]) null);
			float[] K = raster.getSamples(0, 0, w, h, 3, (float[]) null);

			for (int i = 0, imax = Y.length, base = 0; i < imax; i++, base += 3) {
				float k = 220 - K[i], y = 255 - Y[i], cb = 255 - Cb[i], cr = 255 - Cr[i];

				double val = y + 1.402 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base] = val < 0.0 ? (byte) 0 : val > 255.0 ? (byte) 0xff
						: (byte) (val + 0.5);

				val = y - 0.34414 * (cb - 128) - 0.71414 * (cr - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 1] = val < 0.0 ? (byte) 0
						: val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);

				val = y + 1.772 * (cb - 128) - k;
				val = (val - 128) * .65f + 128;
				rgb[base + 2] = val < 0.0 ? (byte) 0
						: val > 255.0 ? (byte) 0xff : (byte) (val + 0.5);
			}

		} else {
			// assert xform==0: xform;
			// CMYK

			int[] C = raster.getSamples(0, 0, w, h, 0, (int[]) null);
			int[] M = raster.getSamples(0, 0, w, h, 1, (int[]) null);
			int[] Y = raster.getSamples(0, 0, w, h, 2, (int[]) null);
			int[] K = raster.getSamples(0, 0, w, h, 3, (int[]) null);

			for (int i = 0, imax = C.length, base = 0; i < imax; i++, base += 3) {
				int c = 255 - C[i];
				int m = 255 - M[i];
				int y = 255 - Y[i];
				int k = 255 - K[i];
				float kk = k / 255f;

				rgb[base] = (byte) (255 - Math.min(255f, c * kk + k));
				rgb[base + 1] = (byte) (255 - Math.min(255f, m * kk + k));
				rgb[base + 2] = (byte) (255 - Math.min(255f, y * kk + k));
			}
		}

		// from other image types we know InterleavedRaster's can be
		// manipulated by AffineTransformOp, so create one of
		// those.
		raster = Raster.createInterleavedRaster(new DataBufferByte(rgb,
				rgb.length), w, h, w * 3, 3, new int[] { 0, 1, 2 }, null);

		ColorSpace cs = ColorSpace.getInstance(ColorSpace.CS_sRGB);
		ColorModel cm = new ComponentColorModel(cs, false, true,
				Transparency.OPAQUE, DataBuffer.TYPE_BYTE);
		return new BufferedImage(cm, (WritableRaster) raster, true, null);
	}

}

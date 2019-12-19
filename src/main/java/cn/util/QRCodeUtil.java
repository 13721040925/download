package cn.util;

public class QRCodeUtil {
	/**
	 * 生成二维码（QR类型）
	 * 
	 * @param content
	 *            二维码文本内容
	 * @param file
	 *            生成的路径（文件路径）
	 * @return 返回文件路径加文件全名称 并执行
	 */
	// public static String createErweima(String content, String file) {
	// try {
	// if (null == content || content.equals("")) {
	// System.out.println("CTHUtil.class-->getQRCode()-->content is null");
	// }
	// int width = 300;
	// int height = 300;
	// HashMap<EncodeHintType, String> hints = new HashMap<>();
	// hints.put(EncodeHintType.CHARACTER_SET, "utf-8");
	// // 二维码的格式是BarcodeFormat.QR_CODE qr类型二维码 BarcodeFormat.DATA_MATRIX dm码
	// BitMatrix qrc = new MultiFormatWriter().encode(content,
	// BarcodeFormat.QR_CODE, width, height, hints);
	// String name = createRandomFileName();
	// File out = new File(file + "/" + name);// 文件保存 位置
	// // 生成二维码图片
	// WriteBitMatricToFile.writeBitMatricToFile(qrc, format, out);
	// WriteBitMatricToFile.parseCode(out);
	// System.out.println(out.getAbsolutePath());
	// return name;// out.getAbsolutePath()
	// } catch (Exception e) {
	// System.out.println(e.getMessage());
	// return null;
	// }
	// }

	/**
     * 生成条形码 如果没效果就用 createtiaoxm
     * 
     * @param content
     *            二维码文本内容
     * @param file
     *            生成的路径（文件路径）
     * @return 返回文件路径加文件全名称
     */
	// public static String createtiaoxma(String content, String file) {
	// try {
	// if (null == content || content.equals("")) {
	// System.out.println("CodeUtil.class-->getQRCode()-->content is null");
	// return null;
	// }
	// int len = content.trim().length();
	// if (len != 12 && len != 13) {
	// System.out.println("CodeUtil.class-->getQRCode()-->content length fail (12 or
	// 13,fact " + len + ")");
	// return null;
	// }
	// }
	// }

}

package xr.sourcecodethreaddemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @ClassName: CodeStreamUtil
 * @Description:工具类 将网页请求返回的流对象转化为String
 * @author iamxiarui@foxmail.com
 * @date 2016年3月24日 下午5:14:21
 * 
 */
public class CodeStreamUtil {

	public static String getCodeStream(InputStream in) {

		String code = "";

		try {
			// 字节流对象
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int length = 0;
			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
				out.flush();
			}
			// 将流对象转换为String
			code = out.toString();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// 返回结果
		return code;
	}

}

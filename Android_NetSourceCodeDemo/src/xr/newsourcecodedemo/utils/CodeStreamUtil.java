package xr.newsourcecodedemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class CodeStreamUtil {

	public static String getCodeStream(InputStream in) {
		String code = "";

		try {

			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int length = 0;
			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
				out.flush();
			}
			code = out.toString();
			out.close();

		} catch (IOException e) {
			System.out.println(e.toString());
		}

		return code;
	}

}

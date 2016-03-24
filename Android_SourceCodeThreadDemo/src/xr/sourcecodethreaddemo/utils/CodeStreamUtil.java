package xr.sourcecodethreaddemo.utils;

import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * @ClassName: CodeStreamUtil
 * @Description:������ ����ҳ���󷵻ص�������ת��ΪString
 * @author iamxiarui@foxmail.com
 * @date 2016��3��24�� ����5:14:21
 * 
 */
public class CodeStreamUtil {

	public static String getCodeStream(InputStream in) {

		String code = "";

		try {
			// �ֽ�������
			ByteArrayOutputStream out = new ByteArrayOutputStream();

			byte[] b = new byte[1024];
			int length = 0;
			while ((length = in.read(b)) != -1) {
				out.write(b, 0, length);
				out.flush();
			}
			// ��������ת��ΪString
			code = out.toString();
			out.close();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// ���ؽ��
		return code;
	}

}

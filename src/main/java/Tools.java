
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Formatter;

public class Tools
{
	/**
	 * 用{@link MessageDigest}获取数据摘要。
	 * @param algorithm 获取数据摘要的算法，例如MD5、SHA等。
	 * @param input 需要计算摘要的内容。
	 * @param charset 对输入内容进行编码的字符集，例如UTF-8、GBK等。
	 * @return
	 */
	public static String getDigest(String algorithm,String input,String charset)
	{
		try
		{
			MessageDigest digest=MessageDigest.getInstance(algorithm);
			byte[] bs=digest.digest(input.getBytes(charset));
			//新建一个Formatter，用于将字节格式化为两位十六进制字符串
			Formatter formatter=new Formatter();
			for(byte b:bs)
				formatter.format("%02x",b);//%02x，0表示高位补0，2表示两位字符，x表示十六进制，byte会转换成Byte
			String output=formatter.toString();
			formatter.close();
			return output;
		}
		catch (UnsupportedEncodingException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NoSuchAlgorithmException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}

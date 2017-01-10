/**
 * 可用于多个线程同时获取一个等差递增序列
 * @author Richard_Ma
 */
public class SerialSyn
{
//	public final long min;
//	public final long max;
//	public final boolean cycle;
	public final long start;//起始值，必须大于或等于0
	public final long step;//步长，必须大于0
	private long serial;//当前序列值
	private String format;//用于将数字格式化
	public final int width;//返回的字符串长度，不足时前位补0
	/**
	 * 获取固定长度的序列，长度只对字符串返回类型有效。
	 * 因为内部序列不循环，所以只对预期的序列最大值小于 Long.MAX_VALUE 有效，超过时会抛出异常。
	 * @param start 起始值，必须大于或等于0
	 * @param step 步长，必须大于0
	 * @param width 返回的字符串长度，不足时前位补0
	 */
	public SerialSyn(long start,long step,int width)
	{
		if(start<0||step<=0) throw new IllegalArgumentException("start或setp过小");
		this.start=start;
		this.step=step;
		this.serial=start-step;//第一次调用getNext方法时可以从start开始
		this.width=width;
		this.format="%0"+width+"d";//0表示前位补0，d表示格式化为十进制数
	}
	/**
	 * 将数字格式化为{@link width}宽度的字符串，长度不够时前位补0，第一次调用时返回值的数字值为{@link start}。
	 * @return
	 */
	public String getNextString()
	{
		String serialStr=String.format(format,getNoCycle());
		if(serialStr.length()>width) serialStr=serialStr.substring(serialStr.length()-width);
		return serialStr;
	}
	/**
	 * 获取当前序列值，第一次调用getNext*方法前此方法返回值为start-step。
	 * @return
	 */
	private synchronized long getCurrent()
	{
		return serial;
	}
	/**
	 * 获取下一个序列值
	 * @return
	 */
	private long getNext()
	{
		return getNoCycle();
	}
	/**
	 * 为了减少同步代码块，将自增操作放在一个单独方法中。
	 * @return
	 */
	private synchronized long getNoCycle()
	{
		return serial+=step;
	}
}

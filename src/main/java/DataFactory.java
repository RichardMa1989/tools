import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

public class DataFactory
{
	public static void main(String[] args)
	{
		int n=50;
		List<String> names=produceNames(n);
		List<String> phones=producePhones(n);
		List<String> mails=produceMail(n);
		List<String> births=produceDates(n,null,null,"yyyyMMdd");
		for(int i=0;i<n;i++)
			System.out.format("%3d%13s%20s%10s%5s\n",i,phones.get(i),mails.get(i),births.get(i),names.get(i));
	}
	/**
	 * 产生中文姓名
	 * @param n 返回的姓名个数
	 * @return
	 */
	public static List<String> produceNames(int n)
	{
		String[] familyNames="赵钱孙李周吴郑王马".split("");//姓氏
		String[] firstNames="建成海涛立伟润发德华朝明天宇巧雨丽".split("");//名字
		Random random=new Random();
		List<String> names=new ArrayList<String>();
		for(int i=0;i<n;i++)
		{
			String familyName=familyNames[random.nextInt(familyNames.length)];
			String firstName=firstNames[random.nextInt(firstNames.length)];
			if(random.nextBoolean()) //如果名字有两个字，再加一个字
			firstName+=firstNames[random.nextInt(firstNames.length)];
			String name=familyName+firstName;
			names.add(name);
		}
		return names;
	}
	/**
	 * 产生特定时间段内的多个时间，开始时间不能大于或等于结束时间
	 * @param n 返回的时间个数
	 * @param start 开始时间，如果为null则为格林尼治时间 19700101 00:00:00
	 * @param end 结束时间，如果为null则为当前时间
	 * @param pattern 用指定的模式将返回的时间格式化为{@link String}类型（如 yyyyMMdd)，
	 *          如果为null则返回的时间为 {@link Date}类型
	 * @return
	 */
	public static <T>List<T> produceDates(int n,Date start,Date end,String pattern)
	{
		long startTime=start==null?new Date(0).getTime():start.getTime();
		long endTime=end==null?new Date().getTime():end.getTime();
		if(startTime>=endTime) throw new IllegalArgumentException("开始时间不能大于或等于结束时间");
		List<Object> dates=new ArrayList<Object>();
		Random random=new Random();
		if(pattern==null) for(int i=0;i<n;i++)
		{
			Date date=new Date(startTime+(long)(random.nextFloat()*(endTime-startTime)));//注意如何产生指定范围内的long值
			dates.add(date);
		}
		else
		{
			SimpleDateFormat format=new SimpleDateFormat(pattern);
			for(int i=0;i<n;i++)
			{
				Date date=new Date(startTime+(long)(random.nextFloat()*(endTime-startTime)));
				dates.add(format.format(date));
			}
		}
		return (List<T>)dates;
	}
	/**
	 * 产生手机号码
	 * @param n 返回的手机号码个数
	 * @return
	 */
	public static List<String> producePhones(int n)
	{
		String[] starts="13 15 18".split(" "); //电话号码前两位
		List<String> phones=new ArrayList<String>();
		Random random=new Random();
		for(int i=0;i<n;i++)
		{
			String start=starts[random.nextInt(starts.length)];
			String end=String.format("%09d",random.nextInt(1000000000));//电话号码后9位
			phones.add(start+end);
		}
		return phones;
	}
	/**
	 * 产生邮箱
	 * @param n 返回的邮箱个数
	 * @return
	 */
	public static List<String> produceMail(int n)
	{
		String[] domains=("qq.com 126.com 163.com yeah.net bankcomm.com tangdi.net yahoo.net gmail.com xmu.edu.cn sina.net").split(" ");
		String[] prefixs="abcdefghijklmnopqrstuvwxyz".split("");
		Random random=new Random();
		List<String> mails=new ArrayList<String>();
		for(int i=0;i<n;i++)
		{
			StringBuffer prefix=new StringBuffer();
			prefix.append(prefixs[random.nextInt(prefixs.length)]);
			prefix.append(prefixs[random.nextInt(prefixs.length)]);
			prefix.append(prefixs[random.nextInt(prefixs.length)]);
			for(int j=0;j<random.nextInt(4);j++)
				prefix.append(prefixs[random.nextInt(prefixs.length)]);
			mails.add(prefix+"@"+domains[random.nextInt(domains.length)]);
		}
		return mails;
	}
}

public class StringTools
{
	public static void dbToJava(String[] dbNames)
	{
		for(String name:dbNames)
		{
			name=name.toLowerCase();
			name=name.replace(" ","");
			String[] strings=name.split("_");
			name="";
			for(String s:strings)
			{
				name+=s.substring(0,1).toUpperCase()+s.substring(1);
			}
			name=name.substring(0,1).toLowerCase()+name.substring(1);
			System.err.println(name);
		}
	}
	public static boolean containWords(String s)
	{
		String pattern="\\w+";
		return s.matches(pattern);
	}
	public static void main(String[] args)
	{
		System.out.println(containWords("asdjfi2345"));
	}
}

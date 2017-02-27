import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;
import java.util.HashMap;
import java.util.Map;

public class Test
{
	public static void main(String[] args) throws IntrospectionException
	{
		Map<String,Object> map=new HashMap<>();
		map.put("a","a");
		map.put("b","b");
		System.out.println(map);
	}
}
class C 
{
	private int i;
	public int getI()
	{
		return i;
	}
	public void setI(int i)
	{
		this.i=i;
	}
	private int getN()
	{
		return 1;
	}
}
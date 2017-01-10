import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.MethodDescriptor;
import java.beans.PropertyDescriptor;

public class Test
{
	public static void main(String[] args) throws IntrospectionException
	{
		BeanInfo beanInfo=Introspector.getBeanInfo(C.class,Object.class);
		for(PropertyDescriptor p:beanInfo.getPropertyDescriptors())
			System.out.println(p);
		for(MethodDescriptor m:beanInfo.getMethodDescriptors())
			System.out.println(m);
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
import java.beans.BeanInfo;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ClassParser
{
	/**
	 * 将符合JavaBean规范的对象中的属性转换成Map
	 * @param o
	 * @return
	 */
	public static Map<String,Object> parseBeanToMap(Object o)
	{
		Map<String,Object> map=new HashMap<>();
		try
		{
			//一般不需要Object中的属性，比如getClass方法
			BeanInfo beanInfo=Introspector.getBeanInfo(o.getClass(),Object.class);
			PropertyDescriptor[] properties=beanInfo.getPropertyDescriptors();
			for(PropertyDescriptor property:properties)
			{
				String name=property.getName();
				Method readMethod=property.getReadMethod();
				//如果读方法是public则可以得到，否则是null
				if(readMethod!=null)
				{
					readMethod.setAccessible(true);
					map.put(name,readMethod.invoke(o));
				}
			}
		}
		catch (Exception e)
		{
			throw new RuntimeException(e);
		}
		return map;
	}
	/**
	 * 将该对象中所有public类型域转换成map，包括父类
	 * @param o
	 * @return
	 */
	public static Map<String,Object> parseFields(Object o)
	{
		Map<String,Object> map=new HashMap<>();
		Class<?> c=o.getClass();
		try
		{
			for(Field f:c.getFields())
				if(!Modifier.isStatic(f.getModifiers())&&!Modifier.isFinal(f.getModifiers()))
				{
					f.setAccessible(true);
					map.put(f.getName(),f.get(o));
				}
		}
		catch (IllegalArgumentException|IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		return map;
	}
	/**
	 * 将该对象中所有声明的域转换成map，包括private等类型，不包括父类
	 * @param o
	 * @return
	 */
	public static Map<String,Object> parseDeclaredFields(Object o)
	{
		Map<String,Object> map=new HashMap<>();
		Class<?> c=o.getClass();
		try
		{
			for(Field f:c.getDeclaredFields())
				if(!Modifier.isStatic(f.getModifiers())&&!Modifier.isFinal(f.getModifiers()))
				{
					f.setAccessible(true);
					map.put(f.getName(),f.get(o));
				}
		}
		catch (IllegalArgumentException|IllegalAccessException e)
		{
			throw new RuntimeException(e);
		}
		return map;
	}
	/**
	 * 将该对象中所有声明的域转换成map，包括private等类型，包括父类
	 * @param o
	 * @return
	 */
	public static Map<String,Object> parseDeclaredFieldsWithSuper(Object o)
	{
		Map<String,Object> map=new HashMap<>();
		Class<?> c=o.getClass();
		while (c!=null&&c!=Object.class)
		{
			try
			{
				for(Field f:c.getDeclaredFields())
					if(!Modifier.isStatic(f.getModifiers())&&!Modifier.isFinal(f.getModifiers()))
					{
						f.setAccessible(true);
						map.put(f.getName(),f.get(o));
					}
				c=c.getSuperclass();
			}
			catch (IllegalArgumentException|IllegalAccessException e)
			{
				throw new RuntimeException(e);
			}
		}
		return map;
	}
}

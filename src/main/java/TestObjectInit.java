public class TestObjectInit
{
	public static void main(String[] args)
	{
		new Son();
	}
}
class A
{
	private static B b=new B("AB");
	public A(String s)
	{
		System.out.println(s);
	}
}
class B
{
	public B(String s)
	{
		System.out.println(s);
	}
}
abstract class Father
{
	private A a=new A("FatherA");
	private static B b=new B("FatherB");
	public Father()
	{
		System.out.println("Father()");
	}
}
class Son extends Father
{
	public Son()
	{
		System.out.println("Son()");
	}
	private A a=new A("SonA");
	private static B b=new B("SonB");
}

import java.util.HashMap;
import java.util.Map;

public class Tree
{
	private Map<Object,Object> datas=new HashMap<Object,Object>();
	public Tree(Object bean,String...fieldNames)
	{}
	public <T>T getChild(Object...childChain)
	{
		Map<Object,Object> parent=datas;
		for(Object child:childChain)
		{
			if(parent.get(child) instanceof Map<?,?>) parent=(Map<Object,Object>)parent.get(child);
		}
		return (T)parent;
	}
}

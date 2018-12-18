package children;


import java.util.Stack;


/** 
 * Custom class representing a hashtable in the form of an array of Stacks
 * 
 * @author johnson_891609
 * @version 12/17/2018 v1.1.0
 */
public class HashTable<T>
{
	private Stack<T>[] table;
	public HashTable()
	{
		table = new Stack[50];
		for(int i = 0;i<table.length;i++)
			table[i] = new Stack<T>();
	}

	/**
	 * Adds an object to the hashtable based on its Hash value.
	 * @param obj - the object to be added to the hash table
	 */
	public void add(T obj)
	{
		//System.out.println("add");
		int i = obj.hashCode();
			table[i].push(obj);
	}
	
	public Stack<T> getStack(int i){
		return table[i];
	}

	public String toString()
	{ //returns the contents of the hash table.
		String output="HASHTABLE\n";
		for(int i = 0;i<table.length;i++) {
			output +="bucket " + i + " ";
			for(int num = 0;num<table[i].size();num++)
				output += table[i].get(num) + " ";
			output += "\n";
			
		}

		return output;
	}
}
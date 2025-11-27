package implementations;

import java.util.EmptyStackException;
import java.util.NoSuchElementException;
import java.util.Arrays;

import utilities.Iterator;
import utilities.StackADT;

public class MyStack<E> implements StackADT<E>
{
	private MyArrayList<E> list;
	
	public MyStack()
	{
		list = new MyArrayList<>();
	}
	
	@Override 
	public void push(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot push null.");
		}
		
		list.add(toAdd);
	}
	
	@Override
	public E pop() throws EmptyStackException
	{
		if (list.isEmpty())
		{
			throw new EmptyStackException();
		}
		
		return list.remove(list.size() - 1);
	}
	
	@Override
	public E peek() throws EmptyStackException
	{
		if (list.isEmpty())
		{
			throw new EmptyStackException();
		}
		
		return list.get(list.size() - 1);
	}
	
	@Override
	public void clear()
	{
		list.clear();
	}
	
	@Override 
	public boolean isEmpty()
	{
		return list.isEmpty();
	}
	
	@Override
	public Object[] toArray()
	{
		Object[] result = new Object[list.size()];

	    int top = list.size() - 1;

	    for (int i = 0; i < result.length; i++)
	    {
	        result[i] = list.get(top - i);
	    }

	    return result;
	}
	
	@Override 
	public E[] toArray(E[] holder) throws NullPointerException
	{
		if (holder == null)
	        throw new NullPointerException();

	    int size = list.size();

	    if (holder.length < size)
	    {
	        holder = Arrays.copyOf(holder, size);
	    }

	    int top = size - 1;

	    for (int i = 0; i < size; i++)
	    {
	        holder[i] = list.get(top - i);
	    }

	    return holder;
	}
	
	@Override
	public boolean contains(E toFind) throws NullPointerException
	{
		return list.contains(toFind);
	}
	
	@Override
	public int search(E toFind)
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		for (int i = list.size() - 1, position = 1; i >= 0; i--, position++)
		{
			if (list.get(i).equals(toFind))
			{
				return position;
			}
		}
		
		return -1;
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return new StackIterator();
	}
	
	private class StackIterator implements Iterator<E>
	{
		private int current = list.size() - 1;
		
		@Override 
		public boolean hasNext()
		{
			return current >= 0;
		}
		
		@Override
		public E next()
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			return list.get(current--);
		}
	}
	
	@Override
	public boolean equals(StackADT<E> that)
	{
		if (that == null)
		{
			return false;
		}
		
		if (this.size() != that.size())
		{
			return false;
		}
		
		Iterator<E> it1 = this.iterator();
		Iterator<E> it2 = that.iterator();
		
		while (it1.hasNext() && it2.hasNext())
		{
			E a = it1.next();
			E b = it2.next();
			
			if (!a.equals(b))
			{
				return false;
			}
		}
		
		return true;
	}
	
	@Override
	public int size()
	{
		return list.size();
	}
	
	@Override
	public boolean stackOverflow()
	{
		return false;
	}
}


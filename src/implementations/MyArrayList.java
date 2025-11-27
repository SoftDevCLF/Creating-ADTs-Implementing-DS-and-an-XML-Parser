package implementations;

import java.util.NoSuchElementException;
import java.util.Arrays;

import utilities.Iterator;
import utilities.ListADT;


public class MyArrayList<E> implements ListADT<E>	
{
	private E[] elements;
	private int size;
	private static final int DEFAULT_CAPACITY = 10;
	
	
	public MyArrayList()
	{
		elements = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	private void ensureCapacity()
	{
		if (size == elements.length)
		{
			elements = Arrays.copyOf(elements, elements.length * 2);
		}
	}
	
	@Override
	public int size()
	{
		return this.size;
	}
	
	@Override
	public void clear()
	{
		elements = (E[]) new Object[DEFAULT_CAPACITY];
		size = 0;
	}
	
	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null.");
		}
		
		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		ensureCapacity();
		
		for (int i = size; i > index; i--)
		{
			elements[i] = elements[i - 1];
		}
		
		elements[index] = toAdd;
		size++;
		
		return true;
		
	}
	
	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null.");
		}
		
		ensureCapacity();
		
		elements[size] =toAdd;
		size++;
		
		return true;
	}
	
	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException();
		}
			
		Iterator<? extends E> it = toAdd.iterator();
		
		while (it.hasNext())
		{
			add(it.next());
		}
		
		return true;
	}
	
	@Override
	public E get( int index ) throws IndexOutOfBoundsException 
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
			
		return elements[index];
	}
	
	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Invalid index.");
		}
		
		E removed = elements[index];
		
		for (int i = index; i < size - 1; i++)
		{
			elements[i] = elements[i + 1];
		}
		
		elements[size - 1] = null;
		size--; 
		
		return removed;
	}
	
	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException();
		}
			
		for (int i = 0; i < size; i++)
		{
			if (elements[i].equals(toRemove))
			{
				return remove(i);
			}
		}
		
		return null;
	}
	
	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException();
		}
			
		
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException();
		}
			
		
		E oldValue = elements[index];
		elements[index] = toChange;
		
		return oldValue;
	}
	
	@Override
	public boolean isEmpty()
	{
		return size == 0;
	}
	
	@Override 
	public boolean contains(E toFind) throws NullPointerException
	{
		if (toFind == null)
		{
			throw new NullPointerException();
		}
		
		for (int i = 0; i < size; i++)
		{
			if (elements[i].equals(toFind))
			{
				return true;
			}
		}
		
		return false;
	}
	
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException();
		}
			
		if (toHold.length < size)
		{
			return Arrays.copyOf(elements, size, (Class<? extends E[]>) toHold.getClass());
		}
		
		for (int i = 0; i < size; i++)
		{
			toHold[i] = elements[i];
		}
		
		return toHold;
	}
	
	@Override
	public Object[] toArray()
	{
		return Arrays.copyOf(elements, size);
	}
	
	@Override
	public Iterator<E> iterator()
	{
		return new MyArrayListIterator();
	}
	
	private class MyArrayListIterator implements Iterator<E>
	{
		private int current = 0; 
		
		@Override
		public boolean hasNext()
		{
			return current < size;
		}
		
		@Override
		public E next() throws NoSuchElementException
		{
			if (!hasNext())
			{
				throw new NoSuchElementException();
			}
			
			return elements[current++];
		}
	}
}

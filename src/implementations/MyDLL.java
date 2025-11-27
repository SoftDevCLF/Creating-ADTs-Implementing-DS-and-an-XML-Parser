package implementations;

import utilities.ListADT;
import utilities.Iterator;
import java.util.NoSuchElementException;
public class MyDLL<E> implements ListADT<E>
{
	private MyDLLNode<E> head;
	private MyDLLNode<E> tail;
	private int size;

	public MyDLL()
	{
		this.head = this.tail = null;
		this.size = 0;
	}

	@Override
	public int size()
	{
		return size;
	}

	@Override
	public void clear()
	{
		head = tail = null;
		size = 0;

	}

	@Override
	public boolean add(int index, E toAdd) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null element to the list.");
		}

		if (index < 0 || index > size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		// Add a the start of the list
		if (index == 0)
		{
			MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, head, null);
			if (isEmpty())
			{
				head = tail = newNode;
			} else
			{
				head.setPrev(newNode);
				newNode.setNext(head);
				head = newNode;
			}
			size++;
			return true;
		}

		// Add at the end of the list
		if (index == size)
		{
			return add(toAdd);
		}

		// Add in the middle of the list

		MyDLLNode<E> current;
		if (index < size / 2)
		{
			current = head;
			for (int i = 0; i < index; i++)
				current = current.getNext();
		} else
		{
			current = tail;
			for (int i = size - 1; i > index; i--)
				current = current.getPrev();
		}
		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, current, current.getPrev());
		current.getPrev().setNext(newNode);
		current.setPrev(newNode);
		size++;

		return true;
	}

	@Override
	public boolean add(E toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null element to the list.");
		}

		MyDLLNode<E> newNode = new MyDLLNode<>(toAdd, null, tail);

		if (isEmpty())
		{
			head = tail = newNode;
		} else
		{
			tail.setNext(newNode);
			newNode.setPrev(tail);
			tail = newNode;
		}
		size++;
		return true;
	}

	@Override
	public boolean addAll(ListADT<? extends E> toAdd) throws NullPointerException
	{
		if (toAdd == null)
		{
			throw new NullPointerException("Cannot add null collection to the list.");
		}

		if (toAdd.isEmpty())
		{
			return false;
		}

	    int otherSize = toAdd.size();
	    for (int i = 0; i < otherSize; i++) {
	        E element = toAdd.get(i); 
	        add(element);             
	    }
		return true;
	}

	@Override
	public E get(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		MyDLLNode<E> currentNode;
		if (index < size / 2)
		{
			currentNode = head;
			for (int i = 0; i < index; i++)
			{
				currentNode = currentNode.getNext();
			}
		} else
		{
			currentNode = tail;
			for (int i = size - 1; i > index; i--)
			{
				currentNode = currentNode.getPrev();
			}
		}
		return currentNode.getElement();
	}

	@Override
	public E remove(int index) throws IndexOutOfBoundsException
	{
		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		MyDLLNode<E> currentNode;
		if (index < size / 2)
		{
			currentNode = head;
			for (int i = 0; i < index; i++)
			{
				currentNode = currentNode.getNext();
			}
		} else
		{
			currentNode = tail;
			for (int i = size - 1; i > index; i--)
			{
				currentNode = currentNode.getPrev();
			}
		}

		E element = currentNode.getElement();
		MyDLLNode<E> prevNode = currentNode.getPrev();
		MyDLLNode<E> nextNode = currentNode.getNext();
		if (prevNode != null)
		{
			prevNode.setNext(nextNode);
		} else
		{
			head = nextNode;
		}

		if (nextNode != null)
		{
			nextNode.setPrev(prevNode);
		} else
		{
			tail = prevNode;
		}
		size--;

		return element;
	}

	@Override
	public E remove(E toRemove) throws NullPointerException
	{
		if (toRemove == null)
		{
			throw new NullPointerException("Cannot remove null element from the list.");
		}

		MyDLLNode<E> current = head;
		while (current != null)
		{
			if (current.getElement().equals(toRemove))
			{
				// Update previous node
				if (current.getPrev() != null)
				{
					current.getPrev().setNext(current.getNext());
				} else
				{
					head = current.getNext();
				}

				// Update next node
				if (current.getNext() != null)
				{
					current.getNext().setPrev(current.getPrev());
				} else
				{
					tail = current.getPrev();
				}

				size--;
				return current.getElement();
			}

			current = current.getNext();
		}

		return null; // element not found

	}

	@Override
	public E set(int index, E toChange) throws NullPointerException, IndexOutOfBoundsException
	{
		if (toChange == null)
		{
			throw new NullPointerException("Cannot set null element in the list.");
		}

		if (index < 0 || index >= size)
		{
			throw new IndexOutOfBoundsException("Index out of bounds");
		}

		MyDLLNode<E> currentNode;
		if (index < size / 2)
		{
			currentNode = head;
			for (int i = 0; i < index; i++)
			{
				currentNode = currentNode.getNext();
			}
		} else
		{
			currentNode = tail;
			for (int i = size - 1; i > index; i--)
			{
				currentNode = currentNode.getPrev();
			}
		}

		E oldElement = currentNode.getElement();
		currentNode.setElement(toChange);
		return oldElement;

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
			throw new NullPointerException("Cannot search for null element in the list.");
		}

		MyDLLNode<E> current = head;
		while (current != null)
		{
			if (current.getElement().equals(toFind))
			{
				return true;
			}
			current = current.getNext();
		}

		return false;
	}

	@SuppressWarnings("unchecked")
	@Override
	public E[] toArray(E[] toHold) throws NullPointerException
	{
		if (toHold == null)
		{
			throw new NullPointerException("The array is null.");
		}

		if (toHold.length < size)
		{
			toHold = (E[]) java.lang.reflect.Array.newInstance(toHold.getClass().getComponentType(), size);
		}

		MyDLLNode<E> current = head;
		int i = 0;

		while (current != null)
		{
			toHold[i++] = current.getElement();
			current = current.getNext();
		}

		if (toHold.length > size)
		{
			toHold[size] = null;
		}

		return toHold;
	}

	@Override
	public Object[] toArray()
	{
		Object[] array = new Object[size];
		MyDLLNode<E> current = head;
		for (int i = 0; i < size; i++)
		{
			array[i] = current.getElement();
			current = current.getNext();
		}
		return array;
	}

	@Override
	public Iterator<E> iterator()
	{
		Object[] elements = this.toArray();

		return new Iterator<E>()
		{
			private int currentIndex = 0;

			@Override
			public boolean hasNext()
			{
				return currentIndex < elements.length;
			}

			@SuppressWarnings("unchecked")
			@Override
			public E next()
			{
				if (!hasNext())
				{
					 throw new NoSuchElementException("No more elements in the iterator.");
				}
				return (E) elements[currentIndex++];
			}
		};

	}

}

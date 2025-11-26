package implementations;

public class MyDLLNode<E>
{
	private E element;
	private MyDLLNode<E> next;
	private MyDLLNode<E> prev;

	public MyDLLNode(E element, MyDLLNode<E> next, MyDLLNode<E> prev)
	{
		super();
		this.element = element;
		this.next = next;
		this.prev = prev;
	}

	/**
	 * @return the element
	 */
	public E getElement()
	{
		return element;
	}

	/**
	 * @param element the element to set
	 */
	public void setElement(E element)
	{
		this.element = element;
	}

	/**
	 * @return the next
	 */
	public MyDLLNode<E> getNext()
	{
		return next;
	}

	/**
	 * @param next the next to set
	 */
	public void setNext(MyDLLNode<E> next)
	{
		this.next = next;
	}

	/**
	 * @return the prev
	 */
	public MyDLLNode<E> getPrev()
	{
		return prev;
	}

	/**
	 * @param prev the prev to set
	 */
	public void setPrev(MyDLLNode<E> prev)
	{
		this.prev = prev;
	}



}

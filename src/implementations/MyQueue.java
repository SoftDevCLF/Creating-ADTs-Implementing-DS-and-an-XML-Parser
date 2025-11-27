package implementations;

import exceptions.EmptyQueueException;
import utilities.QueueADT;
import utilities.Iterator;

public class MyQueue<E> implements QueueADT<E>
{
    private MyDLL<E> list = new MyDLL<>();

    @Override
    public void enqueue(E toAdd) throws NullPointerException
    {
        if (toAdd == null)
            throw new NullPointerException("Cannot enqueue null.");
        list.add(toAdd);
    }

    @Override
    public E dequeue() throws EmptyQueueException
    {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty.");
        return list.remove(0);
    }

    @Override
    public E peek() throws EmptyQueueException
    {
        if (isEmpty())
            throw new EmptyQueueException("Queue is empty.");
        return list.get(0);
    }

    @Override
    public void dequeueAll()
    {
        list.clear();
    }

    @Override
    public boolean isEmpty()
    {
        return list.size() == 0;
    }

    @Override
    public boolean contains(E toFind) throws NullPointerException
    {
        return list.contains(toFind);
    }

    @Override
    public int search(E toFind)
    {
        for (int i = 0; i < list.size(); i++)
            if (list.get(i).equals(toFind))
                return i + 1;
        return -1;
    }

    @Override
    public Iterator<E> iterator()
    {
        return list.iterator();
    }

    @Override
    public boolean equals(QueueADT<E> that)
    {
        if (that == null || this.size() != that.size())
            return false;

        Iterator<E> it1 = this.iterator();
        Iterator<E> it2 = that.iterator();

        while (it1.hasNext())
            if (!it1.next().equals(it2.next()))
                return false;

        return true;
    }

    @Override
    public Object[] toArray()
    {
        return list.toArray();
    }

    @Override
    public E[] toArray(E[] holder) throws NullPointerException
    {
        return list.toArray(holder);
    }

    @Override
    public boolean isFull()
    {
        return false;
    }

    @Override
    public int size()
    {
        return list.size();
    }
}
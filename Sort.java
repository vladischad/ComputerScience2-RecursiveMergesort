import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/**
 * Class for sorting lists that implement the IndexedUnsortedList interface,
 * using ordering defined by class of objects in list or a Comparator.
 * As written uses Mergesort algorithm.
 *
 * @author CS221
 */
public class Sort
{	
	/**
	 * Returns a new list that implements the IndexedUnsortedList interface. 
	 * As configured, uses WrappedDLL. Must be changed if using 
	 * your own IUDoubleLinkedList class. 
	 * 
	 * @return a new list that implements the IndexedUnsortedList interface
	 */
	private static <T> IndexedUnsortedList<T> newList() 
	{
		return new WrappedDLL<T>(); //TODO: replace with your IUDoubleLinkedList for extra-credit
	}
	
	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @see IndexedUnsortedList 
	 */
	public static <T extends Comparable<T>> void sort(IndexedUnsortedList<T> list) 
	{
		mergesort(list);
	}

	/**
	 * Sorts a list that implements the IndexedUnsortedList interface 
	 * using given Comparator.
	 * DO NOT MODIFY THIS METHOD
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 * @see IndexedUnsortedList 
	 */
	public static <T> void sort(IndexedUnsortedList <T> list, Comparator<T> c) 
	{
		mergesort(list, c);
	}
	
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface, 
	 * using compareTo() method defined by class of objects in list.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list, must extend Comparable
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 */
	private static <T extends Comparable<T>> void mergesort(IndexedUnsortedList<T> list)
	{
		if (list.size() > 1)	//if list contains more than 1 element
		{
			T pivot = list.first();

			IndexedUnsortedList<T> less = new WrappedDLL<T>();
			IndexedUnsortedList<T> equal = new WrappedDLL<T>();
			IndexedUnsortedList<T> more = new WrappedDLL<T>();

			for (T i: list)		//sorting elements from list to lists with elements that are less, equal or more than firs element from list
			{
				if (i.compareTo(pivot) < 0) 
				{
					less.add(i);
				}
				else if (i.compareTo(pivot) > 0) 
				{
					more.add(i);
				}
				else 
				{
					equal.add(i);
				}
			}

			mergesort(less);	//sorting elemets from less list
			mergesort(more);	//sorting elemets from more list
			
			//combining less, equal and more lists in one sorted list
			for (T i: less)
			{
				list.addToRear(i);
				list.remove(list.first());
			}

			for (T i: equal)
			{
				list.addToRear(i);
				list.remove(list.first());
			}

			for (T i: more)
			{
				list.addToRear(i);
				list.remove(list.first());
			}
		}
	}
		
	/**
	 * Mergesort algorithm to sort objects in a list 
	 * that implements the IndexedUnsortedList interface,
	 * using the given Comparator.
	 * DO NOT MODIFY THIS METHOD SIGNATURE
	 * 
	 * @param <T>
	 *            The class of elements in the list
	 * @param list
	 *            The list to be sorted, implements IndexedUnsortedList interface 
	 * @param c
	 *            The Comparator used
	 */
	private static <T> void mergesort(IndexedUnsortedList<T> list, Comparator<T> c)
	{
		if(list.size() > 1)		//if list contains more than 1 element
		{
			T pivot = list.remove(list.last());

			IndexedUnsortedList<T> less = new WrappedDLL<T>();
			IndexedUnsortedList<T> equal = new WrappedDLL<T>();
			IndexedUnsortedList<T> more = new WrappedDLL<T>();
			T current;

			while(!list.isEmpty())		//sorting elements from list to lists with elements that are less, equal or more than firs element from list
			{
				current = list.remove(list.first());

				if(c.compare(current, pivot) < 0)
				{
					less.addToFront(current);
				}
				else if(c.compare(current, pivot) == 0)
				{
					equal.addToFront(current);
				}
				else
				{
					more.addToFront(current);
				}
			}

			mergesort(less, c);		//sorting elemets from less list
			mergesort(more, c);		//sorting elemets from more list

			//combining less, equal and more lists in one sorted list
			while(!less.isEmpty())
			{
				list.addToRear(less.remove(less.first()));
			}

			while(!equal.isEmpty())
			{
				list.addToRear(equal.remove(equal.first()));
			}

			list.addToRear(pivot);

			while(!more.isEmpty())
			{
				list.addToRear(more.remove(more.first()));
			}
	}
	}
}
public interface Queue<T> {

	/**
     * Removes the reference to element at the front of
     * the queue. Assumes the queue is not empty
     * @return 
     *		The reference to removed element
     */
	T dequeue();

	/**
     * Add the reference to generic element at the rear of
     * the queue. Assumes element is not null
     * @param element
     *		The (non null) reference to the new element
     */
	void enqueue(T element);

	/**
     * Returns true if the Queue is currently empty
     * @return 
     *		true if the queue is empty 
     */
	boolean isEmpty();
}

public class QueueImplementation<T> implements Queue<T> {

	private static final int MAX_QUEUE_SIZE = 1000;
	private T[] q;
	private int front, rear, count;

    /**
     * Constructor, initializes  <b>queue</b>
     */
	@SuppressWarnings("unchecked")
	public QueueImplementation(){
		q = (T[]) new Object[MAX_QUEUE_SIZE];
		front = 0;
		rear = 0; // represents the empty queue
		count = 0;
	}

    /**
     * Constructor, initializes  <b>queue</b>
     * @param size
     *      The the size of the queue to initialize
     */
	@SuppressWarnings("unchecked")
	public QueueImplementation(int size){
		q = (T[]) new Object[size];
		front = 0;
		rear = 0; // represents the empty queue
		count = 0;
	}

	/**
     * implementation of the method <b>dequeue</b> 
     * from the interface <b>Queue</b>.
     * @return 
     *      The reference to removed element
     */
	public T dequeue(){
		T item = q[front];
		q[front] = null;
		front = (front+1) % q.length;
		count --;
		return(item);
	}

	/**
     * implementation of the method <b>enqueue</b> 
     * from the interface <b>Queue</b>.
     * @param element
     *      The reference to the new element
     */
	@SuppressWarnings("unchecked")
	public void enqueue(T element){
		if(size()<q.length){
			q[rear] = element;
			rear = ( rear+1 ) % q.length;
			count ++;
		}
		else{
			expand();
			enqueue(element);
		}		
		
	}

	@SuppressWarnings("unchecked")
	public void expand(){
		T[] newer = (T[]) new Object[q.length*2];

		for(int i=0; i<count; i++){
			newer[i]=q[front];
			front = (front+1) % q.length;
		front = 0;
		rear = count;
		q = newer;
		}
	}

    /**
     * implementation of the method <b>isEmpty</b> 
     * from the interface <b>Queue</b>.
     * @return 
     *      true if the queue is empty 
     */
	@SuppressWarnings("unchecked")
	public boolean isEmpty(){
		return(count == 0);
	}

    /**
     * Returns but does not remove front element
     * of the queue.
     * @return 
     *      front element of queue
     */
	@SuppressWarnings("unchecked")
	public T peek(){
		return q[front];	

	}

	/**
     * Returns number of elements in array.
     * @return 
     *      count of elements in array
     */
	@SuppressWarnings("unchecked")
	public int size(){
		return count;
	}

}

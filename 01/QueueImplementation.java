public class QueueImplementation<E> implements Queue<E> {

	private static final int MAX_QUEUE_SIZE = 100;
	private E[] q;
	private int front, rear, count;

	public QueueImplementation(){
		q = (E[]) new Object[MAX_QUEUE_SIZE];
		front = 0;
		rear = -1; // represents the empty queue
		count = 0;
	}

	public E dequeue(){
		int f = front;
		front = null;
		front = (front+1) % MAX_QUEUE_SIZE;
		count -= 1;
		return(f);
	}

	public void enqueue(E o){
		rear = ( rear+1 ) % MAX_QUEUE_SIZE;
		q[rear] = o;
		count += 1;
	}

	public boolean isEmpty(){
		return(queue.size() == 0);
	}

	public E peek(){
		return q[front];
	}

	public int getCount(){
		return count;
	}

}

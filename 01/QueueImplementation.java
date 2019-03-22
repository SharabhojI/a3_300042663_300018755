public class QueueImplementation<T> implements Queue<T> {

	private static final int MAX_QUEUE_SIZE = 100;
	private T[] q;
	private int front, rear, count;

	@SuppressWarnings("unchecked")
	public QueueImplementation(){
		q = (T[]) new Object[MAX_QUEUE_SIZE];
		front = 0;
		rear = 0; // represents the empty queue
		count = 0;
	}

	public QueueImplementation(int size){
		q = (T[]) new Object[size];
		front = 0;
		rear = 0; // represents the empty queue
		count = 0;
	}

	public T dequeue(){
		T item = q[front];
		q[front] = null;
		front = (front+1) % q.length;
		count --;
		return(item);
	}

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

	public boolean isEmpty(){
		return(count == 0);
	}

	public T peek(){
		return q[front];	

	}

	public int size(){
		return count;
	}

}

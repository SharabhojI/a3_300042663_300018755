public interface Queue<T> {

	T dequeue();

	void enqueue(T element);

	boolean isEmpty();
}

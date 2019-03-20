public interface Queue<E> {

	E dequeue();

	void enqueue(E o);

	boolean isEmpty();
}

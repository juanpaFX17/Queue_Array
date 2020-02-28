public class ArrayQueue<T> implements Queue<T>{
    //fields
    int front;
    int rear;
    T[] queue;

    public ArrayQueue(int initialCapacity){
        if(initialCapacity<1)
            throw new IllegalArgumentException
                    ("initialCapacity must be >=1");
        queue = (T[]) new Object[initialCapacity + 1];
        front = rear = 0;
    }

    public ArrayQueue(){
        this(10);
    }

    public boolean isEmpty(){
        return front == rear;
    }

    public T getFrontElement(){
        if(isEmpty()) return null;
        else return queue[(front+1)%queue.length];
    }

    public T getRearElement(){
        if(isEmpty()) return null;
        else return queue[rear];
    }

    public void put(T theElement){
        if((rear+1)%queue.length == front){
            T[] newQueue = (T[]) new Object[2 * queue.length];

            int start = (front+1)%queue.length;
            if(start<2)
                System.arraycopy(queue, start, newQueue,0,queue.length - 1);
            else
            {
                System.arraycopy(queue, start, newQueue,0,queue.length - start);
            }

            front = newQueue.length-1;
            rear = queue.length - 2;
            queue = newQueue;
        }
        rear = (rear+1) % queue.length;
        queue[rear] = theElement;
    }

    public T remove(){
        if(isEmpty()) return null;
        front = (front+1) % queue.length;
        T frontElement = queue[front];
        queue[front] = null;
        return frontElement;
    }

    public static void main(String[] args){
        int x;
        ArrayQueue<Integer> q = new ArrayQueue<>(3);

        q.put(new Integer(1));
        q.put(new Integer(2));
        q.put(new Integer(3));
        q.put(new Integer(4));

        q.remove();
        q.remove();
        q.put(new Integer(5));
        q.put(new Integer(6));
        q.put(new Integer(8));
        q.put(new Integer(9));
        q.put(new Integer(10));
        q.put(new Integer(11));
        q.put(new Integer(12));

        while(!q.isEmpty()){
            System.out.println("Rear element is " + q.getRearElement());
            System.out.println("Front element is " + q.getFrontElement());
            System.out.println("Removed the element " + q.remove());
        }
    }
}

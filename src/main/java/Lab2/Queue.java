package Lab2;

public  class Queue {
    private  int size;
    private int[] queArray;
    private int front;
    private int rear;

    public Queue(int size) {
        queArray = new int[size];
        front = 0;
        rear = -1;
    }

    public void insert(int j) // put item at rear of queue
    {
        if (rear == size - 1)
            rear = -1;
        queArray[++rear] = j;
    }

    public int remove() // take item from front of queue
    {
        int temp = queArray[front++];
        if (front == size)
            front = 0;
        return temp;
    }


    public boolean isEmpty() // true if queue is empty
    {
        return (rear + 1 == front || (front + size - 1 == rear));
    }

    public void setFront(int front) {
        this.front = front;
    }

    public void setRear(int rear) {
        this.rear = rear;
    }
}


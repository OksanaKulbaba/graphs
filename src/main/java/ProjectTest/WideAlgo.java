package ProjectTest;
public class WideAlgo {

    public static void main(String[] args) {
        Graph theGraph = new Graph(5);
        theGraph.addVertex('A'); // 0
        theGraph.addVertex('B'); // 1
        theGraph.addVertex('C'); // 2
        theGraph.addVertex('D'); // 3
        theGraph.addVertex('E'); // 4
        theGraph.addEdge(0, 1); // AB
        theGraph.addEdge(1, 2); // BC
        theGraph.addEdge(0, 3); // AD
        theGraph.addEdge(3, 4); // DE
        System.out.print("Посещения: ");
        theGraph.bfs(2,4);
        System.out.println();
    }

    static class Queue {
        private  int size;
        private int[] queArray;
        private int front;
        private int rear;

        public Queue(int size) {
            queArray = new int[size];
            front = 0;
            rear = -1;
            this.size = size;
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
    }

    private static class Vertex {
        int number;
        boolean wasVisited;
        int parent;
        private boolean isAvailable;

        Vertex(int number) {
            this.number = number;
            wasVisited = false;
            parent = -1;
            isAvailable = true;
        }
    }

    private static class Graph {
        private int vertexCount;
        private int vertexMaxCount;
        private Vertex[] vertexList;
        private int[][] listAdjacency;
        private Queue queue;

        public Graph(int count) {
            this.vertexMaxCount = count;
            vertexCount = 0;
            vertexList = new Vertex[vertexMaxCount];
            listAdjacency = new int[vertexMaxCount][vertexMaxCount];

            for (int i = 0; i < vertexMaxCount - 1; i++) {
                for (int j = 0; j < vertexMaxCount; j++) {
                    listAdjacency[i][j] = 0;
                }
            }

            queue = new Queue(count);
        }

        public void addVertex(int number) {
            vertexList[vertexCount++] = new Vertex(number);
        }

        public void addEdge(int from, int to) {
            listAdjacency[from][to] = 1;
            listAdjacency[to][from] = 1;
        }

        public void setAvailable(int index, boolean flag) {
            vertexList[index].isAvailable = flag;
        }

        public void displayVertex(int index) {
            System.out.print(Integer.toString(vertexList[index].number) + " ");
        }

        private void getPath(int vertex) {
            int v = vertexList[vertex].parent;
            if (v == -1) {
                System.out.println();
                return;
            }
            System.out.print("" + v + " ");
            getPath(v);
        }

        public void bfs(int s, int e) {
            int v, v1;
            for (int j = 0; j < vertexCount; j++) {
                vertexList[j].wasVisited = false;
            }
            v1 = 0;
            queue.insert(s);
            vertexList[s].wasVisited = true;
            while (true) {
                if (queue.isEmpty()) {
                    System.out.println("Путь не найден");
                    break;
                }
                if (v1 == e) {
                    System.out.print("Путь найден: ");
                    System.out.print("" + vertexList[v1].number + " ");
                    getPath(v1);
                    System.out.println();
                    break;
                }
                v = queue.remove();
                for (int j = 0; j < vertexCount; j++) {
                    if (listAdjacency[v][j] == 1 && vertexList[j].isAvailable == true
                            && vertexList[j].wasVisited == false) {
                        queue.insert(j);
                        vertexList[j].wasVisited = true;
                        vertexList[j].parent = v;

                        if (e == j) {
                            v1 = j;
                        }
                    }
                }
            }
        }
    }
}

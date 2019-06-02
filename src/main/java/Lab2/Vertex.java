package Lab2;

public class Vertex {
    int label; // label
    boolean wasVisited;
    int wave;

    public int getIndexFromM() {
        return indexFromM;
    }

    int indexFromM;
    Vertex parent;
    private boolean isAvailable;

    public boolean isAvailable() {
        return isAvailable;
    }

    public Vertex(int l, int index) {
        label = l;
        wasVisited = false;
        isAvailable = true;
        parent  = null;
        indexFromM = index;

    }

    public int getParentWave() {
        return parent.wave;
    }

    public int getParentIndex() {
        if (parent == null) return -1;
        return parent.indexFromM;
    }
}

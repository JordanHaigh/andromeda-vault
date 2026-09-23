/**
 * Class name: StationHeap
 * Student Numbers: c3256741 && c3256730 - Kyle Fennell and Jordan Haigh
 * Purpose of Class: this is an indirect max heap (min heap in this case) that is used like a
 * priority queue for the fringe nodes in dijkstra's. It uses explicitly stations as
 * nodes.
 */

public class StationHeap {

    private Station[] heap;
    private int[] into;
    private int[] outof;
    private int capacity = 1;
    private int size = -1;

    public StationHeap() {
        heap = new Station[capacity];
        into = new int[capacity];
        outof = new int[capacity];
    }

    /**
     * Removes a station from the indirect heap
     * @return the top station value in the heap
     */
    public Station remove(){
        Station rootStation = heap[outof[0]];       // gets the top value from the heap
        int aux = outof[0];                         // saves the position it was in
        outof[0] = outof[size];                     // moves the last value to the top
        size--;
        siftDown(0);                        // rectify the heap
        outof[size+1] = aux;                        // place the saved value outside the heap so it is the next place added to for a new entry
        generateInto();                             // fix the into array
        return rootStation;                         // return the top node of the heap
    }

    /**
     * Adds a new station to the indirect heap
     * @param s -  the station being inserted into the heap
     */
    // inserts a node into the heap
    public void add(Station s){
        size++;
        if (size >= capacity) {                     // if we're out of room in the heap
            resize();                               // double the size of the heap
        }
        int current = size;                         // current position being checked
        heap[outof[current]] = s;                   // add the node the the final position
        while (current > 0 && s.compareTo(heap[outof[(current-1)/2]]) > 0){     // while we're not at the top of the heap and the node is greater than its parent
            int aux = outof[current];               // switch the node for its parent
            outof[current] = outof[(current-1)/2];
            outof[(current-1)/2] = aux;
            current = (current-1) / 2;              // current = current's parent
        }
        generateInto();                             // rectify the into array
    }

    /**
     *  Doubles the size of the heap arrays once size is reached
     */
    //doubles the size of all arrays in the heap
    private void resize() {
        capacity *= 2;                                  // double the capacity of the arrays
        Station[] tempHeap = new Station[capacity];
        int[] tempInto = new int[capacity];
        int[] tempOutof = new int[capacity];
        for (int i = 0; i < heap.length; i++) {         // copy the contents into the new arrays
            tempHeap[i] = heap[i];
            tempInto[i] = into[i];
            tempOutof[i] = outof[i];
        }
        for (int i = heap.length; i < capacity; i++){   // fill the rest of the arrays with values equal to their index
            tempInto[i] = i;
            tempOutof[i] = i;
        }
        heap = tempHeap;
        into = tempInto;
        outof = tempOutof;
    }

    /**
     * Siftdown algorithm for fixing the heap structure
     * @param position -  the initial position to sift down from
     */
    // rectifies the max(min) heap
    private void siftDown(int position) {
        int current = position;
        while(leftChild(current) <= size){              // while the current node has a child
            int child = leftChild(current);             // the potentially larger child is the left child
            if (child < size && heap[outof[rightChild(current)]].compareTo(heap[outof[leftChild(current)]]) > 0){
                //if there is a right child and it is larger than the left child
                child = rightChild(current);            // the potentially larger child is the right child
            }
            if (heap[outof[child]].compareTo(heap[outof[position]]) > 0){
                // if the child found is larger than its parent
                int aux = outof[current];
                outof[current] = outof[child];
                outof[child] = aux;
            }
            else {      // parents is bigger than both children
                break;
            }
        }
        outof[current] = outof[position];
    }

    /**
     * Rectifies the heap structure such that the max value is always at the top of the heap
     */
    public void heapify(){
        for (int i = size/2; i >= 0; i--){
            siftDown(i);
        }
    }

    /**
     * Regenerates the into array after adding or removing a station
     */
    private void generateInto(){
        for (int i = 0; i < size; i++){
            into[outof[i]] = i;
        }
    }

    /**
     * Regenerates the outof array after adding or removing a station
     */
    private void generateOutof(){
        for (int i = 0; i < size; i++){
            outof[into[i]] = i;
        }
    }

    /**
     * Returns the index of the left child of the index
     * @param i - Index to return the left child
     * @return - Left child of parameter
     */
    private int leftChild(int i){ return i*2+1; }

    /**
     * Returns the index of the right child of the index
     * @param i - Index to return the right child
     * @return - Right child of parameter
     */
    private int rightChild(int i){ return i*2+2; }

    /**
     * ToString method for printing the heap
     * Mostly used in debugging
     * @return - Sting of heap contents
     */
    @Override
    public String toString(){ return "Printing heap:\n"+prefixRecurse(0, ""); }

    /**
     * Prefix Recursion printout method
     * Used in debugging
     * @param pos - Current position to print
     * @param path - Path that prints for position in tree
     * @return - String printout of position of station in heap
     */
    private String prefixRecurse(int pos, String path){
        String out = pos+" "+heap[outof[pos]].toString();
        if (size >= leftChild(pos)){
            out += "\n"+path+"/"+prefixRecurse(leftChild(pos), path+"/");
        }
        if (size >= rightChild(pos)){
            out += "\n"+path+"\\"+prefixRecurse(rightChild(pos), path+"\\");
        }
        return out;
    }
}

import java.util.*;
import java.io.*;

public class template {
    public static void main(String[] args) throws FileNotFoundException {
        // Scanner in = new Scanner(System.in);
        // Scanner in = new Scanner(new File("template.in"));

        // in.close();
        int[] x = {1,3,2,3,3,3,5,4,1,2};
        Heap heap = new Heap(x.length);
        for(int i=0;i<x.length;i++){
            heap.push(x[i]);
        }
        for(int i=0;i<x.length;i++){
            System.out.print(heap.pop());
            System.out.print(" ");
        }
    }

}

class Heap {

    private int[] heap;
    private int size;
    private int length;

    public Heap(int size) {
        this.size = size;
        this.heap = new int[size];
        this.length = 0;
    }

    public void push(int x) {
        // 插入元素，放在最后
        assert length < size;
        // 存放x的位置
        int i = length++;
        while (i > 0) {
            // 得到父节点
            int j = (i - 1) / 2;
            // 若父节点大于x，则提升x到父节点，继续上升
            // 否则当前位置已经是存放位置（x<=父节点），跳出
            if (heap[j] > x) {
                heap[i] = heap[j];
                i = j;
            } else {
                break;
            }
        }
        heap[i] = x;
    }

    public int pop() {
        // 根节点最小
        int ret = heap[0];
        // 提到根节点的数值
        int x = heap[--length];
        int i = 0;  // 存放x的位置
        while (i * 2 + 1 < length) {
            // 获取两个子节点
            int a = i * 2 + 1;
            int b = i * 2 + 2;
            // 选择一个最小的位置存放
            if (b < length && heap[b] < heap[a]) {
                a = b;
            }
            // 当前子节点大于x，位置合适，跳出
            if (heap[a] >= x)
                break;
            // 选择该子节点位置，继续找大的位置
            heap[i] = heap[a];
            i = a;
        }
        heap[i] = x;
        return ret;
    }

    public int top() {
        return heap[0];
    }

    public int size() {
        return size;
    }

}
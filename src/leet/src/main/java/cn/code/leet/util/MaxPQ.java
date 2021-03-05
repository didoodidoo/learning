package cn.code.leet.util;

import org.junit.jupiter.api.Test;

//大根堆
public class MaxPQ {

    int[] pq;
    int N = 0;//size


    public MaxPQ(int size) {
        //pq[0]不使用
        this.pq = new int[size + 1];
    }

    public void offer(int x) {
        pq[++N] = x;
        swim(N);//把这个往上浮
    }

    public int pop() {
        //弹出堆顶元素，下沉
        int max = pq[1];
        pq[1] = pq[N];
        pq[N] = 0;
        N--;
        sink(1);
        return max;
    }

    //自顶向下调整堆 把k位置的数沉到该有的位置上
    private void sink(int k) {
        while (2 * k <= N) {
            int i = 2 * k;
            //从 左右两个儿子里面选一个较大的交换
            if (i < N && pq[i] < pq[i + 1]) i++;
            // 已经调整好了 直接break
            if (pq[i] < k) break;
            swap(k, i);
            k = i;
        }
    }

    //自底向上调整堆 把k位置上的数上浮到该有的位置
    private void swim(int k) {
        //比它父节点大则往上浮
        while (k > 1 && pq[k] > pq[k / 2]) {
            //交换位置
            swap(k, k / 2);
            k = k / 2;
        }
    }

    private void swap(int a, int b) {
        int tmp = pq[a];
        pq[a] = pq[b];
        pq[b] = tmp;
    }
}

class TestPQ{
    @Test
    public void test() {
        System.out.println("start ");
        MaxPQ pq = new MaxPQ(5);
        pq.offer(10);
        pq.offer(29);
        pq.offer(13);
        pq.offer(66);
        pq.offer(1);
        for (int i = 0; i < 5; i++) {
            System.out.print(pq.pop() + " ");
        }
    }
}
package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc1172_Dinner_Plate_Stacks {
    public lc1172_Dinner_Plate_Stacks() {
    }

    public void init(int capacity) {
        this.capacity = capacity;
        stackInfoList = new ArrayList<>();
    }

    private class StackInfo {
        public int id;
        public int capacity;
        public Stack<Integer> stack;

        public StackInfo(int id, int capacity) {
            this.id = id;
            this.capacity = capacity;
            stack = new Stack<>();
        }

        public boolean isFull() {
            return stack.size() == capacity;
        }
    }

    private List<StackInfo> stackInfoList;
    private int capacity;
    private StackInfoQueue queue = new StackInfoQueue();

    public void push(int val) {
        StackInfo si = queue.getStackInfo();

        if (si == null || si.isFull()) {
            StackInfo newSi = new StackInfo(stackInfoList.size()+1, capacity);
            stackInfoList.add(newSi);
            newSi.stack.push(val);
            queue.add(newSi);
            return;
        }

        si.stack.push(val);
        queue.siftDown(si);
    }

    public int pop() {
        int size = stackInfoList.size();

        for (; size > 0; size--) {
            StackInfo si = stackInfoList.get(size-1);

            if (!si.stack.isEmpty())
                break;

            queue.remove(si);
            stackInfoList.remove(size-1);
        }
        if (size == 0)
            return -1;

        StackInfo si = stackInfoList.get(size-1);
        int val = si.stack.pop();

        // remove si from list, if si.stack becomes empty
        if (si.stack.isEmpty()) {
            queue.remove(si);
            stackInfoList.remove(size-1);
        }
        else {
            queue.siftUp(si);
        }

        return val;
    }

    public int popAtStack(int index) {
        if (stackInfoList.size() <= index)
            return -1;

        StackInfo si = stackInfoList.get(index);

        if (si.stack.isEmpty())
            return -1;

        int val = si.stack.pop();

        queue.siftUp(si);
        return val;
    }

    private class StackInfoQueue {
        class StackInfoComparator implements Comparator<StackInfo> {
            @Override
            public int compare(StackInfo s1, StackInfo s2) {
                if (s1.isFull() && !s2.isFull()) {
                    return 1;
                }

                if (!s1.isFull() && s2.isFull()) {
                    return -1;
                }

                return s1.id - s2.id;
            }
        }

        List<StackInfo> heap;
        Map<StackInfo, Integer> map = new HashMap<>();
        private StackInfoComparator comparator = new StackInfoComparator();

        public StackInfoQueue() {
            heap = new ArrayList<>();
        }

        public StackInfo getStackInfo() {
            if (heap.size() == 0)
                return null;

            return heap.get(0);
        }

        public void add(StackInfo si) {
            map.put(si, heap.size());
            heap.add(si);
            siftUp(si);
        }

        public void remove(StackInfo si) {
            int idx = map.get(si);
            int size = heap.size();
            StackInfo lastSi = heap.get(size-1);

            map.put(lastSi, idx);
            heap.set(idx, lastSi);
            siftDown(lastSi);
            heap.remove(size-1);
        }

        public void siftDown(StackInfo si) {
            int curr = map.get(si);
            int N = heap.size();

            while (curr < N) {
                int child = curr*2+1;

                // break if no left child
                if (child > N-1)
                    break;

                // get smaller child, if has right child
                if (child + 1 <= N-1) {
                    if (comparator.compare(heap.get(child), heap.get(child+1)) > 0) {
                        child = child+1;
                    }
                }
                // break if current idx value < child value
                if (comparator.compare(heap.get(curr), heap.get(child)) < 0)
                    break;

                swap(curr, child);
                curr = child;
            }
        }

        public void siftUp(StackInfo si) {
            int curr = map.get(si);

            while (curr > 0) {
                int parent = (curr-1) / 2;
                if (comparator.compare(heap.get(curr), heap.get(parent)) > 0)
                    break;

                swap(curr, parent);
                curr = parent;
            }
        }

        private void swap(int id1, int id2) {
            StackInfo si1 = heap.get(id1);
            StackInfo si2 = heap.get(id2);

            map.put(si1, id2);
            map.put(si2, id1);
            heap.set(id1, si2);
            heap.set(id2, si1);
        }
    }

    @Test
    public void test01() {
        lc1172_Dinner_Plate_Stacks solu = new lc1172_Dinner_Plate_Stacks();

        solu.init(2);
        solu.push(1);
        solu.push(2);
        solu.push(3);
        solu.push(4);
        solu.push(5);
        Assertions.assertEquals(2, solu.popAtStack(0));

        solu.push(20);
        solu.push(21);
        Assertions.assertEquals(20, solu.popAtStack(0));
        Assertions.assertEquals(21, solu.popAtStack(2));

        Assertions.assertEquals(5, solu.pop());
        Assertions.assertEquals(4, solu.pop());
        Assertions.assertEquals(3, solu.pop());
        Assertions.assertEquals(1, solu.pop());
        Assertions.assertEquals(-1, solu.pop());
    }

    @Test
    public void test02() {
        lc1172_Dinner_Plate_Stacks solu = new lc1172_Dinner_Plate_Stacks();

        solu.init(1);
        solu.push(1);
        solu.push(2);
        solu.push(3);
        Assertions.assertEquals(2, solu.popAtStack(1));
        Assertions.assertEquals(3, solu.pop());
        Assertions.assertEquals(1, solu.pop());
    }

    /*
    a better implementation using TreeSet
     */
    class DinnerPlates {
        private int capacity;
        private List<Deque<Integer>> stacks;
        private TreeSet<Integer> nonFullStacks;

        public DinnerPlates(int capacity) {
            this.capacity = capacity;
            this.stacks = new ArrayList<>();
            this.nonFullStacks = new TreeSet();
        }

        private boolean isFullStack(Deque<Integer> stack) {
            return stack.size() == capacity;
        }

        public void push(int val) {
            if (nonFullStacks.isEmpty()) {
                Deque<Integer> stack = new ArrayDeque<>();
                stack.offerFirst(val);
                stacks.add(stack);
                if (!isFullStack(stack)) {
                    nonFullStacks.add(stacks.size() - 1);
                }
            } else {
                Integer first = nonFullStacks.first();
                Deque<Integer> stack = stacks.get(first);
                stack.offerFirst(val);
                if (isFullStack(stack)) {
                    nonFullStacks.remove(first);
                }
            }
        }

        public int pop() {
            if (stacks.isEmpty()) {
                return -1;
            } else {
                return popAtStack(stacks.size() - 1);
            }
        }

        public int popAtStack(int index) {
            if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
                return -1;
            } else {
                Deque<Integer> stack = stacks.get(index);
                int result = stack.pollFirst();
                while (index == stacks.size() - 1 && index >= 0 && stacks.get(index).isEmpty()) {
                    stacks.remove(index);
                    nonFullStacks.remove(index);
                    index--;
                }

                if (index >= 0) {
                    nonFullStacks.add(index);
                }
                return result;
            }
        }
    }
}

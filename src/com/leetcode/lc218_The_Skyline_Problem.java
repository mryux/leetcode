package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.*;

public class lc218_The_Skyline_Problem {
    class HeightInfo {
        public int x;
        public int height;
        public boolean positive;

        public HeightInfo(int x, int h, boolean p) {
            this.x = x;
            this.height = h;
            this.positive = p;
        }
    }

    public List<List<Integer>> getSkyline(int[][] buildings) {
        HeightInfo[] heights = new HeightInfo[buildings.length*2];

        for (int i = 0; i < buildings.length; i++) {
            int[] b = buildings[i];
            int l = b[0];
            int r = b[1];
            int height = b[2];

            heights[i*2] = new HeightInfo(l, height, true);
            heights[i*2+1] = new HeightInfo(r, height, false);
        }

        Arrays.sort(heights, (h1, h2)-> {
            return h1.x - h2.x;
        });

        HeapImpl heap = new HeapImpl();
        List<List<Integer>> ans = new ArrayList<>();
        int lastHeight = -1;
        int idx = 0;

        while (idx < heights.length) {
            HeightInfo info = heights[idx];

            while (idx < heights.length && (heights[idx].x == info.x)) {
                info = heights[idx];

                if (!info.positive)
                    heap.remove(info.height);
                else
                    heap.add(info.height);
                idx++;
            }

            int currHeight = heap.max();

            if (lastHeight != currHeight) {
                ans.add(List.of(info.x, currHeight));
                lastHeight = currHeight;
            }
        }

        return ans;
    }

    private class HeapImpl {
        List<Integer> list = new ArrayList<>();
        Map<Integer, Integer> indexMap = new HashMap<>();
        Map<Integer, Integer> countMap = new HashMap<>();

        public void add(int val) {
            if (indexMap.containsKey(val)) {
                countMap.put(val, countMap.get(val)+1);
                return;
            }

            list.add(val);
            countMap.put(val, 1);

            int idx = list.size()-1;
            indexMap.put(val, idx);

            while (idx > 0) {
                int parent = (idx-1)/2;

                if (list.get(parent) >= list.get(idx))
                    break;

                swap(parent, idx);
                idx = parent;
            }
        }

        public void remove(int val) {
            if (!indexMap.containsKey(val))
                return;

            if (countMap.get(val) > 1) {
                countMap.put(val, countMap.get(val)-1);
                return;
            }

            int idx = indexMap.get(val);
            int size = list.size();

            swap(idx, --size);
            indexMap.remove(val);
            countMap.remove(val);
            list.remove(size);

            while (idx < size) {
                int lchild = idx*2+1;
                int rchild = lchild+1;
                int maxChild = lchild;

                if (lchild >= size)
                    break;

                if (rchild >= size)
                    maxChild = lchild;
                else
                    maxChild = list.get(lchild) > list.get(rchild) ? lchild : rchild;

                if (list.get(idx) >= list.get(maxChild))
                    break;

                swap(idx, maxChild);
                idx = maxChild;
            }
        }

        public int max() {
            if (list.size() == 0)
                return 0;

            return list.get(0);
        }

        private void swap(int i1, int i2) {
            int v1 = list.get(i1);
            int v2 = list.get(i2);

            indexMap.put(v1, i2);
            indexMap.put(v2, i1);
            list.set(i1, v2);
            list.set(i2, v1);
        }
    }

    @Test
    public void test01() {
        List<List<Integer>> ans = getSkyline(new int[][]{{2,9,10},{3,7,15},{5,12,12},{15,20,10},{19,24,8}});

        Assertions.assertArrayEquals(new int[]{2,10}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{3,15}, Common.toArray(ans.get(1)));
        Assertions.assertArrayEquals(new int[]{7,12}, Common.toArray(ans.get(2)));
        Assertions.assertArrayEquals(new int[]{12,0}, Common.toArray(ans.get(3)));
        Assertions.assertArrayEquals(new int[]{15,10}, Common.toArray(ans.get(4)));
        Assertions.assertArrayEquals(new int[]{20,8}, Common.toArray(ans.get(5)));
        Assertions.assertArrayEquals(new int[]{24,0}, Common.toArray(ans.get(6)));
    }

    @Test
    public void test02() {
        List<List<Integer>> ans = getSkyline(new int[][]{{0,2,3},{2,5,3}});

        Assertions.assertArrayEquals(new int[]{0,3}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{5,0}, Common.toArray(ans.get(1)));
    }

    @Test
    public void test03() {
        List<List<Integer>> ans = getSkyline(new int[][]{{0,3,3},{1,5,3},{2,4,3},{3,7,3}});

        Assertions.assertArrayEquals(new int[]{0,3}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{7,0}, Common.toArray(ans.get(1)));
    }

    @Test
    public void test04() {
        List<List<Integer>> ans = getSkyline(new int[][]{
                {1544,231205,376017},{5998,879527,609178},{6145,335251,620022},{8399,852423,441500},{13985,246050,198772},
                {17311,384461,443954},{19857,545024,924768},{24545,512360,671435},{24637,437312,81524},{34431,410117,572660},
                {41956,357203,120663},{42582,439404,999964},{45017,495896,997381},{59554,425295,694713},{59749,675665,373880},
                {64004,876809,401511},{68655,609957,925100},{69029,470794,244859},{72917,320609,664013},{90240,858350,481221},
                {98512,257365,34944},{98897,617561,110896},{106386,757473,895370},{138505,778541,892102},{142803,482559,915161},
                {144757,224108,307402},{148962,798847,298420},{153916,608978,659252},{159078,780631,595138},{162335,641684,984370},
                {169278,520201,615623},{170632,951441,154717},{172091,990955,831470},{177118,614232,815698},{177303,792498,92042},
                {196888,223796,887790},{200374,903736,16440},{211537,988567,863931},{231437,458146,898771},{239972,855083,850367},
                {247798,704768,652870},{257551,569479,328756},{261295,627086,545380},{262981,826808,237936},{267002,438552,871785},
                {271467,592302,176734},{279209,880256,370636},{290082,738236,759585},{308221,553121,306184},{327430,380670,616140},
                {329228,579494,219292},{335392,940063,872524},{342607,789259,647865},{343982,610053,473003},{351572,710271,234191},
                {355001,378517,833822},{356988,988350,98517},{373321,727994,205027},{399866,834707,234731},{409207,934136,596974},
                {412413,593104,628468},{421212,864982,982652},{442333,923180,176317},{442946,801401,799978},{447411,715053,155265},
                {461090,933427,360992},{471549,665260,955076},{499921,587218,130159},{502274,906023,272847},{505897,821494,809118},
                {512428,705462,965658},{535788,541703,92423},{555687,960113,583425},{564227,805666,405427},{575074,612835,899325},
                {629330,797045,84784},{629718,828687,181137},{640143,711963,50666},{643572,832599,301279},{693513,835058,190184},
                {707821,925059,8606},{720443,971000,127523},{738423,953410,112865},{819134,914252,730415}
        });

        Assertions.assertArrayEquals(new int[]{1544,376017}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{5998,609178}, Common.toArray(ans.get(1)));
        Assertions.assertArrayEquals(new int[]{6145,620022}, Common.toArray(ans.get(2)));
        Assertions.assertArrayEquals(new int[]{19857,924768}, Common.toArray(ans.get(3)));
        Assertions.assertArrayEquals(new int[]{42582,999964}, Common.toArray(ans.get(4)));
        Assertions.assertArrayEquals(new int[]{439404,997381}, Common.toArray(ans.get(5)));
        Assertions.assertArrayEquals(new int[]{495896,984370}, Common.toArray(ans.get(6)));
        Assertions.assertArrayEquals(new int[]{641684,982652}, Common.toArray(ans.get(7)));
        Assertions.assertArrayEquals(new int[]{864982,872524}, Common.toArray(ans.get(8)));
        Assertions.assertArrayEquals(new int[]{940063,863931}, Common.toArray(ans.get(9)));
        Assertions.assertArrayEquals(new int[]{988567,831470}, Common.toArray(ans.get(10)));
        Assertions.assertArrayEquals(new int[]{990955,0}, Common.toArray(ans.get(11)));
    }

    @Test
    public void test05() {
        List<List<Integer>> ans = getSkyline(new int[][]{{1,2,1},{1,2,2},{1,2,3}});

        Assertions.assertArrayEquals(new int[]{1,3}, Common.toArray(ans.get(0)));
        Assertions.assertArrayEquals(new int[]{2,0}, Common.toArray(ans.get(1)));
    }
}

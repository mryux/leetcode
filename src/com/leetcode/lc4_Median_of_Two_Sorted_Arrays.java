package com.leetcode;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc4_Median_of_Two_Sorted_Arrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int size = nums1.length + nums2.length;
        boolean even = (size & 1) == 0;

        if (nums1.length != 0 && nums2.length != 0) {
            if (even)
                return (double)((findKthNum(nums1, nums2, size/2) + findKthNum(nums1, nums2, size/2+1)))/2;
            return findKthNum(nums1, nums2, size/2+1);
        }

        if (nums1.length != 0) {
            if (even)
                return (double) (nums1[(size-1)/2] + nums1[size/2])/2;
            return nums1[size/2];
        }

        if (nums2.length != 0) {
            if (even)
                return (double) (nums2[(size-1)/2] + nums2[size/2])/2;
            return nums2[size/2];
        }

        return 0;
    }

    private double findKthNum(int[] arr1, int[] arr2, int kth) {
        int[] longs = arr1.length > arr2.length ? arr1 : arr2;
        int[] shorts = arr1.length > arr2.length ? arr2 : arr1;
        int l = longs.length;
        int s = shorts.length;

        if (kth <= s)
            return getUpMedian(shorts, 0, kth-1, longs, 0, kth-1);

        if (kth > l) {
            if (shorts[kth-l-1] >= longs[l-1])
                return shorts[kth-l-1];
            if (longs[kth-s-1] >= shorts[s-1])
                return longs[kth-s-1];
            return getUpMedian(shorts, kth-l, s-1, longs, kth-s, l-1);
        }

        if (longs[kth-s-1] >= shorts[s-1])
            return longs[kth-s-1];
        return getUpMedian(shorts, 0, s-1, longs, kth-s, kth-1);
    }
    /*
    e1-s1 must equals e2-s2
     */
    private int getUpMedian(int[] a1, int s1, int e1, int[] a2, int s2, int e2) {
        if (e1-s1 != e2-s2) {
            throw new RuntimeException("args not valid.");
        }

        int mid1 = 0;
        int mid2 = 0;
        int offset = 0;

        while (s1 < e1) {
            mid1 = (s1 + e1) / 2;
            mid2 = (s2 + e2) / 2;
            offset = ((e1-s1+1) & 1) ^ 1;
            if (a1[mid1] > a2[mid2]) {
                e1 = mid1;
                s2 = mid2 + offset;
            } else if (a1[mid1] < a2[mid2]) {
                s1 = mid1 + offset;
                e2 = mid2;
            } else {
                return a1[mid1];
            }
        }

        return Math.min(a1[s1], a2[s2]);
    }

    /*
    another implementation.
     */
    public double findMedianSortedArrays01(int[] nums1, int[] nums2) {
        int m = nums1.length, n = nums2.length, left = (m + n + 1) / 2, right = (m + n + 2) / 2;
        return (findKth(nums1, 0, nums2, 0, left) + findKth(nums1, 0, nums2, 0, right)) / 2.0;
    }

    int findKth(int[] nums1, int i, int[] nums2, int j, int k) {
        if (i >= nums1.length) return nums2[j + k - 1];
        if (j >= nums2.length) return nums1[i + k - 1];

        if (k == 1) return Math.min(nums1[i], nums2[j]);
        int midVal1 = (i + k / 2 - 1 < nums1.length) ? nums1[i + k / 2 - 1] : Integer.MAX_VALUE;
        int midVal2 = (j + k / 2 - 1 < nums2.length) ? nums2[j + k / 2 - 1] : Integer.MAX_VALUE;
        if (midVal1 < midVal2) {
            return findKth(nums1, i + k / 2, nums2, j, k - k / 2);
        } else {
            return findKth(nums1, i, nums2, j + k / 2, k - k / 2);
        }
    }

    @Test
    public void test01() {
        lc4_Median_of_Two_Sorted_Arrays solu = new lc4_Median_of_Two_Sorted_Arrays();

        Assertions.assertEquals(2.0f, solu.findMedianSortedArrays(new int[]{1,3}, new int[]{2}));
        Assertions.assertEquals(2.5f, solu.findMedianSortedArrays(new int[]{1,2}, new int[]{3,4}));
    }
}

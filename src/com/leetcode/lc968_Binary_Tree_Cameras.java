package com.leetcode;

import com.leetcode.linkedlist.TreeNode;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class lc968_Binary_Tree_Cameras {

    class Info {
        public int noCover;
        public int coveredNoCamera;
        public int coveredHasCamera;

        public Info(int noCover, int coveredNoCamera, int coveredHasCamera) {
            this.noCover = noCover;
            this.coveredNoCamera = coveredNoCamera;
            this.coveredHasCamera = coveredHasCamera;
        }
    }
    public int minCameraCover(TreeNode root) {
        Info info = visit(root);

        return Math.min(info.noCover+1, Math.min(info.coveredNoCamera, info.coveredHasCamera));
    }

    private Info visit(TreeNode node) {
        if (node == null)
            return new Info(9999, 0, 9999);

        Info li = visit(node.left);
        Info ri = visit(node.right);

        int noCover = li.coveredNoCamera + ri.coveredNoCamera;
        int coveredNoCamera = Math.min(li.coveredHasCamera+ri.coveredHasCamera,
                Math.min(li.coveredNoCamera+ri.coveredHasCamera, li.coveredHasCamera+ri.coveredNoCamera));
        int coveredHasCamera = Math.min(li.noCover, Math.min(li.coveredNoCamera, li.coveredHasCamera))
                + Math.min(ri.noCover, Math.min(ri.coveredNoCamera, ri.coveredHasCamera))
                + 1;

        return new Info(noCover, coveredNoCamera, coveredHasCamera);
    }

    @Test
    public void test01() {
        lc968_Binary_Tree_Cameras solu = new lc968_Binary_Tree_Cameras();

        Assertions.assertEquals(1, solu.minCameraCover(Common.deserialize(new Integer[]{0,0,null,0,0})));
        Assertions.assertEquals(2, solu.minCameraCover(Common.deserialize(new Integer[]{0,0,null,0,null,0,null,null,0})));
        Assertions.assertEquals(1, solu.minCameraCover2(Common.deserialize(new Integer[]{0,0,null,0,0})));
        Assertions.assertEquals(2, solu.minCameraCover2(Common.deserialize(new Integer[]{0,0,null,0,null,0,null,null,0})));
    }

    public int minCameraCover2(TreeNode root) {
        Data info = process2(root);

        return info.cameras + (info.status == Status.UNCOVERED ? 1 : 0);
    }

    // 以x为头，x下方的节点都是被covered，x自己的状况，分三种
    public static enum Status {
        UNCOVERED, COVERED_NO_CAMERA, COVERED_HAS_CAMERA
    }

    // 以x为头，x下方的节点都是被covered，得到的最优解中：
    // x是什么状态，在这种状态下，需要至少几个相机
    public class Data {
        public Status status;
        public int cameras;

        public Data(Status status, int cameras) {
            this.status = status;
            this.cameras = cameras;
        }
    }

    public Data process2(TreeNode X) {
        if (X == null) {
            return new Data(Status.COVERED_NO_CAMERA, 0);
        }

        Data left = process2(X.left);
        Data right = process2(X.right);
        int cameras = left.cameras + right.cameras;

        // 左、或右，哪怕有一个没覆盖
        if (left.status == Status.UNCOVERED || right.status == Status.UNCOVERED) {
            return new Data(Status.COVERED_HAS_CAMERA, cameras + 1);
        }

        // 左右孩子，不存在没被覆盖的情况
        if (left.status == Status.COVERED_HAS_CAMERA || right.status == Status.COVERED_HAS_CAMERA) {
            return new Data(Status.COVERED_NO_CAMERA, cameras);
        }
        // 左右孩子，不存在没被覆盖的情况，也都没有相机
        return new Data(Status.UNCOVERED, cameras);
    }
}

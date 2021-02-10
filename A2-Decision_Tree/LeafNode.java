public class LeafNode {
    private LeafNode left;
    private LeafNode right;
    private String lable;
    /**
     * 0: WC_TA
     * 1: RE_TA
     * 2: EBIT_TA
     * 3: MVE_BVTD
     * 4: S_TA
     */
    private int attr;
    private double splitval;

    public LeafNode getLeft() {
        return left;
    }

    public void setLeft(LeafNode left) {
        this.left = left;
    }

    public LeafNode getRight() {
        return right;
    }

    public void setRight(LeafNode right) {
        this.right = right;
    }

    public String getLable() {
        return lable;
    }

    public void setLable(String lable) {
        this.lable = lable;
    }

    public int getAttr() {
        return attr;
    }

    public void setAttr(int attr) {
        this.attr = attr;
    }

    public double getSplitval() {
        return splitval;
    }

    public void setSplitval(double splitval) {
        this.splitval = splitval;
    }
}

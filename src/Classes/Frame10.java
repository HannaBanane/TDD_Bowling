package Classes;

public class Frame10 extends Frame{
    private int wurf3;

    public Frame10(int wurf1, int wurf2, int wurf3) {
        super(wurf1, wurf2);
        this.wurf3 = wurf3;
    }

    public int getWurf3() {
        return wurf3;
    }

    @Override
    public int baseScore() {
        return super.baseScore() + wurf3;
    }
}

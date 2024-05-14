package Classes;

public class Frame {
    private int wurf1;
    private int wurf2;

    public Frame (int wurf1, int wurf2){
        this.wurf1 = wurf1;
        this.wurf2 = wurf2;
    }

    public int getWurf2() {
        return wurf2;
    }

    public int getWurf1() {
        return wurf1;
    }

    public void setWurf1(int wurf1) {
        this.wurf1 = wurf1;
    }

    public void setWurf2(int wurf2) {
        this.wurf2 = wurf2;
    }

    public int baseScore(){
        return wurf1 + wurf2;
    }

    public boolean isSpare(){
        if (wurf1 == 10){
            return false;
        }
        return wurf1 + wurf2 == 10;
    }

    public boolean isStrike(){
        return wurf1 == 10;
    }

}

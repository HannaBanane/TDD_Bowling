package Classes;

import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Integer> rolls = new ArrayList<Integer>(21);

    public void addRoll(int pins){
        rolls.add(pins);
    }

    public Frame[] getFrame(){
        return null;
    }

    public int totalScore(){
        List<Frame> frames = buildFrames();
        int baseScore = calculateBaseScore(frames);
        int bonus = calculateBonusScore(frames);
        return baseScore + bonus;
    }

    public boolean isOver(){
        return false;
    }

    private List<Frame> buildFrames(){
        List<Frame> frames = new ArrayList<Frame>();
        for (int i = 0; i < rolls.size(); i++){
            if (rolls.get(i) == 10) {
                frames.add(new Frame(rolls.get(i), 0));
            } else {
                frames.add(new Frame(rolls.get(i), rolls.get(i+1)));
                i++;
            }
        }
        return frames;
    }

    private int calculateBaseScore(List<Frame> frames){
        return frames.stream().mapToInt(Frame::baseScore).sum();

        // oder mit Schleife (KISS - aber Teamabhängig)
        // int baseScore = 0;
        // for (Frame frame : frames){
        //      baseScore += frame.baseScore();
        // }
        // return baseScore;
    }

    private int calculateBonusScore(List<Frame> frames){
        int bonus = 0;
        for (int i = 0; i < frames.size() - 1; i++){
            Frame frame = frames.get(i);
            if (frame.isSpare()){
                bonus += frames.get(i + 1).getWurf1();
            } else if (frame.isStrike()){
                if (frames.get(i + 1).baseScore() == 10){
                    bonus += frames.get(i + 1).getWurf1() + frames.get(i + 2).getWurf1();
                } else {
                    bonus += frames.get(i + 1).baseScore();
                }
            }
        }
        return bonus;
    }
}

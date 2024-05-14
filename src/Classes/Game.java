package Classes;

import java.util.ArrayList;
import java.util.List;

public class Game {

    private List<Integer> rolls = new ArrayList<Integer>(21);

    public void addRoll(int pins){
        rolls.add(pins);
    }

    public Frame[] getFrame(){
        // TODO
        return null;
    }

    public int totalScore(){
        List<Frame> frames = buildFrames();
        int baseScore = calculateBaseScore(frames);
        int bonus = calculateBonusScore(frames);
        return baseScore + bonus;
    }

    public boolean isOver(){
        // TODO
        return rolls.size() >= 21;
    }

//    private List<Frame> buildFrames(){
//        List<Frame> frames = new ArrayList<Frame>();
//        for (int i = 0; i < rolls.size(); i++){
//            if (rolls.get(i) == 10) {
//                frames.add(new Frame(rolls.get(i), 0));
//            } else {
//                frames.add(new Frame(rolls.get(i), rolls.get(i+1)));
//                i++;
//            }
//        }
//        return frames;
//    }

    private List<Frame> buildFrames() {
        List<Frame> frames = new ArrayList<Frame>();
        int frameCount = 1;
        for (int i = 0; i < rolls.size(); i++) {
            if (rolls.get(i) == 10) {
                if (frameCount == 10) {
                    frames.add(new Frame10(rolls.get(i), rolls.get(i + 1), rolls.get(i + 2)));
                    i += 2;
                } else {
                    frames.add(new Frame(rolls.get(i), 0));
                    frameCount++;
                }
            } else {
                frames.add(new Frame(rolls.get(i), rolls.get(i + 1)));
                i++;
                frameCount++;
            }
        }
        return frames;
    }

    private int calculateBaseScore(List<Frame> frames){
        return frames.stream().mapToInt(Frame::baseScore).sum();

//         oder mit Schleife (KISS - aber Teamabhängig)
//         int baseScore = 0;
//         for (Frame frame : frames){
//              baseScore += frame.baseScore();
//         }
//         return baseScore;
    }

//    private int calculateBonusScore(List<Frame> frames){
//        int bonus = 0;
//        for (int i = 0; i < frames.size() - 1; i++){
//            Frame frame = frames.get(i);
//            if (frame.isSpare()){
//                bonus += frames.get(i + 1).getWurf1();
//            } else if (frame.isStrike()){
//                if (frames.get(i + 1).baseScore() == 10){
//                    bonus += frames.get(i + 1).getWurf1() + frames.get(i + 2).getWurf1();
//                } else {
//                    bonus += frames.get(i + 1).baseScore();
//                }
//            }
//        }
//        return bonus;
//    }

    private int calculateBonusScore(List<Frame> frames) {
        int bonusScore = 0;
        for (int i = 0; i < frames.size() - 1; i++) {
            Frame frame = frames.get(i);
            if (frame.isStrike()) {
                bonusScore = calculateStrikeBonus(frames, bonusScore, i);
            } else if (frame.isSpare()) {
                bonusScore += frames.get(i + 1).getWurf1();
            }
        }
        return bonusScore;
    }

    private int calculateStrikeBonus(List<Frame> frames, int bonusScore, int i) {
        if (i==8) {
            bonusScore += frames.get(i + 1).getWurf1()+frames.get(i + 1).getWurf2();
        }else if (frames.get(i + 1).getWurf1() == 10) {
            bonusScore += frames.get(i + 1).getWurf1() + frames.get(i + 2).getWurf1();
        } else {
            bonusScore += frames.get(i + 1).baseScore();
        }
        return bonusScore;
    }
}

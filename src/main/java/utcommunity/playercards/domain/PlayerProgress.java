package utcommunity.playercards.domain;

public class PlayerProgress {
    private int xpInThisLevel;
    private int xpToNextLevel;
    private int yellowStars;
    private int blueStars;

    public int getXpInThisLevel() {
        return xpInThisLevel;
    }

    public void setXpInThisLevel(int xpInThisLevel) {
        this.xpInThisLevel = xpInThisLevel;
    }

    public int getXpToNextLevel() {
        return xpToNextLevel;
    }

    public void setXpToNextLevel(int xpToNextLevel) {
        this.xpToNextLevel = xpToNextLevel;
    }

    public int getYellowStars() {
        return yellowStars;
    }

    public void setYellowStars(int yellowStars) {
        this.yellowStars = yellowStars;
    }

    public int getBlueStars() {
        return blueStars;
    }

    public void setBlueStars(int blueStars) {
        this.blueStars = blueStars;
    }
}

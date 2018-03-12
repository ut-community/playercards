package utcommunity.playercards.domain;

public class PlayerExperience {

    private int level               = 1;
    private int totalXp             = 0;

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }
}

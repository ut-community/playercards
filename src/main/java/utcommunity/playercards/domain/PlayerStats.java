package utcommunity.playercards.domain;

import java.util.ArrayList;
import java.util.List;

public class PlayerStats {

    private String id;
    private String name;
    private String country;
    private List<PlayerStat> stats = new ArrayList<>();
    private int level;
    private int totalXp;
    private int progressInThisLevel;
    private int progressToNextLevel;
    private int yellowStars;
    private int blueStars;

    public PlayerStats(String id) {
       setId(id);
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getTotalXp() {
        return totalXp;
    }

    public void setTotalXp(int totalXp) {
        this.totalXp = totalXp;
    }

    public int getProgressInThisLevel() {
        return progressInThisLevel;
    }

    public void setProgressInThisLevel(int progressInThisLevel) {
        this.progressInThisLevel = progressInThisLevel;
    }

    public int getProgressToNextLevel() {
        return progressToNextLevel;
    }

    public void setProgressToNextLevel(int progressToNextLevel) {
        this.progressToNextLevel = progressToNextLevel;
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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<PlayerStat> getStats() {
        return stats;
    }

    public void setStats(List<PlayerStat> stats) {
        this.stats = stats;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

package utcommunity.playercards.domain;

public class PlayerStat {

    private StatGametype gametype;
    private StatLevel    level;
    private int          rating;
    private int          elo;

    public StatGametype getGametype() {
        return gametype;
    }

    public void setGametype(StatGametype gametype) {
        this.gametype = gametype;
    }

    public StatLevel getLevel() {
        return level;
    }

    public void setLevel(StatLevel level) {
        this.level = level;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public int getElo() {
        return elo;
    }

    public void setElo(int elo) {
        this.elo = elo;
    }
}

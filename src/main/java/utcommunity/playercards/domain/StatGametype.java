package utcommunity.playercards.domain;

public enum StatGametype {
    SHOWDOWN("showdown"),
    BLITZ("blitz"),
    CTF("capture the flag"),
    DUEL("duel"),
    TDM("team deathmatch"),
    DM("deathmatch");

    String name;

    StatGametype(String name) {
        this.name = name;
    }

    public static StatGametype get(String name) {
        name = name.toLowerCase();
        for (StatGametype statGametype : StatGametype.values()) {
            if (statGametype.name.equals(name)) {
                return statGametype;
            }
        }
        throw new IllegalArgumentException("Gametype not found.");
    }
}

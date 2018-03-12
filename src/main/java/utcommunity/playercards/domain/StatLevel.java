package utcommunity.playercards.domain;

public enum StatLevel {
    GREEN("level0"),
    BRONZE("level1"),
    SILVER("level2"),
    GOLD("level3");

    String name;

    StatLevel(String name) {
        this.name = name;
    }

    public static StatLevel get(String name) {
        name = name.toLowerCase();
        for (StatLevel statGametype : StatLevel.values()) {
            if (statGametype.name.equals(name)) {
                return statGametype;
            }
        }
        throw new IllegalArgumentException("StatLevel not found.");
    }
}

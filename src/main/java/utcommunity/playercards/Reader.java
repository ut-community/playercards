package utcommunity.playercards;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;
import utcommunity.playercards.domain.PlayerStat;
import utcommunity.playercards.domain.PlayerStats;
import utcommunity.playercards.domain.StatGametype;
import utcommunity.playercards.domain.StatLevel;

@Component
public class Reader {

    //8fe861e91e8d4303913f23734d9636b7 - random person
    //9b272cfabbbe4d84983ba2dc317452b8  - skandalouz

    //https://www.epicgames.com/unrealtournament/playerCard?playerId=de60fb6009a24775846326574e49ce3c
    private String TEMPLATE = "https://www.epicgames.com/unrealtournament/playerCard?playerId=";

    public Document getDocument(String id) {
        try {
            String endpoint = TEMPLATE + id;
            return Jsoup.connect(endpoint).get();
        } catch (Exception ex) {
            return null;
        }
    }

    public PlayerStats readId(String id) {
        PlayerStats stats = new PlayerStats(id);
        Document    doc   = getDocument(id);

        if (doc == null) {
            return stats;
        }

        tryAndCatch(() -> stats.setName(readName(doc)));
        tryAndCatch(() -> stats.setCountry(readCountry(doc)));
        tryAndCatch(() -> stats.setLevel(readLevel(doc)));
        tryAndCatch(() -> stats.setTotalXp(readTotalXp(doc)));
        tryAndCatch(() -> stats.setProgressInThisLevel(readProgressInThisLevel(doc)));
        tryAndCatch(() -> stats.setProgressToNextLevel(readProgressToNextLevel(doc)));
        tryAndCatch(() -> stats.setBlueStars(readBlueStars(doc)));
        tryAndCatch(() -> stats.setYellowStars(readYellowStars(doc)));
        tryAndCatch(() -> stats.getStats().addAll(readPlayerStats(doc)));

        return stats;
    }

    //Try to read the document, if it fails at any step, too bad
    public void tryAndCatch(Runnable consumer) {
        try {
            consumer.run();
        } catch (Exception ex) {
            //do nothing
        }
    }

    public String readName(Document doc) {
        return doc.select(".playerHeader .headerRow").get(0).text();
    }

    public String readCountry(Document doc) {
        return doc.select(".playerHeader .headerRow").get(1).text();
    }

    public int readLevel(Document doc) {
        String level = doc.select(".xpLevelClass").get(0).text();
        return Integer.parseInt(level);
    }

    public int readTotalXp(Document doc) {
        String xp = remove(
            doc.select(".playerProgress .rankRow").get(0).select(">div").get(2).text(),
            "(", ",", " XP Total)"
        );
        return Integer.parseInt(xp);
    }

    public int readProgressInThisLevel(Document doc) {
        String text = doc.select(".playerProgress .rankRow").get(1).select(".xpDataLabel").get(0).text();
        return Integer.parseInt(text);
    }

    public int readProgressToNextLevel(Document doc) {
        String text = doc.select(".playerProgress .rankRow").get(1).select(".xpDataLabel").get(1).text();
        return Integer.parseInt(text);
    }

    public int readYellowStars(Document doc) {
        String text = doc.select(".playerProgress .rankRow").get(2).select(".challengeValue").get(0).text();
        return Integer.parseInt(text);
    }

    public int readBlueStars(Document doc) {
        String text = doc.select(".playerProgress .rankRow").get(2).select(".challengeValue").get(1).text();
        return Integer.parseInt(text);
    }

    private List<PlayerStat> readPlayerStats(Document doc) {
        List<PlayerStat> list = new ArrayList<>();
        for (Element rankRow : doc.select(".playerRank .rankRow")) {
            PlayerStat playerStat = new PlayerStat();
            playerStat.setGametype(readStatGametype(rankRow));
            playerStat.setLevel(readStatLevel(rankRow));
            playerStat.setRating(readRating(rankRow));
            playerStat.setElo(readElo(rankRow));
            list.add(playerStat);
        }
        return list;
    }

    private StatGametype readStatGametype(Element rankRow) {
        Element element = rankRow.select(".statColumn").get(0);
        String  text    = element.text();
        text = text.replace(" Rank:", "");
        return StatGametype.get(text);
    }

    private StatLevel readStatLevel(Element rankRow) {
        Element element = rankRow.select(".levelColumn .levelClass").get(0);
        String  level   = element.className().split(" ")[1];
        return StatLevel.get(level);
    }

    private int readRating(Element rankRow) {
        Element element = rankRow.select(".levelColumn .skillRatingLevel").get(0);
        String  text    = element.text();
        return new BigDecimal(text)
            .setScale(0, RoundingMode.HALF_EVEN)
            .intValue();
    }

    private int readElo(Element rankRow) {
        Element element = rankRow.select(".rankColumn").get(0);
        String  text    = element.text();
        if (text.toLowerCase().contains("less than")) {
            return 0;
        }
        String intText = remove(text, "(", ")", ",");
        return Integer.parseInt(intText);
    }

    private String remove(String string, String... toRemove) {
        for (String s : toRemove) {
            string = string.replace(s, "");
        }
        return string;
    }
}

package utcommunity.playercards;

import org.jsoup.nodes.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import utcommunity.playercards.domain.PlayerStats;

import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Controller
public class PlayerCardsController {

    @Autowired
    private Reader reader;

    @GetMapping(path = {"/", "/{optionalId}"})
    public String page(Map<String, Object> model, @PathVariable Optional<String> optionalId, @RequestParam Optional<String> playerId) throws IOException {
        optionalId.ifPresent(id -> model.put("id", id));
        playerId.ifPresent(id -> model.put("id", id));
        if (model.containsKey("id")) {
            Document document = reader.getDocument((String) model.get("id"));
            try {
                model.put("name", reader.readName(document));
            } catch (Exception ex) {
            }
        }
        return "test";
    }

    @GetMapping("/{id}/json")
    @ResponseBody
    public PlayerStats id(@PathVariable String id) throws IOException {
        return reader.readId(id);
    }
}

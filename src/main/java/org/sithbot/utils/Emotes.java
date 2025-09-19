package org.sithbot.utils;

import java.util.HashMap;
import java.util.Map;

public class Emotes {

    private final Map<String, String> cardEmotes;

    public Emotes() {
        cardEmotes = new HashMap<>();

        // Spades (b for black)
        cardEmotes.put("2b", "<:2b:123456789012345678>");
        cardEmotes.put("3b", "<:3b:123456789012345679>");
        cardEmotes.put("4b", "<:4b:123456789012345680>");
        cardEmotes.put("5b", "<:5b:123456789012345681>");
        cardEmotes.put("6b", "<:6b:123456789012345682>");
        cardEmotes.put("7b", "<:7b:123456789012345683>");
        cardEmotes.put("8b", "<:8b:123456789012345684>");
        cardEmotes.put("9b", "<:9b:123456789012345685>");
        cardEmotes.put("10b", "<:10b:123456789012345686>");
        cardEmotes.put("Jb", "<:Jb:123456789012345687>");
        cardEmotes.put("Qb", "<:Qb:123456789012345688>");
        cardEmotes.put("Kb", "<:Kb:123456789012345689>");
        cardEmotes.put("Ab", "<:Ab:123456789012345690>");

        // Hearts (r for red)
        cardEmotes.put("2r", "<:2r:123456789012345691>");
        cardEmotes.put("3r", "<:3r:123456789012345692>");
        cardEmotes.put("4r", "<:4r:123456789012345693>");
        cardEmotes.put("5r", "<:5r:123456789012345694>");
        cardEmotes.put("6r", "<:6r:123456789012345695>");
        cardEmotes.put("7r", "<:7r:123456789012345696>");
        cardEmotes.put("8r", "<:8r:123456789012345697>");
        cardEmotes.put("9r", "<:9r:123456789012345698>");
        cardEmotes.put("10r", "<:10r:123456789012345699>");
        cardEmotes.put("Jr", "<:Jr:123456789012345700>");
        cardEmotes.put("Qr", "<:Qr:123456789012345701>");
        cardEmotes.put("Kr", "<:Kr:123456789012345702>");
        cardEmotes.put("Ar", "<:Ar:123456789012345703>");
    }

    /**
     * Returns the custom emoji mention for the given card.
     * Falls back to ❔ if card is not found.
     */
    public String getEmote(Object card) {
        return cardEmotes.getOrDefault(card.toString(), "❔");
    }

    /**
     * Optional helper to get Unicode emoji for actions
     */
    public static String getActionEmote(String action) {
        return switch (action.toLowerCase()) {
            case "hit" -> "✅";
            case "stand" -> "❌";
            case "double" -> "💰";
            case "play" -> "▶️";
            default -> "❔";
        };
    }
}

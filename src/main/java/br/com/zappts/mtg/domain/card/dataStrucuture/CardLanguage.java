package br.com.zappts.mtg.domain.card.dataStrucuture;

public enum CardLanguage {

    PORTUGUESE("português"),

    ENGLISH("inglês"),

    JAPANESE("japones");

    private final String language;
    CardLanguage(String language) {
        this.language = language;
    }

    public String getLanguage() {
        return language;
    }

}

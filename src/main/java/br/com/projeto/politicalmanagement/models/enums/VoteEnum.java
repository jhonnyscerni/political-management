package br.com.projeto.politicalmanagement.models.enums;

public enum VoteEnum {
    CONQUISTADO("Conquistado"),
    A_CONQUISTAR("Á Conquistar"),
    PERDIDO("Perdido");

    private String name;

    VoteEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

package com.puzhen.nplxi;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Token {

    private Character character;
    private List<CharacterNOccurrence> characterNOccurrenceList = new ArrayList<>();
    private Map<Character, Integer> character2Index = new HashMap<>();

    public Token(Character character) {
        this.character = character;
    }

    public void addSuffix(Character suffix) {
        if (character2Index.containsKey(suffix)) {
            int index = character2Index.get(suffix);
            CharacterNOccurrence characterNOccurrence = characterNOccurrenceList.get(index);
            characterNOccurrence.addOneOccurrence();
        } else {
            // create a new CharacterNOccurrence
            CharacterNOccurrence characterNOccurrence = new CharacterNOccurrence();
            characterNOccurrence.setCharacter(suffix);
            characterNOccurrence.setOccurrence(1);
            characterNOccurrenceList.add(characterNOccurrence);
            character2Index.put(suffix, characterNOccurrenceList.size() - 1);
        }
    }

    public List<CharacterNOccurrence> getCharacterNOccurrenceList() {
        return characterNOccurrenceList;
    }
}

class CharacterNOccurrence {
    @Override
    public String toString() {
        return "CharacterNOccurrence{" +
                "character=" + character +
                ", occurrence=" + occurrence +
                '}';
    }

    private Character character;
    private int occurrence;

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public int getOccurrence() {
        return occurrence;
    }

    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    public void addOneOccurrence() {
        occurrence++;
    }
}

package com.puzhen.nplxi;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("I'm studying President Xi's speech");
        BufferedReader rd = new BufferedReader(new FileReader(new File("xi's-speech.txt")));
        String line;
        // a special character is return
        Token tokenForReturn = new Token('\n');
        map.put('\n', tokenForReturn);
        while ((line = rd.readLine()) != null) {
            if (line.length() < 1) continue;
            char veryFirstCharacter = line.charAt(0);
            tokenForReturn.addSuffix(veryFirstCharacter);
            for (int i = 1; i < line.length(); i++) {
                char firstCharacter = line.charAt(i - 1);
                char secondCharacter = line.charAt(i);
                if (map.containsKey(firstCharacter)) {
                    map.get(firstCharacter).addSuffix(secondCharacter);
                } else {
                    Token token = new Token(firstCharacter);
                    token.addSuffix(secondCharacter);
                    map.put(firstCharacter, token);
                }
                if (i == line.length() - 1) {
                    // add return to the token
                    if (map.containsKey(secondCharacter)) {
                        map.get(secondCharacter).addSuffix('\n');
                    } else {
                        Token token = new Token(secondCharacter);
                        token.addSuffix('\n');
                        map.put(secondCharacter, token);
                    }
                }
            }
            System.out.println(line);
        }
        for (Character character : map.keySet()) {
            Token token = map.get(character);
            System.out.println("For " + character + "\n" + token.getCharacterNOccurrenceList().toString());
        }

        // write an article here
        Character character = '国';
        for (int i = 0; i < 1000; i++) {
            System.out.print(character);
            character = getNextCharacter(character);
        }
    }

    private static Character getNextCharacter(Character character) {
        if (!map.containsKey(character)) {
            System.err.println("No character in map: [" + character + "].");
        }
        Token token = map.get(character);
        List<CharacterNOccurrence> characterNOccurrenceList = token.getCharacterNOccurrenceList();
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < characterNOccurrenceList.size(); i++) {
            CharacterNOccurrence characterNOccurrence = characterNOccurrenceList.get(i);
            Character character1 = characterNOccurrence.getCharacter();
            for (int j = 0; j < characterNOccurrence.getOccurrence(); j++)
                list.add(character1);
        }
        // randomly choose it
        int randomInt = (new Random()).nextInt(list.size());
        return list.get(randomInt);
    }

    private static Map<Character, Token> map = new HashMap<>();
}

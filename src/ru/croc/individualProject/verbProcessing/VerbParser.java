package ru.croc.individualProject.verbProcessing;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VerbParser {
    private final File source;

    public VerbParser (File source){
        this.source = source;
    }

    public List<Verb> parseToVerbs(){
        List<Verb> verbs = new ArrayList<>();
        try(Scanner scanner = new Scanner(this.source)){
            while (scanner.hasNextLine()){
                String[] forms = scanner.nextLine().split(";");
                verbs.add(new Verb(forms[0],forms[1],forms[2]));
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        return verbs;
    }

}

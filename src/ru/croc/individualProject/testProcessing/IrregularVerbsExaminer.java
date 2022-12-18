package ru.croc.individualProject.testProcessing;

import ru.croc.individualProject.verbProcessing.Verb;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class IrregularVerbsExaminer implements Examiner{

    private final Verb verb;
    private final int difficultyLevel;

    public IrregularVerbsExaminer(Verb verb,int difficultyLevel){
        this.difficultyLevel = difficultyLevel;
        this.verb = verb;
    }

    //формирование строки-задания в соответствии со сложностью
    public String verbFormToPrint(String form){
        String newForm = form;
        if(difficultyLevel != 3){
            Random random = new Random();
            int numberOfHints = form.length()/(difficultyLevel+1);
            IntStream stream = random.ints(0, form.length()-1);
            List<Integer> hintIndexes = stream.distinct().limit(numberOfHints).boxed().collect(Collectors.toList());
            for (int i = 0; i < form.length(); i++) {
                if(!hintIndexes.contains(i)){
                    newForm = newForm.substring(0,i)+"_"+newForm.substring(i+1);
                }
            }
        }else{
            newForm = newForm.replaceAll(".","_");
        }
        return newForm;
    }
    @Override
    public String generateQuestion() {
        return verb.getFirstForm()+" - "+ verbFormToPrint(verb.getSecondForm())+
                " - "+verbFormToPrint(verb.getThirdForm());
    }
    @Override
    public boolean answerIsCorrect(String answer) {
        answer = answer.replaceAll("\\s+"," ");
        String[] verbForms = answer.split(" ");
        return verbForms[0].equals(verb.getSecondForm()) &&
                verbForms[1].equals(verb.getThirdForm());

    }


}

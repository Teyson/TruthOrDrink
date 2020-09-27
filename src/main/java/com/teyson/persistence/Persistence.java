package com.teyson.persistence;

import com.google.gson.reflect.TypeToken;
import com.teyson.domain.Question;
import com.teyson.interfaces.IPersistence;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

import com.google.gson.Gson;

public class Persistence implements IPersistence {
    //Instance variable of singleton class
    private static Persistence instance;

    //Helper variables
    private FileWriter fileWriter;
    private final File questionFile;
    private final Gson gson = new Gson();

    //Actual variables
    private List<Question> allQuestions;
    private int highestId;

    public Persistence() {
        //HJÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆÆLP DET HER LORT VIRKER IKKE....
        this.questionFile = new File("src\\main\\resources\\com\\teyson\\persistence\\questions.json");
        this.allQuestions = loadQuestions();
        highestId = findHighestId();
    }

    public static Persistence getInstance() {
        if (instance == null) {
            instance = new Persistence();
        }
        return instance;
    }

    private int findHighestId() {
        int max;
        try {
            max = Collections.max(this.allQuestions).getId();
        }catch(NoSuchElementException e){
            max = -1;
        }
        return max;
    }

    public List<Question> loadQuestions() {
        try {
            BufferedReader reader = Files.newBufferedReader(questionFile.toPath());

            List<Question> questions = gson.fromJson(reader, new TypeToken<List<Question>>() {}.getType());

            reader.close();

            if(questions == null){
                return new ArrayList<Question>();
            }else {
                return questions;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public boolean addQuestion(Question q){
        //checks if the question is already in the list
        if(!allQuestions.contains(q)){
            //makes the json string from the object
            String json = gson.toJson(q);
            try {
                //Write object to file
                fileWriter = new FileWriter(questionFile, true);
                fileWriter.write(json);
                fileWriter.close();

                //Update the question list
                allQuestions.add(q);

                //update the highest id
                if(highestId < q.getId()){
                    highestId = q.getId();
                }

                return true;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public boolean removeQuestion(Question q){
        allQuestions.remove(q);

        //update highest id
        if(highestId == q.getId()){
            highestId = findHighestId();
        }

        return saveQuestions();
    }

    @Override
    public boolean saveQuestions() {
        try {
            fileWriter = new FileWriter(questionFile, false);
            for(Question q : allQuestions){
                fileWriter.write(gson.toJson(q));
            }
            return true;
        } catch (IOException e) {
            e.printStackTrace();

        }
        return false;
    }

    public List<Question> getAllQuestions() {
        loadQuestions();
        return allQuestions;
    }

    public int getHighestId() {
        return highestId;
    }
}

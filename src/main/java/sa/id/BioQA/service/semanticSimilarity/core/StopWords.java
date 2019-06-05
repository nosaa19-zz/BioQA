/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sa.id.BioQA.service.semanticSimilarity.core;

import sa.id.BioQA.BioQaApplication;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;

public class StopWords {
    
    public static HashSet<String> stopWords;
    public static String stopWordFile = BioQaApplication.class.getResource("/public/stopWords/stop.words.txt").toString();
    public BufferedReader buffer;
    public StopWords() throws FileNotFoundException, IOException{
        
        stopWords = new HashSet<String>();
        buffer = new BufferedReader(new FileReader(new File(stopWordFile)));
       fillStopWords();
        
    }
    
    public void fillStopWords() throws IOException{
    
        String line;
        while((line=buffer.readLine())!=null){
        
            String stop = line.replaceAll(" ", "");
            stopWords.add(stop);
        }
        
        buffer.close();
        
    }
    
    public HashSet<String> getStopWords() throws IOException{
         
        return this.stopWords;
    }
    
    
}

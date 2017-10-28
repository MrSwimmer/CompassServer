package com.serverforcompass;

import android.app.Application;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;


/**
 * Created by Севастьян on 08.10.2017.
 */

public class MyApp extends Application {
    private SharedPreferences mSettings;
    public static final String APP_PREFERENCES = "mysettings";
    private static MyApp singleton;
    public ArrayList<String> StackSkillsFront = new ArrayList<String>(), StackSkillsBack = new ArrayList<String>(), StackSkillsAndroid= new ArrayList<String>(),StackSkillsData= new ArrayList<String>(),StackSkillsGame= new ArrayList<String>(),StackSkillsQA= new ArrayList<String>(),StackSkillsIOS= new ArrayList<String>(), StackSkillsSQL= new ArrayList<String>();
    public ArrayList<Skill> CountFront = new ArrayList<Skill>();
    public ArrayList<Skill> CountBack = new ArrayList<Skill>();
    public ArrayList<Skill> CountAndr = new ArrayList<Skill>();
    public ArrayList<Skill> CountData = new ArrayList<Skill>();
    public ArrayList<Skill> CountGame = new ArrayList<Skill>();
    public ArrayList<Skill> CountQA = new ArrayList<Skill>();
    public ArrayList<Skill> CountIOS = new ArrayList<Skill>();
    public ArrayList<Skill> CountSQL = new ArrayList<Skill>();

    // Возвращает экземпляр данного класса
    public static MyApp getInstance() {
        return singleton;
    }

    @Override
    public final void onCreate() {
        super.onCreate();
        singleton = this;

        //progers
        GetStacksFromFile(StackSkillsFront,"frontend.txt");
        GetStacksFromFile(StackSkillsBack, "backend.txt");
        GetStacksFromFile(StackSkillsAndroid, "android.txt");
        GetStacksFromFile(StackSkillsData, "datascience.txt");
        GetStacksFromFile(StackSkillsGame, "game.txt");
        GetStacksFromFile(StackSkillsQA, "qa.txt");
        GetStacksFromFile(StackSkillsIOS, "ios.txt");
        GetStacksFromFile(StackSkillsSQL, "sql.txt");

        //mSettings = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE);
    }
    void GetStacksFromFile(ArrayList<String> stack, String text){
        AssetManager am = this.getAssets();
        InputStream is = null;
        try {
            is = am.open(text);
        } catch (IOException e) {
            e.printStackTrace();
        }
        BufferedReader r = new BufferedReader(new InputStreamReader(is));
        StringBuilder total = new StringBuilder();
        String line;
        try {
            while ((line = r.readLine()) != null) {
                total.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        String allStack = total.toString();
        Log.i("code", allStack);
        int pos=0;
        for(int i=0; i<allStack.length(); i++){
            if(allStack.charAt(i)==','){
                stack.add(allStack.substring(pos, i).toLowerCase());
                pos=i+1;
            }
        }
        Log.i("code","size stack "+ stack.size());
    }
}

package com.serverforcompass;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static String[] maskeys;
    public static Context context;
    Button Start;
    static int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        Start = (Button) findViewById(R.id.start);
        if(count==8){
            Toast.makeText(this, "Загрузка завершена", Toast.LENGTH_SHORT).show();
        }
        Start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                start(0);
                /*maskeys = new String[]{"frontend разработчик", "frontend", "developer"};
                LoaderBase loaderBaseFront = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsFront, MyApp.getInstance().CountFront);
                maskeys = new String[]{"backend разработчик", "backend", "developer"};
                LoaderBase loaderBaseBack = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsBack, MyApp.getInstance().CountBack);
                maskeys = new String[]{"android разработчик", "android", "developer"};
                LoaderBase loaderBaseAndr = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsAndroid, MyApp.getInstance().CountAndr);
                maskeys = new String[]{"data scientist", "data", "scien"};
                LoaderBase loaderBaseData = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsData, MyApp.getInstance().CountData);
                maskeys = new String[]{"разработчик игр", "game", "игр"};
                LoaderBase loaderBaseGame = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsGame, MyApp.getInstance().CountGame);
                maskeys = new String[]{"тестировщик по", "qa", "тестировщик"};
                LoaderBase loaderBaseQA = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsQA, MyApp.getInstance().CountQA);
                maskeys = new String[]{"ios разработчик", "ios", "developer"};
                LoaderBase loaderBaseIOS = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsIOS, MyApp.getInstance().CountIOS);
                maskeys = new String[]{"sql разработчик", "sql", "developer"};
                LoaderBase loaderBaseSQL = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsSQL, MyApp.getInstance().CountSQL);*/
            }
        });
    }
    static void start(int i){
        switch (i){
            case 0:
                maskeys = new String[]{"frontend разработчик", "frontend", "developer"};
                LoaderBase loaderBaseFront = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsFront, MyApp.getInstance().CountFront);
                break;
            case 1:
                maskeys = new String[]{"backend разработчик", "backend", "developer"};
                LoaderBase loaderBaseBack = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsBack, MyApp.getInstance().CountBack);
                break;
            case 2:
                maskeys = new String[]{"android разработчик", "android", "developer"};
                LoaderBase loaderBaseAndr = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsAndroid, MyApp.getInstance().CountAndr);
                break;
            case 3:
                Log.i("code", "start data");
                maskeys = new String[]{"data scientist", "data", "scien"};
                LoaderBase loaderBaseData = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsData, MyApp.getInstance().CountData);
                break;
            case 4:
                maskeys = new String[]{"разработчик игр", "game", "игр"};
                LoaderBase loaderBaseGame = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsGame, MyApp.getInstance().CountGame);
                break;
            case 5:
                maskeys = new String[]{"тестировщик по", "qa", "тестировщик"};
                LoaderBase loaderBaseQA = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsQA, MyApp.getInstance().CountQA);
                break;
            case 6:
                maskeys = new String[]{"ios разработчик", "ios", "developer"};
                LoaderBase loaderBaseIOS = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsIOS, MyApp.getInstance().CountIOS);
                break;
            case 7:
                maskeys = new String[]{"sql разработчик", "sql", "developer"};
                LoaderBase loaderBaseSQL = new LoaderBase(maskeys, MyApp.getInstance().StackSkillsSQL, MyApp.getInstance().CountSQL);
                break;
        }
    }
}

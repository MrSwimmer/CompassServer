package com.serverforcompass;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;


import com.serverforcompass.hh.APIService;
import com.serverforcompass.hh.GETPage.PageV;
import com.serverforcompass.hh.GETVacancy.Vacancy;

import java.util.ArrayList;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.serverforcompass.MainActivity.*;
import static com.serverforcompass.MainActivity.count;

/**
 * Created by Севастьян on 06.10.2017.
 */

public class LoaderBase {
    private final APIService service;
    float percent, progress=0;
    boolean add=false;
    float count=0;
    public LoaderBase(final String[] params, final ArrayList<String> From, final ArrayList<Skill> To) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.hh.ru")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(APIService.class);
        //Log.i("code", "start "+params[1]);
        Call<PageV> callcount = service.getListURL(params[0], 1);
        callcount.enqueue(new Callback<PageV>() {
            @Override
            public void onResponse(Call<PageV> call, Response<PageV> response) {
                PageV page = response.body();
                count=(page.getFound()/20);
                count=Math.min(count,99);
                percent = 100/(count*20);
                Log.i("code", count+"");
                GetIDthenVacancy(params, From, To);
            }
            @Override
            public void onFailure(Call<PageV> call, Throwable t) {

            }
        });

    }



    void GetIDthenVacancy(final String[] params, final ArrayList<String> stackfromfile, final ArrayList<Skill> countskills){

        for(int i=0; i<count; i++){

            Call<PageV> call = service.getListURL(params[0], i);

            call.enqueue(new Callback<PageV>() {
                @Override
                public void onResponse(Call<PageV> call, Response<PageV> response) {
                    PageV page = response.body();
                    for(int j=0; j<20; j++) {
                        String buf = page.getItems().get(j).getId();
                        String toLow = page.getItems().get(j).getName().toLowerCase();
                        if(toLow.contains(params[1])||toLow.contains(params[2])){
                            Call<Vacancy> callv = service.getVacancy(buf);
                            callv.enqueue(new Callback<Vacancy>() {
                                @Override
                                public void onResponse(Call<Vacancy> call, Response<Vacancy> response) {
                                    progress+=percent;
                                    Log.i("code", params[1]+ " " + progress);
                                    if(progress>=98&&!add) {
                                        MainActivity.count++;
                                        //Log.i("code", countskills.size()+"inloader");
                                        new MakeandDownFiles(params[1], countskills);
                                        add=true;
                                        Log.i("code", "write "+params[1]);
                                        start(MainActivity.count);
                                        Handler handler = new Handler(Looper.getMainLooper());
                                        handler.post( new Runnable() {
                                            @Override
                                            public void run() {
                                                if(MainActivity.count==8){
                                                    Toast.makeText(MainActivity.context, "Готово", Toast.LENGTH_SHORT).show();
                                                }
                                                else {
                                                    Toast.makeText(MainActivity.context, "Начинаем загрузку "+MainActivity.count, Toast.LENGTH_SHORT).show();
                                                }
                                            }
                                        } );
                                    }
                                    String description = response.body().getDescription();
                                    //Log.i("code", description + " " + stackfromfile.size());
                                    //ArrayList<String> arrayList = stackfromfile;
                                    for(int k=0; k<stackfromfile.size(); k++){
                                        if(description.toLowerCase().contains(stackfromfile.get(k))){
                                            Skill newskill = new Skill(stackfromfile.get(k), params[1]);
                                            boolean meet=false;
                                            for(int o=0; o<countskills.size(); o++){
                                                //Log.i("code",arrayList.get(k)+" "+MyApp.getInstance().CountSkillsFront.get(o).getSkill());
                                                if(countskills.get(o).getSkill().equals(stackfromfile.get(k))){
                                                    countskills.get(o).addCount();
                                                    meet=true;
                                                    //Log.i("code",arrayList.get(k));
                                                    break;
                                                }
                                            }
                                            if(!meet){
                                                countskills.add(newskill);
                                                //Log.i("code",newskill.getSkill());
                                            }
                                        }
                                    }
                                }
                                @Override
                                public void onFailure(Call<Vacancy> call, Throwable t) {
                                    Log.i("code", "SVBID "+ t.toString());
                                }
                            });
                        }
                        else {
                            progress+=percent;
                            Log.i("code", params[1]+ " " + progress);
                            if(progress>=97&&!add) {
                                MainActivity.count++;
                                new MakeandDownFiles(params[1], countskills);
                                add=true;
                                start(MainActivity.count);
                                Handler handler = new Handler(Looper.getMainLooper());
                                handler.post( new Runnable() {
                                    @Override
                                    public void run() {
                                        if(MainActivity.count==8){
                                            Toast.makeText(MainActivity.context, "Готово", Toast.LENGTH_SHORT).show();

                                        }
                                        else {
                                            Toast.makeText(MainActivity.context, "Начинаем загрузку "+MainActivity.count, Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                } );
                                Log.i("code", "write "+params[1]);

                            }
                        }
                    }
                }
                @Override
                public void onFailure(Call<PageV> call, Throwable t) {
                    Log.i("code", "SVBID"+ t.toString());
                }
            });
        }
    }
}

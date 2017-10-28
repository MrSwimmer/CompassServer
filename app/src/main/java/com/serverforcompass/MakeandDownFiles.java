package com.serverforcompass;

import android.os.Environment;
import android.util.Log;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Севастьян on 28.10.2017.
 */

public class MakeandDownFiles {

    String DIR_SD = "CompassITServ";
    public MakeandDownFiles(String param, ArrayList<Skill> countskills) {
        Log.i("code", countskills.size()+"size");
        writeFileSD(param+".txt", countskills);
    }
    void writeFileSD(String name, ArrayList<Skill> countskills) {
        // проверяем доступность SD
        if (!Environment.getExternalStorageState().equals(
                Environment.MEDIA_MOUNTED)) {
            Log.i("code", "SD-карта не доступна: " + Environment.getExternalStorageState());
            return;
        }
        // получаем путь к SD
        File sdPath = Environment.getExternalStorageDirectory();
        // добавляем свой каталог к пути
        sdPath = new File(sdPath.getAbsolutePath() + "/" + DIR_SD);
        // создаем каталог
        sdPath.mkdirs();
        // формируем объект File, который содержит путь к файлу
        File sdFile = new File(sdPath, name);
        try {
            // открываем поток для записи
            BufferedWriter bw = new BufferedWriter(new FileWriter(sdFile));
            // пишем данные

            for(int i=0; i<countskills.size(); i++){
                String buf=countskills.get(i).getSkill()+":"+countskills.get(i).getCount()+",";
                bw.write(buf);
            }
            // закрываем поток
            bw.close();
            Log.i("code", "Файл записан на SD: " + sdFile.getAbsolutePath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

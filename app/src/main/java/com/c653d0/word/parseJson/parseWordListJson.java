package com.c653d0.word.parseJson;

import android.util.Log;

import com.c653d0.word.MyViewModel;
import com.c653d0.word.db.WordList.AllWord;

import org.json.JSONArray;
import org.json.JSONObject;

public class parseWordListJson {
    MyViewModel viewModel;

    public parseWordListJson(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void parseJsonData(String jsonData) {
        try{
            viewModel.deleteWord();
            JSONObject beginObject = new JSONObject(jsonData);
            String wordData = beginObject.getString("datas");
            JSONArray jsonArray = new JSONArray(wordData);

            for (int i=0 ; i<jsonArray.length() ; i++) {
                JSONObject wordObject = jsonArray.getJSONObject(i);
                String word = wordObject.getString("name");
                String meaning = wordObject.getString("desc");
                //String symbol = wordObject.getString("symbol");
                //String sound = wordObject.getString("sound");

                viewModel.insertWord(new AllWord(word,meaning,false));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

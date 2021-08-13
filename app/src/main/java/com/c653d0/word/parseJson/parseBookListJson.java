package com.c653d0.word.parseJson;

import android.util.Log;

import com.c653d0.word.MyViewModel;
import com.c653d0.word.db.BookList.BookList;

import org.json.JSONArray;
import org.json.JSONObject;

public class parseBookListJson {
    MyViewModel viewModel;

    public parseBookListJson(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }


    public void parseJsonData(String jsonData) {
        try {
            JSONObject allJsonObject = new JSONObject(jsonData);
            String data = allJsonObject.getString("datas");
            JSONArray jsonArray = new JSONArray(data);
            viewModel.deleteBook();

            for (int i=0 ; i<jsonArray.length() ; i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                String bookTitle = jsonObject.getString("title");
                Log.e("book_title",bookTitle);
                String allBookWords = jsonObject.getString("word_num");
                Log.e("allBookWord",allBookWords);

                viewModel.insertBook(new BookList(bookTitle,allBookWords,false));
                /*String allListObject = jsonObject.getString("child_list");
                JSONArray allList = new JSONArray(allListObject);*/
                /*for (int j=0 ; j<allList.length() ; j++){
                    JSONObject courseObject = allList.getJSONObject(j);
                    String classId = courseObject.getString("class_id");
                    Log.e("classId",classId);
                    String wordNum = courseObject.getString("word_num");
                    Log.e("wordNum",wordNum);
                    String title = courseObject.getString("title");
                    Log.e("title",title);
                    viewModel.insertBook(new BookList(bookTitle,allBookWords,title,classId,wordNum));
                }*/
            }
        }catch (Exception e){
            Log.e("jsonParseError",e.toString());
            e.printStackTrace();
        }


    }
}

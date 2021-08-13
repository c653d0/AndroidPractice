package com.c653d0.word.parseJson;

import android.util.Log;

import com.c653d0.word.MyViewModel;
import com.c653d0.word.db.EachBook.EachBook;

import org.json.JSONArray;
import org.json.JSONObject;

public class parseEachBookJson {
    private MyViewModel viewModel;

    public parseEachBookJson(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void parseJsonData(String jsonData) {
        try {
            viewModel.deleteEachBook();
            JSONObject jsonObject = new JSONObject(jsonData);
            String data = jsonObject.getString("datas");
            JSONArray jsonArray = new JSONArray(data);
            JSONObject dataObject = jsonArray.getJSONObject(0);
            JSONArray childListArray = new JSONArray(dataObject.getString("child_list"));


            for (int i = childListArray.length()-1; i >=0; i--) {
                JSONObject object = childListArray.getJSONObject(i);
                String classID = object.getString("class_id");
                String wordNum = object.getString("word_num");
                String title = object.getString("title");
                int courseNum = Integer.parseInt(object.getString("course_num"));

                viewModel.insertEachBook(new EachBook(classID, title, wordNum,true));

                Log.e("each_book_data", "class_id=" + classID + " word_num=" + wordNum + " title=" + title);
            }

        } catch (Exception e) {
            Log.e("parseEachBookError", e.toString());
            e.printStackTrace();
        }

    }

    //JSON数据示例
    /*{
        "msg":"success",
            "code":"1000",
            "datas": [
        {
            "child_list": [
            {
                "class_id":"250815",
                    "word_num":"345",
                    "title":"新初中人教版 八年级上",
                    "course_num":"18"
            },
            {
                "class_id":"358176",
                    "word_num":"492",
                    "title":"新初中人教版 八年级下",
                    "course_num":"25"
            }
      ],
            "word_num":"837",
                "title":"新初中人教版",
                "course_num":"43"
        }
  ]
    }*/
}

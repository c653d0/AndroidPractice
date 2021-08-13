package com.c653d0.word;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;
import android.view.Window;

import com.c653d0.word.databinding.ActivityMainBinding;
import com.c653d0.word.db.BookList.BookList;
import com.c653d0.word.parseJson.parseBookListJson;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class MainActivity extends AppCompatActivity {
    MyViewModel viewModel;
    ActivityMainBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication())).get(MyViewModel.class);


        binding.setData(viewModel);
        binding.setLifecycleOwner(this);

        GetWordFromApi getWord = new GetWordFromApi();

        String requestUrl = "https://rw.ylapi.cn/reciteword/list.u";

        Map params = new HashMap();
        params.put("uid", "11578");
        params.put("appkey", "c3e25fceb30ffad660d9259a0abd0813");

        String result = getWord.getResult(requestUrl,params);

        parseBookListJson parseJson = new parseBookListJson(viewModel);

        //带入name参数可以得到课本列表
        parseJson.parseJsonData(result);

        /*binding.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                GetWordFromApi getWord = new GetWordFromApi();

                String requestUrl = "https://rw.ylapi.cn/reciteword/list.u";

                Map params = new HashMap();
                params.put("uid", "11578");
                params.put("appkey", "c3e25fceb30ffad660d9259a0abd0813");
                params.put("name","新初中人教版");

                String result = getWord.getResult(requestUrl,params);
                Log.e("MainRes",result);

                binding.textView.setText(result);

                parseEachBookJson parseJson = new parseEachBookJson(viewModel);

                //带入name参数可以得到课本列表
                parseJson.parseJsonData(result);



            }
        });*/
    }
}
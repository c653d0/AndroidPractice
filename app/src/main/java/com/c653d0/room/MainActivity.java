package com.c653d0.room;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    //WordDao wordDao;
    //WordDataBase wordDataBase;
    Button buttonInsert, buttonUpdate, buttonDelete, buttonClear;
    MyAdapter adapter1,adapter2;
    Switch aSwitch;
    RecyclerView recyclerView;


    //LiveData
    LiveData<List<Word>> allWordsLive;

    WordViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewModel = new ViewModelProvider(this,
                new ViewModelProvider.AndroidViewModelFactory(this.getApplication()))
                .get(WordViewModel.class);

        recyclerView = findViewById(R.id.recycleview);
        aSwitch = findViewById(R.id.switch1);

        adapter1 = new MyAdapter(false,viewModel);
        adapter2 = new MyAdapter(true,viewModel);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter1);

        aSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    recyclerView.setAdapter(adapter2);
                }else{
                    recyclerView.setAdapter(adapter1);
                }
            }
        });
        //wordDataBase = WordDataBase.getDatabase(getApplicationContext());

        //wordDao = wordDataBase.getWordDao();

        //LiveData
        //allWordsLive = wordDao.getAllWordsLive();
        //LiveData

        //使用LiveData不再需要刷新
        //updateView();

        viewModel.getAllWordsLive().observe(this, new Observer<List<Word>>() {
            @Override
            public void onChanged(List<Word> words) {
                int tmp = adapter1.getItemCount();
                adapter1.setAllWords(words);
                adapter2.setAllWords(words);
                if (tmp != words.size()) {
                    adapter1.notifyDataSetChanged();
                    adapter2.notifyDataSetChanged();
                }

            }
        });

        buttonInsert = findViewById(R.id.button_insert);
        buttonUpdate = findViewById(R.id.button_updata);
        buttonDelete = findViewById(R.id.button_delete);
        buttonClear = findViewById(R.id.button_clear);

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String[] english = {
                        "Hello",
                        "World",
                        "Android",
                        "Google",
                        "Studio",
                        "Project",
                        "Database",
                        "Recycler",
                        "View",
                        "String",
                        "Value",
                        "Integer"
                };
                String[] chinese = {
                        "你好",
                        "世界",
                        "安卓系统",
                        "谷歌公司",
                        "工作室",
                        "项目",
                        "数据库",
                        "回收站",
                        "视图",
                        "字符串",
                        "价值",
                        "整数类型"
                };
                for (int i = 0; i < english.length; i++) {
                    viewModel.insertWords(new Word(english[i],chinese[i]));
                }
                //new InsertAsyncTask(wordDao).execute(word,word1);
                //updateView();
            }
        });

        buttonClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //wordDao.deleteAllWords();
                //updateView();
                viewModel.deleteAllWords();
                //new DeleteAllAsyncTask(wordDao).execute();
            }
        });

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word = new Word("Hi", "你好");
                word.setId(214);
                //wordDao.updateWords(word);
                //updateView();
                viewModel.updateWords(word);
                //new UpdateAsyncTask(wordDao).execute(word);
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Word word= new Word("","");
                word.setId(214);
                //wordDao.deleteWords(word);
                //updateView();

                viewModel.deleteWords(word);
            }
        });
    }


}
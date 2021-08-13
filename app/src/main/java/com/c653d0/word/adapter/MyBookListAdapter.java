package com.c653d0.word.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.c653d0.word.GetWordFromApi;
import com.c653d0.word.MyViewModel;
import com.c653d0.word.R;
import com.c653d0.word.db.BookList.BookList;
import com.c653d0.word.parseJson.parseEachBookJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBookListAdapter extends RecyclerView.Adapter<MyBookListAdapter.MyViewHolder> {
    private List<BookList> allBookListLive = new ArrayList<>();
    private MyViewModel viewModel;
    NavController controller;

    public MyBookListAdapter(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAllBookListLive(List<BookList> books) {
        this.allBookListLive = books;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card_book,parent,false);
        return new MyViewHolder(itemView);
    }


    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        BookList bookList = allBookListLive.get(position);
        holder.bookName.setText(bookList.getTitle());
        holder.bookPic.setImageResource(R.drawable.ic_baseline_book_24);
        holder.wordNum.setText("单词数："+bookList.getWordNum());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewModel.deleteEachBook();

                GetWordFromApi getWord = new GetWordFromApi();
                controller = Navigation.findNavController(v);

                String requestUrl = "https://rw.ylapi.cn/reciteword/list.u";

                Map params = new HashMap();
                params.put("uid", "11578");
                params.put("appkey", "c3e25fceb30ffad660d9259a0abd0813");
                params.put("name",bookList.getTitle());

                String result = getWord.getResult(requestUrl,params);
                parseEachBookJson parseEachBookJson = new parseEachBookJson(viewModel);
                parseEachBookJson.parseJsonData(result);

                controller.navigate(R.id.action_selectBookFragment_to_allBookFragment);

                Log.e("onClickThing",result);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allBookListLive.size();
    }


    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView bookName,wordNum;
        ImageView bookPic;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.textview_bookListName);
            bookPic = itemView.findViewById(R.id.imageView_bookListPic);
            wordNum = itemView.findViewById(R.id.textView_bookListWordNum);
        }
    }
}

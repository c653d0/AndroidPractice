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
import com.c653d0.word.db.EachBook.EachBook;
import com.c653d0.word.parseJson.parseWordListJson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MyBookAdapter extends RecyclerView.Adapter<MyBookAdapter.MyBookViewHolder> {
    List<EachBook> allBookLive = new ArrayList<>();
    MyViewModel viewModel;
    NavController controller;

    public void setAllBookLive(List<EachBook> allBookLive) {
        this.allBookLive = allBookLive;
    }

    public MyBookAdapter(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public MyBookViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card_all_book,parent,false);
        return new MyBookViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyBookViewHolder holder, int position) {
        EachBook book = allBookLive.get(position);
        holder.bookName.setText(book.getTitle());
        holder.bookWordNum.setText(book.getWordNum());
        holder.bookPic.setImageResource(R.drawable.ic_baseline_menu_book_24);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                controller = Navigation.findNavController(v);
                GetWordFromApi getWord = new GetWordFromApi();

                String requestUrl = "https://rw.ylapi.cn/reciteword/wordlist.u";

                Map params = new HashMap();
                params.put("uid", "11578");
                params.put("appkey", "c3e25fceb30ffad660d9259a0abd0813");
                params.put("class_id",book.getClassId());
                params.put("course","1");

                String result = getWord.getResult(requestUrl,params);

                Log.e("errorResult",result);
                parseWordListJson parseWordListJson = new parseWordListJson(viewModel);
                parseWordListJson.parseJsonData(result);

                controller.navigate(R.id.action_allBookFragment_to_allWordFragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return allBookLive.size();
    }

    static class MyBookViewHolder extends RecyclerView.ViewHolder {
        ImageView bookPic;
        TextView bookName, bookWordNum;

        public MyBookViewHolder(@NonNull View itemView) {
            super(itemView);
            bookPic = itemView.findViewById(R.id.imageView_bookPic);
            bookName = itemView.findViewById(R.id.textView_bookName);
            bookWordNum = itemView.findViewById(R.id.textView_bookWordNum);
        }
    }
}

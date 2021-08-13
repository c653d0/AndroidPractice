package com.c653d0.word.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.c653d0.word.MyViewModel;
import com.c653d0.word.R;
import com.c653d0.word.db.WordList.AllWord;

import java.util.ArrayList;
import java.util.List;

public class MyWordAdapter extends RecyclerView.Adapter<MyWordAdapter.MyViewHolder> {
    List<AllWord> allWordList = new ArrayList<>();
    MyViewModel viewModel;

    public MyWordAdapter(MyViewModel viewModel) {
        this.viewModel = viewModel;
    }

    public void setAllWordList(List<AllWord> list) {
        this.allWordList = list;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View itemView;
        itemView = layoutInflater.inflate(R.layout.cell_card_word,parent,false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        AllWord word = allWordList.get(position);
        holder.word.setText(word.getEnglishWord());
        holder.number.setText(String.valueOf(position+1));

        holder.isHide.setOnCheckedChangeListener(null);
        if (word.isHide()) {
            holder.meaning.setText(word.getChineseMeaning());
            holder.isHide.setChecked(true);
        }else{
            holder.meaning.setText("***********");
            holder.isHide.setChecked(false);
        }

        holder.isHide.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked){
                    holder.meaning.setText(word.getChineseMeaning());
                    word.setHide(true);
                }else{
                    holder.meaning.setText("***********");
                    word.setHide(false);
                }
            }
        });

        holder.buttonUpPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "上一页", Toast.LENGTH_SHORT).show();
            }
        });

        holder.buttonNextPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(holder.itemView.getContext(), "下一页", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return allWordList.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {
        TextView word,meaning,number;
        Switch isHide;
        Button buttonUpPage, buttonNextPage;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            word = itemView.findViewById(R.id.textView_word);
            meaning = itemView.findViewById(R.id.textView_meaning);
            number = itemView.findViewById(R.id.textView_number);
            isHide = itemView.findViewById(R.id.switch_hide);
            buttonNextPage = itemView.findViewById(R.id.buttonUpPage);
            buttonNextPage = itemView.findViewById(R.id.buttonNextPage);
        }
    }
}

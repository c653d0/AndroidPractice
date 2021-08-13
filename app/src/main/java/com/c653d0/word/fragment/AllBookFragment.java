package com.c653d0.word.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c653d0.word.adapter.MyBookAdapter;
import com.c653d0.word.MyViewModel;
import com.c653d0.word.R;
import com.c653d0.word.databinding.FragmentAllBookBinding;
import com.c653d0.word.db.EachBook.EachBook;

import java.util.List;


public class AllBookFragment extends Fragment {


    public AllBookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        MyViewModel viewModel;
        MyBookAdapter adapter;
        FragmentAllBookBinding bookBinding;

        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        bookBinding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_book,container,false);

        adapter = new MyBookAdapter(viewModel);
        bookBinding.recyclerViewAllBook.setLayoutManager(new LinearLayoutManager(requireContext()));
        bookBinding.recyclerViewAllBook.setAdapter(adapter);


        viewModel.getAllEachBookLive().observe(requireActivity(), new Observer<List<EachBook>>() {
            @Override
            public void onChanged(List<EachBook> books) {
                Log.e("type_list",books.toString());
                adapter.setAllBookLive(books);
                adapter.notifyDataSetChanged();

            }
        });
        return bookBinding.getRoot();
    }
}
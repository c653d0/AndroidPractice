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

import com.c653d0.word.adapter.MyBookListAdapter;
import com.c653d0.word.MyViewModel;
import com.c653d0.word.R;
import com.c653d0.word.databinding.FragmentSelectBookBinding;
import com.c653d0.word.db.BookList.BookList;

import java.util.List;

public class SelectBookFragment extends Fragment {

    public SelectBookFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        MyViewModel viewModel;
        MyBookListAdapter myAdapter;
        FragmentSelectBookBinding binding;

        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);
        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_select_book,container,false);

        myAdapter = new MyBookListAdapter(viewModel);



        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setAdapter(myAdapter);
        binding.recyclerView.setFocusable(false);

        viewModel.getAllBookLive().observe(requireActivity(), new Observer<List<BookList>>() {
            @Override
            public void onChanged(List<BookList> bookLists) {
                int tmp = myAdapter.getItemCount();
                Log.e("type_list",bookLists.toString());
                myAdapter.setAllBookListLive(bookLists);
                if (tmp != bookLists.size()) {
                    myAdapter.notifyDataSetChanged();
                }

            }
        });
        return binding.getRoot();
    }


}
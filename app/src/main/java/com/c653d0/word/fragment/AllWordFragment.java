package com.c653d0.word.fragment;

import android.os.Bundle;

import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.c653d0.word.MyViewModel;
import com.c653d0.word.R;
import com.c653d0.word.adapter.MyWordAdapter;
import com.c653d0.word.databinding.FragmentAllBookBinding;
import com.c653d0.word.databinding.FragmentAllWordBinding;
import com.c653d0.word.db.WordList.AllWord;
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton;

import java.util.List;


public class AllWordFragment extends Fragment {

    public AllWordFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FragmentAllWordBinding binding;
        MyViewModel viewModel;
        MyWordAdapter adapter;

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_all_word,container,false);
        viewModel = new ViewModelProvider(requireActivity()).get(MyViewModel.class);

        adapter = new MyWordAdapter(viewModel);
        binding.recyclerViewAllWord.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerViewAllWord.setAdapter(adapter);

        viewModel.getAllWordLive().observe(requireActivity(), new Observer<List<AllWord>>() {
            @Override
            public void onChanged(List<AllWord> allWords) {
                adapter.setAllWordList(allWords);
                adapter.notifyDataSetChanged();
            }
        });
        return binding.getRoot();
    }
}
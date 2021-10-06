package com.moataz.afternoonhadeeth.ui.view.fragment;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding;
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter;
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel;
import com.moataz.afternoonhadeeth.utils.IOnBackPressed;

public class HomeFragment extends Fragment implements IOnBackPressed {

    private HomeAdapter adapter;
    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        initializeAdapter();
        initializeViewModel();
        getTopList();
        onSwipeRefresh();
        return view;
    }

    @SuppressLint("ClickableViewAccessibility")
    private void initializeAdapter() {
        adapter = new HomeAdapter();
        binding.recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        binding.recyclerView.setHasFixedSize(true);
        binding.recyclerView.setAdapter(adapter);
        // disable thw touch on items when scroll the recyclerview
        binding.recyclerView.setOnTouchListener((view, motionEvent) -> {
            binding.recyclerView.onTouchEvent(motionEvent);
            return true;
        });
    }

    private void getTopList() {
        viewModel.makeApiCallHome().observe(requireActivity(), response -> {
            switch (response.status) {
                case ERROR: {
                    binding.progressBar.setVisibility(View.GONE);
                    break;
                }
                case LOADING: {
                    binding.progressBar.setVisibility(View.VISIBLE);
                    break;
                }
                case SUCCESS: {
                    binding.progressBar.setVisibility(View.GONE);
                    adapter.setHomeList(response.data);
                    break;
                }
            }
        });
    }

    private void onSwipeRefresh() {
        binding.swipeToRefresh.setOnRefreshListener(() -> viewModel.makeApiCallHome().observe(requireActivity(), response -> {
            switch (response.status) {
                case ERROR: {
                    binding.swipeToRefresh.setRefreshing(false);
                    break;
                }
                case SUCCESS: {
                    binding.swipeToRefresh.setRefreshing(false);
                    adapter.setHomeList(response.data);
                    break;
                }
            }
        }));
    }

    private void initializeViewModel() {
        viewModel = new ViewModelProvider(this).get(HomeViewModel.class);
    }

    @Override
    public boolean onBackPressed() {
        requireActivity().moveTaskToBack(true); //exit the app when press back
        requireActivity().finish();
        return true;
    }
}
package com.moataz.afternoonhadeeth.ui.view.fragment;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.anjlab.android.iab.v3.BillingProcessor;
import com.anjlab.android.iab.v3.TransactionDetails;
import com.moataz.afternoonhadeeth.R;
import com.moataz.afternoonhadeeth.databinding.FragmentHomeBinding;
import com.moataz.afternoonhadeeth.ui.adapter.HomeAdapter;
import com.moataz.afternoonhadeeth.ui.viewmodel.HomeViewModel;
import com.moataz.afternoonhadeeth.utils.IOnBackPressed;
import com.moataz.afternoonhadeeth.utils.helper.Intents;

public class HomeFragment extends Fragment implements IOnBackPressed, BillingProcessor.IBillingHandler {

    private HomeAdapter adapter;
    private HomeViewModel viewModel;
    private FragmentHomeBinding binding;
    private BillingProcessor billingProcess;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setOnClickToolbarIcons();
        setupBillingProcess(getContext());
        initializeAdapter();
        initializeViewModel();
        getTopList();
        onSwipeRefresh();
        return view;
    }

    private void setOnClickToolbarIcons() {
        binding.instagram.setOnClickListener(v -> Intents.INSTANCE.openInstagramAccountIntent((AppCompatActivity) requireActivity()));
    }

    private void setupBillingProcess(Context context) {
        billingProcess = new BillingProcessor(context, getResources().getString(R.string.play_console_license), this);
        billingProcess.initialize();
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

    /* start of billing methods */
    @Override
    public void onProductPurchased(@NonNull String productId, TransactionDetails details) {
        Log.d("MainActivity", "onProductPurchased: ");
    }

    @Override
    public void onPurchaseHistoryRestored() {
        Log.d("MainActivity", "onPurchaseHistoryRestored: ");
    }

    @Override
    public void onBillingError(int errorCode, Throwable error) {
        Log.d("MainActivity", "onBillingError: ");
    }

    @Override
    public void onBillingInitialized() {
        Log.d("MainActivity", "onBillingInitialized: ");

        String premium = getResources().getString(R.string.premium);
        TransactionDetails purchaseTransactionDetails = billingProcess.getSubscriptionTransactionDetails(premium);
        billingProcess.loadOwnedPurchasesFromGoogle();

        binding.support.setOnClickListener(v -> {
            if (billingProcess.isSubscriptionUpdateSupported()) {
                billingProcess.subscribe(requireActivity(), premium);
            } else {
                Log.d(
                        "MainActivity",
                        "onBillingInitialized: Subscription updated is not supported"
                );
            }
        });
    }
    /* end of billing methods */

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (!billingProcess.handleActivityResult(requestCode, resultCode, data)) {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onBackPressed() {
        requireActivity().moveTaskToBack(true); //exit the app when press back
        requireActivity().finish();
        return true;
    }

    @Override
    public void onDestroy() {
        billingProcess.release();
        super.onDestroy();
    }
}
package com.example.tuprak8;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class SearchFragment extends Fragment {

    private EditText search;
    private ImageView cancelSearch;
    private ProgressBar progressBar;
    private RecyclerView rvSearch;

    private DataListAdapter adapter;

    public SearchFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        search = view.findViewById(R.id.search);
        cancelSearch = view.findViewById(R.id.cancelSearch);
        progressBar = view.findViewById(R.id.ProgressBar);
        rvSearch = view.findViewById(R.id.rv_search);
        adapter = new DataListAdapter();

        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        rvSearch.setLayoutManager(layoutManager);
        rvSearch.setAdapter(adapter);

        progressBar.setVisibility(View.GONE);
        // Mengatur TextWatcher untuk melakukan pencarian saat teks berubah
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                searchData(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });

        cancelSearch.setOnClickListener(v -> {
            search.setText("");
        });
    }

    private void searchData(CharSequence charSequence) {
        progressBar.setVisibility(View.VISIBLE);
        rvSearch.setVisibility(View.GONE);

        Executor executor = Executors.newSingleThreadExecutor();
        Handler handler = new Handler(Looper.getMainLooper());

        executor.execute(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            handler.post(() -> {
                if (TextUtils.isEmpty(charSequence)) {
                    // Jika teks pencarian kosong, set daftar kosong
                    adapter.setArrayList(new ArrayList<>());
                } else {
                    // Jika terdapat teks pencarian, cari data sesuai dengan teks tersebut
                    adapter.setArrayList(DataSource.searchDataUpload(charSequence.toString()));
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.GONE);
                rvSearch.setVisibility(View.VISIBLE);
            });
        });
    }
}

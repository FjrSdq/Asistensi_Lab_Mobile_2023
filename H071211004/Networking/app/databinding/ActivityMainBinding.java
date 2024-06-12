package com.muammarahlnn.networkingassignment.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.muammarahlnn.networkingassignment.R;
/* loaded from: classes6.dex */
public final class ActivityMainBinding implements ViewBinding {
    public final ImageView btnRefresh;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final RecyclerView rvUser;
    public final TextView textView;
    public final TextView tvAlert;

    private ActivityMainBinding(ConstraintLayout rootView, ImageView btnRefresh, ProgressBar progressBar, RecyclerView rvUser, TextView textView, TextView tvAlert) {
        this.rootView = rootView;
        this.btnRefresh = btnRefresh;
        this.progressBar = progressBar;
        this.rvUser = rvUser;
        this.textView = textView;
        this.tvAlert = tvAlert;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityMainBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_main, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityMainBinding bind(View rootView) {
        int id = R.id.btn_refresh;
        ImageView btnRefresh = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (btnRefresh != null) {
            id = R.id.progress_bar;
            ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
            if (progressBar != null) {
                id = R.id.rv_user;
                RecyclerView rvUser = (RecyclerView) ViewBindings.findChildViewById(rootView, id);
                if (rvUser != null) {
                    id = R.id.textView;
                    TextView textView = (TextView) ViewBindings.findChildViewById(rootView, id);
                    if (textView != null) {
                        id = R.id.tv_alert;
                        TextView tvAlert = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvAlert != null) {
                            return new ActivityMainBinding((ConstraintLayout) rootView, btnRefresh, progressBar, rvUser, textView, tvAlert);
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}

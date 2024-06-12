package com.muammarahlnn.networkingassignment.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.muammarahlnn.networkingassignment.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes6.dex */
public final class ActivityProfileBinding implements ViewBinding {
    public final ImageView btnRefresh;
    public final CardView cvProfile;
    public final CircleImageView ivPhoto;
    public final ProgressBar progressBar;
    private final ConstraintLayout rootView;
    public final TextView tvAlert;
    public final TextView tvEmail;
    public final TextView tvName;

    private ActivityProfileBinding(ConstraintLayout rootView, ImageView btnRefresh, CardView cvProfile, CircleImageView ivPhoto, ProgressBar progressBar, TextView tvAlert, TextView tvEmail, TextView tvName) {
        this.rootView = rootView;
        this.btnRefresh = btnRefresh;
        this.cvProfile = cvProfile;
        this.ivPhoto = ivPhoto;
        this.progressBar = progressBar;
        this.tvAlert = tvAlert;
        this.tvEmail = tvEmail;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public ConstraintLayout getRoot() {
        return this.rootView;
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ActivityProfileBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.activity_profile, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ActivityProfileBinding bind(View rootView) {
        int id = R.id.btn_refresh;
        ImageView btnRefresh = (ImageView) ViewBindings.findChildViewById(rootView, id);
        if (btnRefresh != null) {
            id = R.id.cv_profile;
            CardView cvProfile = (CardView) ViewBindings.findChildViewById(rootView, id);
            if (cvProfile != null) {
                id = R.id.iv_photo;
                CircleImageView ivPhoto = (CircleImageView) ViewBindings.findChildViewById(rootView, id);
                if (ivPhoto != null) {
                    id = R.id.progress_bar;
                    ProgressBar progressBar = (ProgressBar) ViewBindings.findChildViewById(rootView, id);
                    if (progressBar != null) {
                        id = R.id.tv_alert;
                        TextView tvAlert = (TextView) ViewBindings.findChildViewById(rootView, id);
                        if (tvAlert != null) {
                            id = R.id.tv_email;
                            TextView tvEmail = (TextView) ViewBindings.findChildViewById(rootView, id);
                            if (tvEmail != null) {
                                id = R.id.tv_name;
                                TextView tvName = (TextView) ViewBindings.findChildViewById(rootView, id);
                                if (tvName != null) {
                                    return new ActivityProfileBinding((ConstraintLayout) rootView, btnRefresh, cvProfile, ivPhoto, progressBar, tvAlert, tvEmail, tvName);
                                }
                            }
                        }
                    }
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}

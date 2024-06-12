package com.muammarahlnn.networkingassignment.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.cardview.widget.CardView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.muammarahlnn.networkingassignment.R;
import de.hdodenhof.circleimageview.CircleImageView;
/* loaded from: classes6.dex */
public final class ItemUserBinding implements ViewBinding {
    public final CircleImageView ivPhoto;
    private final CardView rootView;
    public final TextView tvEmail;
    public final TextView tvName;

    private ItemUserBinding(CardView rootView, CircleImageView ivPhoto, TextView tvEmail, TextView tvName) {
        this.rootView = rootView;
        this.ivPhoto = ivPhoto;
        this.tvEmail = tvEmail;
        this.tvName = tvName;
    }

    @Override // androidx.viewbinding.ViewBinding
    public CardView getRoot() {
        return this.rootView;
    }

    public static ItemUserBinding inflate(LayoutInflater inflater) {
        return inflate(inflater, null, false);
    }

    public static ItemUserBinding inflate(LayoutInflater inflater, ViewGroup parent, boolean attachToParent) {
        View root = inflater.inflate(R.layout.item_user, parent, false);
        if (attachToParent) {
            parent.addView(root);
        }
        return bind(root);
    }

    public static ItemUserBinding bind(View rootView) {
        int id = R.id.iv_photo;
        CircleImageView ivPhoto = (CircleImageView) ViewBindings.findChildViewById(rootView, id);
        if (ivPhoto != null) {
            id = R.id.tv_email;
            TextView tvEmail = (TextView) ViewBindings.findChildViewById(rootView, id);
            if (tvEmail != null) {
                id = R.id.tv_name;
                TextView tvName = (TextView) ViewBindings.findChildViewById(rootView, id);
                if (tvName != null) {
                    return new ItemUserBinding((CardView) rootView, ivPhoto, tvEmail, tvName);
                }
            }
        }
        String missingId = rootView.getResources().getResourceName(id);
        throw new NullPointerException("Missing required view with ID: ".concat(missingId));
    }
}

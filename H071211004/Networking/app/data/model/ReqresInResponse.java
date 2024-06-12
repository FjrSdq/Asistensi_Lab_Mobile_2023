package com.muammarahlnn.networkingassignment.data.model;

import com.google.gson.annotations.SerializedName;
/* loaded from: classes5.dex */
public class ReqresInResponse<T> {
    @SerializedName("data")
    private T data;

    public T getData() {
        return this.data;
    }
}

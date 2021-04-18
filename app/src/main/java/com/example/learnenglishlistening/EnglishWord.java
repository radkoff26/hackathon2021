package com.example.learnenglishlistening;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

class EnglishWord {

    @SerializedName("text")
    @Expose
    private String text;
    @SerializedName("pos")
    @Expose
    private String pos;
    @SerializedName("fr")
    @Expose
    private Integer fr;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getPos() {
        return pos;
    }

    public void setPos(String pos) {
        this.pos = pos;
    }

    public Integer getFr() {
        return fr;
    }

    public void setFr(Integer fr) {
        this.fr = fr;
    }

    @Override
    public String toString() {
        return "EnglishWord{" +
                "text='" + text + '\'' +
                ", pos='" + pos + '\'' +
                ", fr=" + fr +
                '}';
    }
}

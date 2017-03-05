package com.example.mpape.Ser;
import android.graphics.Bitmap;
public interface DataListener {
	void onDirty(Bitmap bufferedImage);
	void conv(int t);
}

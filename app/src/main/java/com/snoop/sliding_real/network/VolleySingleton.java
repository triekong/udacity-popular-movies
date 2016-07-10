package com.snoop.sliding_real.network;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;
import com.snoop.sliding_real.extras.MyApplication;

/**
 * Created by galaxywizkid on 7/5/16.
 */
public class VolleySingleton {

    private static VolleySingleton sInstance = null;
    private ImageLoader imageLoader;
    private RequestQueue mRequestQueue;


    private VolleySingleton() {
        mRequestQueue = Volley.newRequestQueue(MyApplication.getAppContext());
        imageLoader = new ImageLoader(mRequestQueue, new ImageLoader.ImageCache() {
            LruCache<String, Bitmap> cache = new LruCache<>((int) ((Runtime.getRuntime().maxMemory()/1024)/8));
            @Override
            public Bitmap getBitmap(String url) {
                return cache.get(url);
            }

            @Override
            public void putBitmap(String url, Bitmap bitmap) {
                cache.put(url, bitmap);
            }
        });
    }

    public static VolleySingleton getInstance() {

        if (sInstance == null) {
            sInstance = new VolleySingleton();
        }
        return sInstance;
    }

    public RequestQueue getmRequestQueue(){
        return mRequestQueue;
    }

    public ImageLoader getImageLoader() {
        return imageLoader;
    }
}

package networking;

import android.graphics.Bitmap;
import android.util.LruCache;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import application.MyApplicationClass;
import utils.ImageHelper;

/**
 * Created by Gaurav on 10/7/17.
 */

public class VolleyManager {


    private static VolleyManager sINSTANCE;

    private ImageLoader mImageLoader;

    public static VolleyManager getInstance() {
        if (sINSTANCE == null) {
            synchronized (VolleyManager.class) {
                if (sINSTANCE == null) {
                    sINSTANCE = new VolleyManager();
                }
            }
        }
        return sINSTANCE;
    }

    private VolleyManager() {

        mImageLoader = new ImageLoader(Volley.newRequestQueue(MyApplicationClass.getInstance()),
                new ImageLoader.ImageCache() {
                    private final LruCache<String, Bitmap>cache = new LruCache<>(20);

                    @Override
                    public Bitmap getBitmap(String url) {
                        Bitmap bitmap = cache.get(url);
//                        bitmap = ImageHelper.getRoundedCornerBitmap(bitmap, 12);
                        return bitmap;
                    }

                    @Override
                    public void putBitmap(String url, Bitmap bitmap) {
                        cache.put(url, bitmap);
                    }
                });

    }

    public RequestQueue getRequestQueue() {
        return Volley.newRequestQueue(MyApplicationClass.getInstance());
    }

    public ImageLoader getImageLoader() {
        return mImageLoader;
    }
}

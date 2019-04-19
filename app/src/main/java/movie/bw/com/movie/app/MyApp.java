package movie.bw.com.movie.app;

import android.app.Application;
import android.content.SharedPreferences;
import android.os.Environment;

import com.facebook.cache.disk.DiskCacheConfig;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

public class MyApp extends Application {

    private static SharedPreferences head;

    public static SharedPreferences getHead() {
        return head;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        head = this.getSharedPreferences("head", MODE_PRIVATE);
        DiskCacheConfig diskCacheConfig = DiskCacheConfig.newBuilder(this)
                .setBaseDirectoryName("IMage")
                .setBaseDirectoryPath(Environment.getExternalStorageDirectory())
                .build();

        ImagePipelineConfig config = ImagePipelineConfig.newBuilder(this)
                .setMainDiskCacheConfig(diskCacheConfig)
                .build();
        Fresco.initialize(this, config);
    }
}

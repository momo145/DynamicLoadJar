package com.chenxinjian.dynamicloadjar.utils;

import android.content.Context;
import android.os.Environment;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import dalvik.system.DexClassLoader;

/**
 * Created by 陈欣健 on 2017/1/17.
 */
public class FileUtil {
    private static FileUtil ourInstance = new FileUtil();

    public static FileUtil getInstance() {
        return ourInstance;
    }

    private FileUtil() {
    }
    //写到SD卡的jar
    public static final String JAR_FILE_NAME = "invoke_demo_dex.jar";
    //jar存储目录
    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "DynamicLoadJar");

    private void copyFromAsset(Context context) {
        if (!dir.exists()) {
            dir.mkdirs();
        }
        InputStream ins = null;
        FileOutputStream fos = null;
        try {
            ins = context.getAssets().open(JAR_FILE_NAME);

            File jar = new File(dir, JAR_FILE_NAME);
            fos = new FileOutputStream(jar);
            int count = 0;
            byte[] b = new byte[1024];
            while ((count = ins.read(b)) != -1) {
                fos.write(b, 0, count);
            }

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (fos != null) {
                    fos.flush();
                    fos.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    DexClassLoader classLoader = null;

    /**
     * 加载jar
     * @param context
     * @return
     */
    public Object loadJar(Context context) {
        Object object = null;
        copyFromAsset(context);
        File file = new File(dir, JAR_FILE_NAME);
        final File optimizedDexOutputPath = context.getDir("temp", Context.MODE_PRIVATE);
        classLoader = new DexClassLoader(file.getAbsolutePath(),
                optimizedDexOutputPath.getAbsolutePath(), null,
                context.getClassLoader());
        try {
            //实现类
            Class<?> iclass = classLoader.loadClass("com.test.invoke.InvokeImpl");
            object = iclass.newInstance();
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return object;
    }
}

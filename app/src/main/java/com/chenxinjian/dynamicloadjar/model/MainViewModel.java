package com.chenxinjian.dynamicloadjar.model;

import android.databinding.ObservableField;
import android.os.Environment;
import android.view.View;
import android.widget.Toast;

import com.chenxinjian.dynamicloadjar.utils.FileUtil;
import com.test.invoke.Invoke;

import java.io.File;

/**
 * Created by 陈欣健 on 2017/1/17.
 */

public class MainViewModel {
    public ObservableField<Boolean> isLoadJar = new ObservableField<>(false);
    private static MainViewModel ourInstance = new MainViewModel();

    public static MainViewModel getInstance() {
        return ourInstance;
    }

    private MainViewModel() {
    }

    /**
     * 测试方法
     *
     * @param view
     */
    public void invoke(View view) {
        if (invoke != null) {
            Toast.makeText(view.getContext(), invoke.test2(), Toast.LENGTH_SHORT).show();
        }
    }

    File dir = new File(Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + "temp0");

    Invoke invoke;

    /**
     * 加载动态jar
     *
     * @param view
     */
    public void loadJar(final View view) {
        //这里开线程是因为会把 assets的jar写到手机存储,会涉及到io操作,所以尽量在线程上操作
        new Thread(new Runnable() {
            @Override
            public void run() {
                Object object = FileUtil.getInstance().loadJar(view.getContext());
                if (object != null && object instanceof Invoke) {
                    invoke = (Invoke) object;
                    isLoadJar.set(true);
                }
            }
        }).start();

    }
}

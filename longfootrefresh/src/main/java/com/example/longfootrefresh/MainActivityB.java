package com.example.longfootrefresh;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import com.example.longfootrefresh.bean.StaticsBean;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class MainActivityB extends AppCompatActivity implements View.OnClickListener {

    private Handler handler;
    private HandlerThread handlerThread;
    private ImageView imageView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.btn_save).setOnClickListener(this);
        findViewById(R.id.btn_read).setOnClickListener(this);
        initList();

    }
    List<StaticsBean> staticsBeans;
    private void initList() {
        if(staticsBeans==null){
            staticsBeans = new ArrayList<StaticsBean>();
            for (int i = 0;i<10;i++){
                staticsBeans.add(new StaticsBean("name"+i,i));
            }
        }
    }

    public static void setSerializableObject(File file, Object value) throws IOException {
        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
        out.writeObject(value);
        out.flush();
        out.close();
    }
    File file=null;
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btn_save){
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},1);
            }

            file = new File(getSDCardFolder(),"list.xml");
            try {
                file.createNewFile();
                setSerializableObject(file,staticsBeans);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(v.getId()==R.id.btn_read){
            try {
                Object serializableObject = getSerializableObject(file.getAbsolutePath());
                List<StaticsBean> staticsBeans1 = (List<StaticsBean>) serializableObject;
                Gson gson = new Gson();
                String json = gson.toJson(staticsBeans1);
                System.out.println(json);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public static File getSDCardFolder() {
        File root = null;
        if("mounted".equals(Environment.getExternalStorageState())) {
            root = Environment.getExternalStorageDirectory();
        } else {
            root = Environment.getDataDirectory();
        }

        return root;
    }
    public static Object getSerializableObject(String objPath) throws IOException, ClassNotFoundException {
        ObjectInputStream in = new ObjectInputStream(new FileInputStream(objPath));
        Object retValue = in.readObject();
        in.close();
        return retValue;
    }
}

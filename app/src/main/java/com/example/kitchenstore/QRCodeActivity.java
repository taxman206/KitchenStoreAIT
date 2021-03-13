package com.example.kitchenstore;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.zxing.WriterException;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Set;

import androidmads.library.qrgenearator.QRGContents;
import androidmads.library.qrgenearator.QRGEncoder;

public class QRCodeActivity extends AppCompatActivity {
private ImageView qr_code_iv;
    String inputValue="https://kitchenstore-1bd6a.web.app/cart.html?param=";
    Bitmap bitmap;
    QRGEncoder qrgEncoder;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_q_r_code);
        qr_code_iv=findViewById(R.id.qr_code_iv);
        JSONObject json=new JSONObject();
        Set<String> keySet=RvAdapter.cartList.keySet();
        Iterator iterator=keySet.iterator();
        while (iterator.hasNext())
        {
            try {
                json.put(iterator.next().toString(),RvAdapter.cartList.get(iterator.next().toString()));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        String toJsonStr=json.toString();
        try {
            toJsonStr= URLEncoder.encode(toJsonStr,"UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        inputValue+=toJsonStr;


        WindowManager manager = (WindowManager) getSystemService(WINDOW_SERVICE);
        Display display = manager.getDefaultDisplay();
        Point point = new Point();
        display.getSize(point);
        int width = point.x;
        int height = point.y;
        int smallerDimension = width < height ? width : height;
        smallerDimension = smallerDimension * 3 / 4;

        qrgEncoder = new QRGEncoder(
                inputValue, null,
                QRGContents.Type.TEXT,
                smallerDimension);

        try {
            bitmap = qrgEncoder.encodeAsBitmap();

        } catch (WriterException e) {
            e.printStackTrace();
        }

    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        qr_code_iv.setImageBitmap(bitmap);

        //https://kitchenstore-1bd6a-default-rtdb.firebaseio.com/Users/userdemo.json
    }
}
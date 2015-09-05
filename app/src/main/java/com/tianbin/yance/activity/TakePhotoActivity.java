package com.tianbin.yance.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.lidroid.xutils.ViewUtils;
import com.lidroid.xutils.view.annotation.ContentView;
import com.lidroid.xutils.view.annotation.ViewInject;
import com.lidroid.xutils.view.annotation.event.OnClick;
import com.tianbin.yance.R;

@ContentView(R.layout.activity_take_photo)
public class TakePhotoActivity extends AppCompatActivity {

    @ViewInject(R.id.btn_take_photo)
    private Button mBtnTakePhoto;
    @ViewInject(R.id.btn_album)
    private Button mBtnAlbum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ViewUtils.inject(this);
    }

    @OnClick(R.id.btn_take_photo)
    public void mBtnTakePhoto_onClick(View view){
        //获取拍照图片
        //跳转到扫描界面
        Intent intent = new Intent(this,ScanActivity.class);
        startActivity(intent);
        //销毁当前界面
        finish();
    }

    @OnClick(R.id.btn_album)
    public void mBtnAlbum_onClick(View view){

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_take_photo, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

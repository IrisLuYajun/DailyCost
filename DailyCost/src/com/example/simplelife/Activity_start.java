package com.example.simplelife;

import android.os.Bundle;
import android.app.Activity;
import android.app.ActivityManager;
import android.content.Intent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;


public class Activity_start extends Activity  {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);//ע��˳��
        setContentView(R.layout.activity_list);
        getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.title_start);
        setContentView(R.layout.activity_start);
        
        AppManager.getAppManager().addActivity(this);//����Activityʵ����ӵ�AppManager�Ķ�ջ
        
        Button bn=(Button) findViewById(R.id.button1);
        bn.setOnClickListener(new OnClickListener()
        {

			@Override
			public void onClick(View source) {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Activity_start.this,Activity_list.class);
        		startActivity(intent);
			}
			
        });
 
        
    /*--------�˳�����--------------*/
        Button exit=(Button) findViewById(R.id.exit);
        exit.setOnClickListener(new OnClickListener()
        {		
			@Override
        	public void onClick(View source)
        	{
				AppManager.getAppManager().AppExit(Activity_start.this);
        	}
        });
    }
    
}

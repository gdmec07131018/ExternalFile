package cn.edu.gdmec.s07131018.externalfile;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tv,tv1;
	private EditText et;
	private CheckBox cb;
	private final String NAME = "External.txt";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		et = (EditText) findViewById(R.id.et);
		tv = (TextView) findViewById(R.id.tv);
		tv1 = (TextView) findViewById(R.id.tv1);
		cb = (CheckBox) findViewById(R.id.cb);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void Read(View view) {
		FileInputStream fis = null;
		File dir = Environment.getExternalStorageDirectory();
		File file = new File(dir+"/"+NAME);
		String str = "";
		try {
			fis = new FileInputStream(file);
			if (fis.available() == 0) {
				return;
			}
			byte buffer[] = new byte[fis.available()];
			while(fis.read(buffer)!=-1){
			str += new String(buffer);
			}
			tv.setText(str);
			tv1.setText("文件读取成功,文件长度"+str.length());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{
			try {
				if(fis!=null){
					fis.close();
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void Write(View view) {
		File dir = Environment.getExternalStorageDirectory();
		if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)){
			File newFile = new File(dir+"/"+NAME);
			FileOutputStream fos = null;
			try {
				newFile.createNewFile();
				if (newFile.exists()&&newFile.canWrite()){
					fos=new FileOutputStream(newFile);
					fos.write(et.getText().toString().getBytes());
					tv1.setText(NAME+"写入SD卡");
				}
			}catch(IOException e){
				e.printStackTrace();
			}finally{
				if (fos!=null){
					try{
						fos.flush();
						fos.close();
					}catch(IOException e){
						e.printStackTrace();
					}
				}

		}
	}
}
}

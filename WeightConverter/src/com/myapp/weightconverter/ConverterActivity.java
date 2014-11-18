package com.myapp.weightconverter;

import android.app.Dialog;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;



public class ConverterActivity extends ActionBarActivity implements OnClickListener {

	public static double weight = 2.2046;
	private EditText edWeight;
	private TextView txtTitle, txtResulte;
	private RadioButton rbKgLbs, rbLbsKg;
	private Toast msg;
	
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.converter);

    edWeight = (EditText) findViewById(R.id.edWeight);
    txtTitle = (TextView) findViewById(R.id.txtAboutTitle);
    txtResulte = (TextView) findViewById(R.id.txtResulte);
    rbKgLbs = (RadioButton) findViewById(R.id.rbKgLbs);
    rbLbsKg = (RadioButton) findViewById(R.id.rbLbsKg);
	
    rbKgLbs.setOnClickListener(this);
    rbLbsKg.setOnClickListener(this);
    
    }
    
    
    @Override
	public boolean onCreateOptionsMenu(Menu menu) {
    
	getMenuInflater().inflate(R.menu.menu_activity, menu);;
    return (true);
   
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    			
    	switch (item.getItemId()) {
     		
    		case R.id.menu_setting:	
    			Intent setting = new Intent (ConverterActivity.this, SettingActivity.class);
    			startActivity(setting);
                return (true);
    		case R.id.menu_about:
    			Intent about = new Intent (ConverterActivity.this, AboutActivity.class);
    			startActivity(about);
    			return (true);          
     		default:
            	return (super.onOptionsItemSelected(item));
    	}
    }
  
	@Override
	public void onClick(View v) {
		
		String getWeight = edWeight.getText().toString();
		if (getWeight.equals("")) {
			
			final Dialog dialog = new Dialog(this);
				  dialog.requestWindowFeature(Window.FEATURE_LEFT_ICON);
				  dialog.setContentView(R.layout.dialog_view);
			
				  TextView text = (TextView)dialog.findViewById(R.id.dialog_text_view);
				  ImageButton btnDialog = (ImageButton)dialog.findViewById(R.id.dialog_imgBtn);
				  
				  dialog.setFeatureDrawableResource(Window.FEATURE_LEFT_ICON, R.drawable.status_dialog_warning);
				  dialog.setTitle(R.string.dialog_title);
				  text.setText(R.string.dialog_msg);
			
			btnDialog.setOnClickListener(new OnClickListener() {
				
				@Override
				public void onClick(View v) {
					dialog.dismiss();
				}
			});
			
				dialog.show();
				
				rbKgLbs.setChecked(false);
				rbLbsKg.setChecked(false);
				
		} else {
		
			double i = Double.parseDouble(getWeight);
			Resources resources = getResources();
			
			if (v == rbKgLbs) {
				
				rbLbsKg.setChecked(false);
				double w = i * weight;	
				txtTitle.setText(R.string.title_kg);
				txtResulte.setText(i +" "+ resources.getString(R.string.resulte_kg, w));
						
			} else if (v == rbLbsKg) {
				
				rbKgLbs.setChecked(false);
				double w = i / weight;
				txtTitle.setText(R.string.title_lbs);
				txtResulte.setText(i +" "+ resources.getString(R.string.resulte_lbs, w));
			}
		}
		
		edWeight.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
		
			}
			
			@Override
			public void afterTextChanged(Editable s) {
				
				if (s == null || s.length() == 0) {
					rbKgLbs.setChecked(false);
					rbLbsKg.setChecked(false);
					txtTitle.setText("");
					txtResulte.setText("");
					edWeight.setImeOptions(EditorInfo.IME_FLAG_NO_ENTER_ACTION);
				}
				
			}
		});
	}
}

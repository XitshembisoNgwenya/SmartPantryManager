package com.example.smartpantrymanager.screens;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Switch;
import com.example.smartpantrymanager.ui.Ui;
public class SettingsActivity extends Activity {
 @Override public void onCreate(Bundle state){super.onCreate(state);LinearLayout root=Ui.scroll(this);root.addView(Ui.title(this,"Settings"));Ui.gap(this,root,24);
  LinearLayout row=new LinearLayout(this);row.setOrientation(LinearLayout.HORIZONTAL);root.addView(row);row.addView(Ui.text(this,"Enable Expiry Alerts"));Switch toggle=new Switch(this);toggle.setChecked(getPreferences(MODE_PRIVATE).getBoolean("expiry_alerts",true));toggle.setOnCheckedChangeListener((button,checked)->getPreferences(MODE_PRIVATE).edit().putBoolean("expiry_alerts",checked).apply());row.addView(toggle);
 }
}

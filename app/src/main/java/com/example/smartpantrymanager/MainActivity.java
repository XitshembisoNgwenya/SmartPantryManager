package com.example.smartpantrymanager;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.LinearLayout;
import com.example.smartpantrymanager.ui.Ui;
import com.example.smartpantrymanager.screens.*;

public class MainActivity extends Activity {
 @Override public void onCreate(Bundle state){super.onCreate(state);
  LinearLayout column=Ui.column(this);column.setGravity(Gravity.CENTER);setContentView(column);
  ImageView brandLogo=new ImageView(this);brandLogo.setImageResource(R.drawable.nxg_logo);brandLogo.setContentDescription("NxG Technologies logo");brandLogo.setScaleType(ImageView.ScaleType.FIT_CENTER);
  column.addView(brandLogo,new LinearLayout.LayoutParams(Ui.dp(this,88),Ui.dp(this,88)));Ui.gap(this,column,10);
  column.addView(Ui.title(this,"Smart Pantry Manager"));Ui.gap(this,column,20);
  column.addView(Ui.button(this,"Add Ingredient",v->open(AddIngredientActivity.class)));Ui.gap(this,column,10);
  column.addView(Ui.button(this,"View Pantry",v->open(PantryActivity.class)));Ui.gap(this,column,10);
  column.addView(Ui.button(this,"View Recipes",v->open(RecipeActivity.class)));Ui.gap(this,column,10);
  column.addView(Ui.button(this,"Settings",v->open(SettingsActivity.class)));
 }
 private void open(Class<?> page){startActivity(new Intent(this,page));}
}

package com.example.smartpantrymanager.screens;
import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.example.smartpantrymanager.ui.Ui;
import com.example.smartpantrymanager.repository.PantryRepository;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class AddIngredientActivity extends Activity {
 @Override public void onCreate(Bundle state){super.onCreate(state);
  LinearLayout root=Ui.scroll(this);root.addView(Ui.title(this,"Add Ingredient"));Ui.gap(this,root,20);
  EditText name=Ui.field(this,root,"Ingredient Name","");EditText quantity=Ui.field(this,root,"Quantity","");quantity.setInputType(8194);
  EditText unit=Ui.field(this,root,"Unit (e.g. pcs, g, kg, ml, L)","");EditText expiry=Ui.field(this,root,"Expiry Date (optional: yyyy-MM-dd)","");
  Ui.gap(this,root,10);root.addView(Ui.button(this,"Save Ingredient",v->{
   String n=name.getText().toString().trim(),u=unit.getText().toString().trim(),e=expiry.getText().toString().trim();
   Double q=Validation.quantity(quantity.getText().toString());
   if(n.isEmpty()||q==null||u.isEmpty()||!Validation.validUnit(u)||!Validation.date(e)){Ui.message(this,"Enter a name, positive quantity, valid unit and date (yyyy-MM-dd)");return;}
   try(PantryRepository repo=new PantryRepository(this)){repo.addItem(n,q,u,e);Ui.message(this,"Ingredient Saved!");name.setText("");quantity.setText("");unit.setText("");expiry.setText("");}
  }));
 }
}

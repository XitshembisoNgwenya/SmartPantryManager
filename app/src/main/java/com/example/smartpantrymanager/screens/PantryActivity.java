package com.example.smartpantrymanager.screens;
import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import com.example.smartpantrymanager.ui.Ui;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.repository.PantryRepository;
import java.util.List;

public class PantryActivity extends Activity {
 @Override public void onCreate(Bundle b){super.onCreate(b);showItems();}
 @Override protected void onResume(){super.onResume();showItems();}
 private void showItems(){LinearLayout root=Ui.scroll(this);root.addView(Ui.title(this,"Your Pantry"));Ui.gap(this,root,16);
  List<PantryItem> items;try(PantryRepository repo=new PantryRepository(this)){items=repo.getItems();}
  if(items.isEmpty())root.addView(Ui.text(this,"Your pantry is empty. Add an ingredient to get started."));
  for(PantryItem item:items){LinearLayout card=Ui.card(this,root);card.addView(Ui.title(this,item.name));card.addView(Ui.text(this,"Quantity: "+Ui.qty(item.quantity)));card.addView(Ui.text(this,"Unit: "+item.unit));card.addView(Ui.text(this,"Expiry: "+item.expiryDate));LinearLayout actions=new LinearLayout(this);card.addView(actions);actions.addView(Ui.button(this,"Edit",v->edit(item)));actions.addView(Ui.button(this,"Delete",v->new AlertDialog.Builder(this).setTitle("Delete ingredient?").setMessage("Delete "+item.name+"?").setNegativeButton("Cancel",null).setPositiveButton("Delete",(dialog,which)->{try(PantryRepository repo=new PantryRepository(this)){repo.deleteItem(item.id);}showItems();}).show()));}
 }
 private void edit(PantryItem item){LinearLayout form=Ui.column(this);EditText name=Ui.field(this,form,"Ingredient Name",item.name);EditText qty=Ui.field(this,form,"Quantity",Ui.qty(item.quantity));qty.setInputType(8194);EditText unit=Ui.field(this,form,"Unit",item.unit);EditText expiry=Ui.field(this,form,"Expiry Date (yyyy-MM-dd)",item.expiryDate);
 AlertDialog dlg=new AlertDialog.Builder(this).setTitle("Edit Ingredient").setView(form).setNegativeButton("Cancel",null).setPositiveButton("Save",null).create();dlg.setOnShowListener(x->dlg.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(v->{String n=name.getText().toString().trim(),u=unit.getText().toString().trim(),e=expiry.getText().toString().trim();Double q=Validation.quantity(qty.getText().toString());if(n.isEmpty()||q==null||u.isEmpty()||!Validation.validUnit(u)||!Validation.date(e)){Ui.message(this,"Enter a name, positive quantity, valid unit and date");return;}try(PantryRepository repo=new PantryRepository(this)){repo.updateItem(item.id,n,q,u,e);}dlg.dismiss();showItems();}));dlg.show();
 }
}

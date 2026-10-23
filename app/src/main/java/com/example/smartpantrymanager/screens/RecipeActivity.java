package com.example.smartpantrymanager.screens;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.repository.PantryRepository;
import com.example.smartpantrymanager.repository.RecipeMatcher;
import com.example.smartpantrymanager.ui.Ui;
import java.util.List;

public class RecipeActivity extends Activity {
 @Override public void onCreate(Bundle b){super.onCreate(b);}
 @Override protected void onResume(){super.onResume();display();}
 private void display(){LinearLayout root=Ui.scroll(this);root.addView(Ui.title(this,"Suggested Recipes"));Ui.gap(this,root,16);
  List<PantryItem> pantry;List<Recipe> recipes;try(PantryRepository repo=new PantryRepository(this)){pantry=repo.getItems();recipes=repo.getRecipes();}
  root.addView(Ui.text(this,"Pantry Count: "+pantry.size()));Ui.gap(this,root,8);
  for(PantryItem item:pantry){root.addView(Ui.text(this,"Name="+item.name+", Qty="+Ui.qty(item.quantity)+", Unit="+item.unit+", Expiry="+item.expiryDate));}
  Ui.gap(this,root,16);int count=0;
  for(Recipe r:recipes){if(!RecipeMatcher.matches(r,pantry))continue;count++;
   LinearLayout card=Ui.card(this,root);card.addView(Ui.title(this,r.name));StringBuilder names=new StringBuilder();for(Recipe.Requirement req:r.ingredients){if(names.length()>0)names.append(", ");names.append(req.name).append(" (").append(Ui.qty(req.quantity)).append(" ").append(req.unit).append(")");}
   card.addView(Ui.text(this,"Ingredients: "+names));card.addView(Ui.text(this,"Instructions: "+r.steps));card.addView(Ui.button(this,"Recipe Details",v->{Intent i=new Intent(this,RecipeDetailActivity.class);i.putExtra("recipe_id",r.id);startActivity(i);}));
  }
  if(count==0)root.addView(Ui.text(this,"No recipes match your pantry yet - add more ingredients."));
 }
}

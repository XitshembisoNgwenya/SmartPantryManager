package com.example.smartpantrymanager.screens;
import android.app.Activity;
import android.os.Bundle;
import android.widget.LinearLayout;
import com.example.smartpantrymanager.model.Recipe;
import com.example.smartpantrymanager.repository.PantryRepository;
import com.example.smartpantrymanager.ui.Ui;
public class RecipeDetailActivity extends Activity {
 @Override public void onCreate(Bundle b){super.onCreate(b);long id=getIntent().getLongExtra("recipe_id",-1);Recipe r;try(PantryRepository repo=new PantryRepository(this)){r=repo.getRecipe(id);}LinearLayout root=Ui.scroll(this);if(r==null){root.addView(Ui.text(this,"Recipe not found."));return;}
  root.addView(Ui.title(this,r.name));Ui.gap(this,root,16);root.addView(Ui.title(this,"Ingredients"));for(Recipe.Requirement i:r.ingredients)root.addView(Ui.text(this,"• "+Ui.qty(i.quantity)+" "+i.unit+" "+i.name));Ui.gap(this,root,16);root.addView(Ui.title(this,"Method"));root.addView(Ui.text(this,r.steps));
 }
}

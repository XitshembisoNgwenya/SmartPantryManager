package com.example.smartpantrymanager.repository;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import java.util.ArrayList;
import java.util.List;

public class PantryRepository extends SQLiteOpenHelper {
 private static final String NAME="smart_pantry.db";
 public PantryRepository(Context context){super(context,NAME,null,1);}
 @Override public void onCreate(SQLiteDatabase db){
  db.execSQL("CREATE TABLE pantry (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, quantity REAL NOT NULL, unit TEXT NOT NULL, expiry TEXT NOT NULL)");
  db.execSQL("CREATE TABLE recipes (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT NOT NULL, steps TEXT NOT NULL)");
  db.execSQL("CREATE TABLE recipe_ingredients (recipe_id INTEGER NOT NULL, name TEXT NOT NULL, quantity REAL NOT NULL, unit TEXT NOT NULL, FOREIGN KEY(recipe_id) REFERENCES recipes(id))");
  seed(db);
 }
 @Override public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion){}
 public long addItem(String name,double qty,String unit,String expiry){return getWritableDatabase().insert("pantry",null,values(name,qty,unit,expiry));}
 public boolean updateItem(long id,String name,double qty,String unit,String expiry){return getWritableDatabase().update("pantry",values(name,qty,unit,expiry),"id=?",new String[]{String.valueOf(id)})>0;}
 public boolean deleteItem(long id){return getWritableDatabase().delete("pantry","id=?",new String[]{String.valueOf(id)})>0;}
 private ContentValues values(String name,double qty,String unit,String expiry){ContentValues v=new ContentValues();v.put("name",name.trim());v.put("quantity",qty);v.put("unit",unit.trim());v.put("expiry",expiry.trim());return v;}
 public List<PantryItem> getItems(){List<PantryItem> list=new ArrayList<>();try(Cursor c=getReadableDatabase().rawQuery("SELECT id,name,quantity,unit,expiry FROM pantry ORDER BY id DESC",null)){while(c.moveToNext())list.add(new PantryItem(c.getLong(0),c.getString(1),c.getDouble(2),c.getString(3),c.getString(4)));}return list;}
 public List<Recipe> getRecipes(){List<Recipe> list=new ArrayList<>();SQLiteDatabase db=getReadableDatabase();try(Cursor c=db.rawQuery("SELECT id,name,steps FROM recipes ORDER BY id",null)){while(c.moveToNext()){long id=c.getLong(0);List<Recipe.Requirement> req=new ArrayList<>();try(Cursor i=db.rawQuery("SELECT name,quantity,unit FROM recipe_ingredients WHERE recipe_id=?",new String[]{String.valueOf(id)})){while(i.moveToNext())req.add(new Recipe.Requirement(i.getString(0),i.getDouble(1),i.getString(2)));}list.add(new Recipe(id,c.getString(1),c.getString(2),req));}}return list;}
 public Recipe getRecipe(long recipeId){for(Recipe r:getRecipes())if(r.id==recipeId)return r;return null;}
 private void addRecipe(SQLiteDatabase db,String name,String steps,String... components){ContentValues rec=new ContentValues();rec.put("name",name);rec.put("steps",steps);long id=db.insertOrThrow("recipes",null,rec);for(String component:components){String[] parts=component.split("\\|");ContentValues v=new ContentValues();v.put("recipe_id",id);v.put("name",parts[0]);v.put("quantity",Double.parseDouble(parts[1]));v.put("unit",parts[2]);db.insertOrThrow("recipe_ingredients",null,v);}}
 private void seed(SQLiteDatabase db){
  addRecipe(db,"Pancakes","Mix all ingredients into batter. Pour small portions into a pan and cook on each side until golden.","egg|2|pcs","milk|200|ml","flour|150|g");
  addRecipe(db,"Omelette","Beat eggs with milk. Pour into a pan and cook until set.","egg|2|pcs","milk|30|ml");
  addRecipe(db,"French Toast","Beat eggs with milk. Dip bread in mixture and fry on both sides.","egg|2|pcs","milk|100|ml","bread|2|slices");
  addRecipe(db,"Scrambled Eggs","Beat eggs and stir continuously in a warm pan until set.","egg|2|pcs");
  addRecipe(db,"Boiled Eggs","Place eggs in water, bring to a boil and cook until done.","egg|2|pcs","water|500|ml");
  addRecipe(db,"Toast","Place bread in a toaster or dry pan until crisp.","bread|2|slices");
  addRecipe(db,"Cheese Toast","Put cheese onto bread and toast until melted.","bread|2|slices","cheese|40|g");
  addRecipe(db,"Tomato Sandwich","Slice tomato and layer between bread slices.","bread|2|slices","tomato|1|pcs");
  addRecipe(db,"Egg Sandwich","Boil or fry eggs, then put them between slices of bread.","bread|2|slices","egg|2|pcs");
  addRecipe(db,"Banana Smoothie","Blend banana and milk until smooth.","banana|1|pcs","milk|250|ml");
  addRecipe(db,"Banana Pancakes","Mash banana with eggs and flour. Fry spoonfuls until cooked.","banana|1|pcs","egg|2|pcs","flour|70|g");
  addRecipe(db,"Rice","Rinse rice and simmer in water until tender.","rice|100|g","water|200|ml");
  addRecipe(db,"Tomato Rice","Cook rice in water and stir in chopped tomato.","rice|100|g","water|200|ml","tomato|1|pcs");
  addRecipe(db,"Mashed Potatoes","Boil peeled potatoes, then mash with milk.","potato|2|pcs","milk|50|ml");
  addRecipe(db,"Baked Potatoes","Bake potatoes until the centres are tender.","potato|2|pcs");
  addRecipe(db,"Simple Pasta","Boil pasta in water until tender, then drain.","pasta|100|g","water|500|ml");
  addRecipe(db,"Tomato Pasta","Cook pasta in water, drain and toss with chopped tomatoes.","pasta|100|g","water|500|ml","tomato|2|pcs");
  addRecipe(db,"Apple Banana Salad","Chop the apple and banana, and mix in a bowl.","apple|1|pcs","banana|1|pcs");
  addRecipe(db,"Cucumber Tomato Salad","Slice and combine cucumber and tomato.","cucumber|1|pcs","tomato|2|pcs");
  addRecipe(db,"Cheese Omelette","Beat eggs, pour into a pan and top with cheese before folding.","egg|2|pcs","cheese|40|g");
 }
}

package com.example.smartpantrymanager.repository;
import com.example.smartpantrymanager.model.PantryItem;
import com.example.smartpantrymanager.model.Recipe;
import java.util.List;
import java.util.Locale;
import java.util.HashMap;
import java.util.Map;

public final class RecipeMatcher {
 private RecipeMatcher(){}
 public static String canonical(String name){String n=name.toLowerCase(Locale.ROOT).trim().replaceAll("\\s+"," ");
  if(n.equals("tomatoes"))return "tomato";if(n.equals("potatoes"))return "potato";if(n.equals("eggs"))return "egg";
  if(n.equals("bananas"))return "banana";if(n.equals("apples"))return "apple";if(n.equals("cucumbers"))return "cucumber";
  if(n.endsWith("s")&&!n.endsWith("ss")&&!n.endsWith("us")&&n.length()>3)return n.substring(0,n.length()-1);
  return n;
 }
 private static String kind(String unit){String u=unit.toLowerCase(Locale.ROOT).trim();
  if(u.equals("kg")||u.startsWith("kilogram")||u.equals("g")||u.startsWith("gram"))return "mass";
  if(u.equals("l")||u.startsWith("liter")||u.startsWith("litre")||u.equals("ml")||u.startsWith("millil"))return "volume";
  if(u.equals("slice")||u.equals("slices"))return "slices";
  if(u.equals("pc")||u.equals("pcs")||u.startsWith("piece")||u.startsWith("unit"))return "pcs";
  return "unknown:"+u;
 }
 private static double base(double n,String unit){String u=unit.toLowerCase(Locale.ROOT).trim();return (u.equals("kg")||u.startsWith("kilogram")||u.equals("l")||u.startsWith("liter")||u.startsWith("litre")&&!u.startsWith("millil"))?n*1000:n;}
 public static boolean matches(Recipe recipe,List<PantryItem> pantry){Map<String,Double> stocks=new HashMap<>();for(PantryItem item:pantry){String k=canonical(item.name)+"|"+kind(item.unit);stocks.put(k,stocks.getOrDefault(k,0.0)+base(item.quantity,item.unit));}
  for(Recipe.Requirement req:recipe.ingredients){String key=canonical(req.name)+"|"+kind(req.unit);if(stocks.getOrDefault(key,0.0)+1e-9<base(req.quantity,req.unit))return false;}
  return true;
 }
}

package com.example.smartpantrymanager.screens;
import java.text.SimpleDateFormat;
import java.util.Locale;
public class Validation {
 public static Double quantity(String text){try{double v=Double.parseDouble(text.trim());return Double.isFinite(v)&&v>0?v:null;}catch(Exception ex){return null;}}
 public static boolean validUnit(String u){return u.matches("(?i)(g|gram|grams|kg|kilogram|kilograms|ml|milliliter|milliliters|millilitre|millilitres|l|liter|liters|litre|litres|pcs|pc|piece|pieces|unit|units|slice|slices)");}
 public static boolean date(String s){if(s.isEmpty())return true;try{SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd",Locale.ROOT);df.setLenient(false);return s.matches("\\d{4}-\\d{2}-\\d{2}")&&df.parse(s)!=null;}catch(Exception ex){return false;}}
}

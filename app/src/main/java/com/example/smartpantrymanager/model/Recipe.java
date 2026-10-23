package com.example.smartpantrymanager.model;
import java.util.List;
public class Recipe {
 public final long id; public final String name,steps;public final List<Requirement> ingredients;
 public Recipe(long id,String name,String steps,List<Requirement> ingredients){this.id=id;this.name=name;this.steps=steps;this.ingredients=ingredients;}
 public static class Requirement {
  public final String name,unit; public final double quantity;
  public Requirement(String name,double quantity,String unit){this.name=name;this.quantity=quantity;this.unit=unit;}
 }
}

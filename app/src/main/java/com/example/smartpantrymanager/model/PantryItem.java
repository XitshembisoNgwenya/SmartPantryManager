package com.example.smartpantrymanager.model;
public class PantryItem {
 public final long id; public final String name,unit,expiryDate; public final double quantity;
 public PantryItem(long id,String name,double quantity,String unit,String expiryDate){this.id=id;this.name=name;this.quantity=quantity;this.unit=unit;this.expiryDate=expiryDate;}
}

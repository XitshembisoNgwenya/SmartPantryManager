package com.example.smartpantrymanager.ui;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.View;
import android.widget.*;
import android.text.InputType;

public class Ui {
 public static final int PURPLE=Color.rgb(103,80,164);
 public static LinearLayout column(Activity a){LinearLayout l=new LinearLayout(a);l.setOrientation(LinearLayout.VERTICAL);l.setPadding(dp(a,16),dp(a,16),dp(a,16),dp(a,16));l.setBackgroundColor(Color.WHITE);return l;}
 public static LinearLayout scroll(Activity a){ScrollView sc=new ScrollView(a);LinearLayout root=column(a);sc.addView(root);a.setContentView(sc);return root;}
 public static int dp(Activity a,int n){return (int)(a.getResources().getDisplayMetrics().density*n+0.5f);}
 public static TextView title(Activity a,String s){TextView t=text(a,s);t.setTextSize(24);t.setTypeface(null,Typeface.BOLD);return t;}
 public static TextView text(Activity a,String s){TextView t=new TextView(a);t.setText(s);t.setTextSize(16);t.setTextColor(Color.rgb(40,40,40));t.setPadding(0,dp(a,4),0,dp(a,4));return t;}
 public static void gap(Activity a,LinearLayout l,int h){View v=new View(a);l.addView(v,new LinearLayout.LayoutParams(1,dp(a,h)));}
 public static Button button(Activity a,String text,View.OnClickListener listener){Button b=new Button(a);b.setText(text);b.setTextColor(Color.WHITE);b.setAllCaps(false);b.setBackgroundTintList(android.content.res.ColorStateList.valueOf(PURPLE));b.setOnClickListener(listener);return b;}
 public static EditText field(Activity a,LinearLayout parent,String hint,String initial){EditText e=new EditText(a);e.setSingleLine(true);e.setText(initial);e.setHint(hint);parent.addView(e,new LinearLayout.LayoutParams(-1,dp(a,58)));gap(a,parent,10);return e;}
 public static LinearLayout card(Activity a,LinearLayout parent){LinearLayout card=new LinearLayout(a);card.setOrientation(LinearLayout.VERTICAL);card.setPadding(dp(a,16),dp(a,16),dp(a,16),dp(a,16));android.graphics.drawable.GradientDrawable bg=new android.graphics.drawable.GradientDrawable();bg.setColor(Color.rgb(246,243,250));bg.setCornerRadius(dp(a,12));card.setBackground(bg);LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(-1,-2);lp.bottomMargin=dp(a,12);parent.addView(card,lp);return card;}
 public static String qty(double q){return q==Math.rint(q)?String.valueOf((long)q):String.valueOf(q);}
 public static void message(Activity a,String s){Toast.makeText(a,s,Toast.LENGTH_SHORT).show();}
}

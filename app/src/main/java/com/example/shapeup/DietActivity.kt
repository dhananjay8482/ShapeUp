package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView

class DietActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val btn_veg = findViewById<Button>(R.id.diet_plan_veg)
        val btn_nonveg = findViewById<Button>(R.id.diet_plan_nonveg)
        val btn_vegan = findViewById<Button>(R.id.diet_plan_vegan)
        val tv_category = findViewById<TextView>(R.id.diet_tv_category)
        val image_category = findViewById<ImageView>(R.id.diet_image_category)

        btn_veg.setOnClickListener {
            image_category.visibility = View.VISIBLE
            tv_category.setText("Category : Veg Diet")
            image_category.setImageResource(R.drawable.veg_plan)
        }

        btn_nonveg.setOnClickListener {
            image_category.visibility = View.VISIBLE
            tv_category.setText("Category : Non-Veg Diet")
            image_category.setImageResource(R.drawable.nonveg)
        }

        btn_vegan.setOnClickListener {
            image_category.visibility = View.VISIBLE
            tv_category.setText("Category : Vegan Diet")
            image_category.setImageResource(R.drawable.vegan)
        }



    }

    override fun onBackPressed() {
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}



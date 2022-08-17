package com.example.shapeup

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class BMICalculatorActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_bmicalculator)
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val weight = findViewById<EditText>(R.id.weight)
        val height = findViewById<EditText>(R.id.height)
        val result = findViewById<TextView>(R.id.result)
        val btn_calculate = findViewById<Button>(R.id.btn)
        val remark = findViewById<TextView>(R.id.bmi_remark)

        btn_calculate.setOnClickListener {
            val h = (height.text).toString().toFloat() /100 * 30.48
            val w = weight.text.toString().toFloat()
            val res = w/(h*h)
            result.text = "%.2f".format(res)
            if(res<=18.5){
                remark.text = "~ Underweight\nGo For Weight Gain"
            }
            if(res>18.5 && res<=24.9 ){
                remark.text = "~ Normal Weight\nGo For Abs"
            }
            if(res>25 && res<=29.9 ){
                remark.text = "~ Overweight\nGo For Weigt Loss"
            }
            if(res>=30){
                remark.text = "~ Oesity\nGo For Weight Loss"
            }


        }

    }

    override fun onBackPressed() {
        intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
        finish()
    }

}
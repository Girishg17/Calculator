package com.girish.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.math.roundToInt


abstract class MainActivity : AppCompatActivity() {
    private var tvInput: TextView? =null
    var lastNumeric:Boolean=false
    var lastDot:Boolean=false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tvInput=findViewById(R.id.result)
    }
    fun onDigit(view:View){
        lastNumeric=true
        lastDot=false
tvInput?.append((view as Button).text)
    }

    fun onClear(view: View){
        lastNumeric=true
        lastDot=false
       

        tvInput?.text=""
    }
    fun onDecimal(view: View){
if(!lastDot && lastNumeric){
  lastNumeric=false
    lastDot=true
    tvInput?.append((view as Button).text)
}
    }
    fun onOperator(view:View){

        tvInput?.text?.let {
            if(lastNumeric && !isOperatorAdded(it.toString())){
                tvInput?.append((view as Button).text)
                lastNumeric=false
                lastDot=false
            }
        }

    }
    private fun isOperatorAdded(value : String) : Boolean{
        if(value.startsWith("-") && value.length == 1) {
            return false
        }else if (value.contains("/") || value.contains("*") || value.contains("+")){
            return true
        }else if (value.length > 1){
            var temp = value.subSequence(1, value.length-1)
            return temp.contains("-")
        }else{
            return false
        }}
    fun onEqual(view: View){
if(lastNumeric){
    var tvValue=tvInput?.text.toString()
    var prefix=""
    try {
        if(tvValue.startsWith("-")){
            prefix="-"
            tvValue=tvValue.substring(1)
        }
        if(tvValue.contains("-")){
            val splitValue=tvValue.split("-")
            var one=splitValue[0]
            var two=splitValue[1]
            if(prefix.isNotEmpty()){
                one=prefix+one
            }
            var res=one.toDouble()-two.toDouble()
            var re=(res*100.0).roundToInt()/100.0
            tvInput?.text=re.toString()
        }
else  if(tvValue.contains("+")){
            val splitValue=tvValue.split("+")
            var one=splitValue[0]
            var two=splitValue[1]
            if(prefix.isNotEmpty()){
                one=prefix+one
            }
            var res=one.toDouble()+two.toDouble()
            var re=(res*100.0).roundToInt()/100.0
            tvInput?.text=re.toString()
        }
        else  if(tvValue.contains("*")){
            val splitValue=tvValue.split("*")
            var one=splitValue[0]
            var two=splitValue[1]
            if(prefix.isNotEmpty()){
                one=prefix+one
            }
            var res=one.toDouble()*two.toDouble()
            var re=(res*100.0).roundToInt()/100.0
            tvInput?.text=re.toString()
        }
else  if(tvValue.contains("/")){
            val splitValue=tvValue.split("/")
            var one=splitValue[0]
            var two=splitValue[1]
            if(prefix.isNotEmpty()){
                one=prefix+one
            }
            var res=one.toDouble()/two.toDouble()
                var re=(res*100.0).roundToInt()/100.0
            tvInput?.text=re.toString()
        }



    }catch (e:ArithmeticException){
        e.printStackTrace()
    }
}
    }
}
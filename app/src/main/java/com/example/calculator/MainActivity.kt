package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.calculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    var lastNumeric = false
    var stateError = false
    var lastDot = false
    var firstNum = 0.0
    var lastNum = 0.0
    var lastOp = ""
    var isFirst = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun evaluteExp(): Double {
        var result = 0.0
        try {
            result = when{
                lastOp == "-" -> firstNum - lastNum
                lastOp == "+" -> firstNum + lastNum
                lastOp == "*" -> firstNum * lastNum
                lastOp == "/" -> firstNum / lastNum
                lastOp == "%" -> firstNum % lastNum
                else -> firstNum
            }
        }catch (ex : ArithmeticException){
            result = 0.0
            stateError = true
        }
        lastNum = 0.0
        binding.datatv.text = "0"


        return result
    }

    fun onClrClick(view: View) {
        binding.datatv.text = ""
        lastNumeric = false
    }

    fun onMulClick(view: View) {

        val num = binding.datatv.text.toString().toDouble()
        lastOp = "*"

        if(isFirst){
            firstNum = num
            isFirst = false
            lastNum = 1.0
        }
        else {
            lastNum = num
        }

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()

    }

    fun onPlusClick(view: View) {
        val num = binding.datatv.text.toString().toDouble()
        lastOp = "+"

        if(isFirst){
            firstNum = num
            isFirst = false
        }
        else {
            lastNum = num
        }

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()

    }

    fun onMinClick(view: View) {
        val num = binding.datatv.text.toString().toDouble()
        lastOp = "-"

        if(isFirst){
            firstNum = num
            isFirst = false

        }
        else {
            lastNum = num
        }
        binding.resulttv.visibility = View.VISIBLE

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()
    }

    fun onEqualClick(view: View) {
        val num = binding.datatv.text.toString().toDouble()

        lastNum = num.toDouble()

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()
    }

    fun onDotClick(view: View) {}
    fun onAcClick(view: View) {
        binding.datatv.text = "0"
        binding.resulttv.text = ""
        stateError = false
        lastNumeric = false
        lastDot = false
        isFirst = true
        firstNum = 0.0
        lastNum = 0.0
        binding.resulttv.visibility = View.GONE
    }

    fun onDivClick(view: View) {
        val num = binding.datatv.text.toString().toDouble()
        lastOp = "/"

        if(isFirst){
            firstNum = num
            lastNum = 1.0
            isFirst = false
        }
        else {
            lastNum = num
        }

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()
    }

    fun onModClick(view: View) {
        val num = binding.datatv.text.toString().toDouble()
        lastOp = "%"

        if(isFirst){
            firstNum = num
            isFirst = false
        }
        else {
            lastNum = num
        }

        firstNum = evaluteExp()

        binding.resulttv.visibility = View.VISIBLE

        binding.resulttv.text = firstNum.toString()
    }

    fun onDigitClick(view: View) {
        if(binding.datatv.text.toString() == "0"){
            binding.datatv.text = (view as Button).text
        }
        else {
            binding.datatv.append((view as Button).text)
        }

        lastNumeric = true
    }

    fun onBackClick(view: View) {
        binding.datatv.text = binding.datatv.text.toString().dropLast(n = 1)
    }

}
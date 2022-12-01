package com.example.belajar1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView



class MainActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var edtWidth:EditText
    private lateinit var edtHeight: EditText
    private lateinit var edtLength: EditText
    private lateinit var btnCalculate : Button
    private lateinit var tvResult: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        edtWidth=findViewById(R.id.edt_width);
        edtHeight=findViewById(R.id.edt_height);
        edtLength=findViewById(R.id.edt_length);
        btnCalculate=findViewById(R.id.btn_calculate);
        tvResult=findViewById(R.id.tv_result);

        btnCalculate.setOnClickListener(this)
    }

    override fun onClick(view: View?) {
        if (view?.id==R.id.btn_calculate){
            var inputLength =
                edtLength.getText().toString().trim();

            var inputWidth =
                edtWidth.getText().toString().trim();

            var inputHeight =
                edtHeight.getText().toString().trim();

            var isEmptyFields = false
            var isInvalidDouble = false

            if(TextUtils.isEmpty(inputLength)){
                isEmptyFields = true
                edtLength.error="Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputWidth)){
                isEmptyFields=true
                edtWidth.error = "Field ini tidak boleh kosong"
            }
            if (TextUtils.isEmpty(inputHeight)){
                edtHeight.error = "Field ini tidak boleh kosong"
            }
            val panjang = convertToDouble(inputLength);
            val lebar = convertToDouble(inputWidth);
            val tinggi = convertToDouble(inputHeight);

            if (panjang == null){
                isInvalidDouble = true
                edtLength.error = "nilai tidak valid"
            }
            if (lebar == null){
                isInvalidDouble=true
                edtWidth.error = "nilai tidak valid"
            }
            if(tinggi == null){
                isInvalidDouble=true
                edtHeight.error = "nilai tidak valid"
            }

            if(!isEmptyFields&& !isInvalidDouble) {
                val volume = panjang!!.toDouble() * tinggi!!.toDouble() * lebar!!.toDouble()
                tvResult.text = volume.toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle){
        super.onSaveInstanceState(outState, outPersistentState)
        outState.putString(STATE_RESULT,tvResult.text.toString())
    }
    private fun convertToDouble(str:String):Double?{
        return try {
            str.toDouble()
        }catch(e:NumberFormatException){
            null
        }
    }

    companion object{
        private const val STATE_RESULT="state_result"
    }
}





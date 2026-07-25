package com.wts.zenipower

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import java.text.NumberFormat
import java.util.Locale

class CalculateCost : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_calculate_cost)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val editBat = findViewById<TextInputEditText>(R.id.edt_bat)
        val editPercent = findViewById<TextInputEditText>(R.id.edt_percent)
        val editKwh = findViewById<TextInputEditText>(R.id.edt_cost_kwh)
        val editEffi = findViewById<TextInputEditText>(R.id.edt_effi_charger)
        val txtResult = findViewById<TextView>(R.id.txtView_result)
        val btnCalculate = findViewById<Button>(R.id.btn_calc)

        btnCalculate.setOnClickListener {

            val editBatStr: String = editBat.text.toString()
            val editPercentStr: String = editPercent.text.toString()
            val editKwhStr: String = editKwh.text.toString()
            val editEffiStr: String = editEffi.text.toString()


            if (editBatStr == "" || editPercentStr == "" || editKwhStr == "" || editEffiStr == "") {
                Snackbar.make(
                    editBat,
                    "Preencha todos os campos!",
                    Snackbar.LENGTH_LONG
                )
                    .show()
            } else {
                val editBatFloat = editBatStr.toFloat()
                val editPercentFloat = editPercentStr.toFloat()
                val editKwhFloat = editPercentStr.toFloat()
                val editEffiFloat = editEffiStr.toFloat()

                //final calculation
                val costCharger = ((editBatFloat/editEffiFloat) * (editPercentFloat) * (editKwhFloat) / 100)
                val valorFormatado = NumberFormat.getCurrencyInstance(Locale("pt", "BR"))
                    .format(costCharger)
                txtResult.text = "Total a pagar: $valorFormatado"
            }
        }


    }
}
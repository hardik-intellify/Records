package com.theintellify.realm_db

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import com.theintellify.realm_db.model.Employee
import io.realm.Realm
import io.realm.RealmResults
import io.realm.kotlin.createObject
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    /**
     * 
     * JUST FOR DEMO PURPOSE
     * TextView SHOULD BE REPLACED BY RECYCLER VIEW FOR FURTHER IMPLEMENTATION.
     *
     * */

    private val db: Realm = Realm.getDefaultInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        showData()
    }

    fun showData(){
        val results : RealmResults<Employee> = db.where(Employee::class.java).findAll()
        var op = ""
        results.forEach {
            op += it.toString() + "\n"
            Log.i("DATA", "==> results: $it")
        }
        tvRecords.text = op
    }

    fun saveRecord(view: View) {
        val name = edtName.text.toString().trim()
        val designation = edtDesg.text.toString().trim()
        if(name.isNotEmpty() && designation.isNotEmpty()) {
            db.executeTransactionAsync({ bgRealm ->
                val employee = bgRealm.createObject<Employee>()
                employee.eName = name
                employee.designation = designation
            }, {
                clearText()
                showData()
            }, { error ->
                Toast.makeText(
                    this@MainActivity,
                    "Transaction failed and was automatically canceled ${error.message}",
                    Toast.LENGTH_SHORT
                ).show()
            })
        }else{
            Toast.makeText(
                this@MainActivity,
                "Seriously?",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    fun clearText(){
        edtDesg.text.clear()
        edtName.text.clear()
    }

    override fun onDestroy() {
        super.onDestroy()
        db.close()
    }
}

package com.vishnevskiypro.secretchat

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.vishnevskiypro.secretchat.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater).also { setContentView(it.root) }

        // Write a message to the database
        val database = Firebase.database
        val myRef = database.getReference("messages")

        binding.btnSend.setOnClickListener {
            myRef.setValue(binding.textInput.text.toString())
        }
        onChangeListener(myRef)

    }

    private fun onChangeListener(dRef: DatabaseReference){
        dRef.addValueEventListener( object : ValueEventListener{

            override fun onDataChange(snapshot: DataSnapshot) {
                binding.apply {
                    textField.append("\n")
                    textField.append(snapshot.value.toString())
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }

        )

    }

}
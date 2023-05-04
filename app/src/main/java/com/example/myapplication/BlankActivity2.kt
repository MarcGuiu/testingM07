package com.example.myapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class BlankActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_blank2)
        val bundle = intent.extras
        val transferencia: Transferencia? = bundle?.getSerializable("transferencia") as Transferencia?

        val bundle2 = Bundle()
        bundle2.putSerializable("transferencia", transferencia)
        val fragment = ResumFragment()
        fragment.arguments = bundle2
        supportFragmentManager.beginTransaction()
//            .add(R.id.resum, fragment)
            .add(R.id.resum, ResumFragment.newInstance(transferencia!!))

            .commit()
    }
}
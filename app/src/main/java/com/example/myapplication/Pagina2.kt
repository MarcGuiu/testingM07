package com.example.myapplication

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch

class Pagina2 : AppCompatActivity() {

    private val items: ArrayList<Transferencia> = ArrayList()
    private lateinit var db: AppDatabase
    private lateinit var searchView: androidx.appcompat.widget.SearchView
    private lateinit var adapter: ListAdapter
    var recyclerView: RecyclerView? = null
    val itemsSearch: ArrayList<Transferencia> = ArrayList()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_pagina2)

        val username = intent.getStringExtra("username")
        var benvingut = findViewById<TextView>(R.id.benvingut)
        val rvList = findViewById<RecyclerView>(R.id.rv_list)
        val transfer = findViewById<Button>(R.id.transfer)
        var money = findViewById<TextView>(R.id.money)
        var import = intent.getStringExtra("import")
        var dinersTotal = intent.getStringExtra("dinersTotal")
        searchView = findViewById(R.id.searchView)
        val query = searchView.query.toString()
        recyclerView = findViewById(R.id.rv_list)

        benvingut.text = "Benvingut !"

        if (dinersTotal == null) {
            dinersTotal = "1000"
        }

        money.text = dinersTotal.toString()

        GlobalScope.launch {
            db = AppDatabase.getInstance(applicationContext)!!
            val transferencies = db.TransferenciaDAO().loadAllTransfers()
            if (!transferencies.isEmpty()) {
                transferencies.forEach{
                    items.add(it)
                }
            }
            MainScope().launch {
                // Set up the RecyclerView adapter
                val adapter = ListAdapter(items, this@Pagina2)
                recyclerView?.adapter = adapter
                //adapter = ListAdapter(items, this)
                itemsSearch.addAll(items)
                rvList.adapter = ListAdapter(itemsSearch, this@Pagina2)
                rvList.layoutManager = LinearLayoutManager(this@Pagina2)
            }
        }

        transfer.setOnClickListener{
            val intent = Intent(this, BlankActivity::class.java)
            this.startActivity(intent)
        }
        searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                filterTransfers(query)

                return false
            }
            override fun onQueryTextChange(newText: String?): Boolean {
                filterTransfers(newText)

                return false
            }
            private fun filterTransfers(text: String?) {
                itemsSearch.clear()
                for (item in items){
                    if (item.concept?.contains(text!!, true) == true){
                        itemsSearch.add(item)
                    }
                }
                rvList.adapter?.notifyDataSetChanged()
            }
        })
    }
}

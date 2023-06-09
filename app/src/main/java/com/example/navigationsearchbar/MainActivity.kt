package com.example.navigationsearchbar

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.*


class MainActivity : AppCompatActivity() {

    private lateinit var adapter : ArrayAdapter<*>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this,android.R.layout.simple_list_item_1,resources.getStringArray(R.array.countries_array))
        val lv_listView = findViewById<ListView>(R.id.lv_listView)
        lv_listView.adapter = adapter
        lv_listView.onItemClickListener = AdapterView.OnItemClickListener{
            parent,view,position,id ->
            Toast.makeText(applicationContext, parent?.getItemAtPosition(position).toString(),Toast.LENGTH_SHORT).show()
        }

        val tv_emptyTextView= findViewById<TextView>(R.id.emptyTextView)

        lv_listView.emptyView=tv_emptyTextView

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.nav_menu,menu)

        val search=menu?.findItem(R.id.nav_search)
        val searchView= search?.actionView as SearchView
        searchView.queryHint = "Search something"

        searchView.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query:String?): Boolean{
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                adapter.filter.filter(newText)
                return true
            }
        })

        return super.onCreateOptionsMenu(menu)
    }
}
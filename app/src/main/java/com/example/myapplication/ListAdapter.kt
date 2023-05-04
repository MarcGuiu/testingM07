    package com.example.myapplication

    import android.content.Context
    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import android.widget.TextView
    import androidx.core.content.ContextCompat.startActivity
    import androidx.recyclerview.widget.RecyclerView
    import kotlinx.coroutines.GlobalScope
    import kotlinx.coroutines.launch


    class ListAdapter(val items: ArrayList<Transferencia>, val context: Context): RecyclerView.Adapter<ListAdapter.ViewHolder>() {

        private val originalList: MutableList<Transferencia> = mutableListOf()
        val database = AppDatabase.getInstance(context)

        init {
            originalList.addAll(items)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(context).inflate(R.layout.item_list, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val item = items[position]
            holder.tvItem.text = item.concept
            holder.tvItem2.text = item.importDiners.toString() + " €"

            holder.btnDelete.setOnClickListener{
                items.removeAt(position)
                notifyDataSetChanged()
                GlobalScope.launch {
                    val dao = database?.TransferenciaDAO()

                    // Eliminar la transferencia de la base de datos
                    if (dao != null) {
                        item.id?.let { it1 -> dao.deleteTransferenciaById(it1) }
                    }
                }
            }
            holder.btnEdit.setOnClickListener{
                val intent = Intent(context, BlankActivity::class.java)
                context.startActivity(intent)
            }

            holder.tvItem.setOnClickListener{
                val transferencia = items[position]
                val bundle = Bundle()
                bundle.putSerializable("transferencia", transferencia)
                val intent = Intent(context, BlankActivity2::class.java)
                intent.putExtras(bundle)
                context.startActivity(intent)
            }
        }

        override fun getItemCount(): Int {
            return items.size
        }

        class ViewHolder(view: View): RecyclerView.ViewHolder(view){
            val tvItem = view.findViewById<TextView>(R.id.tv_item)
            val tvItem2 = view.findViewById<TextView>(R.id.tv_item2)
            val btnDelete = view.findViewById<Button>(R.id.button7)
            val btnEdit = view.findViewById<Button>(R.id.button2)
        }

        fun updateList(newList: List<Transferencia>) {
            items.clear()
            if (newList.isEmpty()) {
                items.addAll(originalList)
            } else {
                items.addAll(newList)
            }
            notifyDataSetChanged()
        }

    }

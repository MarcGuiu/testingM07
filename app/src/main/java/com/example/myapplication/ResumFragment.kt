package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class ResumFragment : Fragment() {

    private var transferencia: Transferencia? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            transferencia = it.getSerializable("transferencia") as Transferencia
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_resum, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val nomResum = view.findViewById<TextView>(R.id.nomResum)
        val concepteResum = view.findViewById<TextView>(R.id.concepteResum)
        val telefonResum = view.findViewById<TextView>(R.id.telefonResum)
        val importResum = view.findViewById<TextView>(R.id.importResum)
        val btnReturn = view.findViewById<Button>(R.id.btnRetorn)

        val id = transferencia?.id.toString()
        val telefon = transferencia?.telefon.toString()
        val concepte = transferencia?.concept
        val import = transferencia?.importDiners.toString()

        nomResum.text = "Id: $id"
        telefonResum.text = "Telefon: $telefon"
        concepteResum.text = "Concepte: $concepte"
        importResum.text = "Import: $import"

        btnReturn.setOnClickListener {
            val intent = Intent(context, Pagina2::class.java)
            startActivity(intent)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(transferencia: Transferencia) =
            ResumFragment().apply {
                arguments = Bundle().apply {
                    putSerializable("transferencia", transferencia)
                }
            }
    }
}

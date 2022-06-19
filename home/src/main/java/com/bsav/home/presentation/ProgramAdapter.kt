package com.bsav.home.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bsav.core.utils.loadImageFromPathWithBaseUrl
import com.bsav.home.databinding.ItemProgramBinding
import com.bsav.home.domain.model.Program

class ProgramAdapter(
    private val programs: List<Program> = emptyList(),
    private val onClickProgram: OnClickProgram
) : RecyclerView.Adapter<ProgramAdapter.ViewHolder>() {

    inner class ViewHolder(private val binding: ItemProgramBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(program: Program) {
            with(binding) {
                textPoster.text = program.name
                program.posterPath?.let {
                    imgPoster.loadImageFromPathWithBaseUrl(it)
                } ?: {
                    imgPoster.visibility = View.GONE
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(ItemProgramBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val program = programs[position]
        viewHolder.bind(program)
        viewHolder.itemView.setOnClickListener {
            onClickProgram.goToDetails(program.id, program.type)
        }
    }

    override fun getItemCount() = programs.size
}

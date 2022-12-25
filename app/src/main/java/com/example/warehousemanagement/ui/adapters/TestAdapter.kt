package com.example.warehousemanagement.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.warehousemanagement.data.model.Date
import com.example.warehousemanagement.data.model.Items
import com.example.warehousemanagement.databinding.SingleBrandLayoutBinding

class TestAdapter  :
    ListAdapter<Date, TestAdapter.TestViewHolder>(
        ItemsCallback()
    ) {

    private lateinit var itemClickListener: (Date, Int) -> Unit

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TestViewHolder {
        val binding =
            SingleBrandLayoutBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return TestViewHolder(binding)
    }


    override fun onBindViewHolder(holder: TestViewHolder, position: Int) {
        holder.bindData()
    }

    inner class TestViewHolder(private val binding: SingleBrandLayoutBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private var model: Date? = null

        fun bindData() {
            model = getItem(adapterPosition)
            binding.apply {

                binding.tvLtd.text = model?.date

            }
            binding.card.setOnLongClickListener {
                itemClickListener.invoke(model!!, adapterPosition)
                true
            }

//            binding.tvItemCode.setOnClickListener {
//                val action = DashboardFragmentDirections.actionDashboardFragmentToItemsFragment()
//                binding.tvItemCode.findNavController().navigate(action)
//            }
        }
    }

    fun setOnItemClickListener(clickListener: (Date, Int) -> Unit) {
        itemClickListener = clickListener
    }

}

class ItemsCallback :
    DiffUtil.ItemCallback<Date>() {
    override fun areItemsTheSame(
        oldItem: Date,
        newItem: Date
    ): Boolean {
        return oldItem.date == newItem.date
    }

    override fun areContentsTheSame(
        oldItem: Date,
        newItem: Date
    ): Boolean {
        return oldItem == newItem
    }


}
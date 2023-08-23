package com.appearnings.baseapp.ui

import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.appearnings.baseapp.databinding.DialogSelectServiceBinding
import com.appearnings.baseapp.databinding.RawServiceHeaderBinding
import com.appearnings.common.BaseBottomSheetFragment

class DialogSelectService :
    BaseBottomSheetFragment<DialogSelectServiceBinding>(DialogSelectServiceBinding::inflate),
    OnClickListener {

    private val sectionList = ArrayList<ServiceList>()
    private var adapter: DummyAdapter? = null

    override fun init() {
        super.init()
        setDataList()
    }

    private fun setDataList() {
        val serviceList: List<ServiceList> = listOf(
            ServiceList("A-Service", false),
            ServiceList("A-Service", false),
            ServiceList("B-Service", false),
            ServiceList("B-Service", false),
            ServiceList("C-Service", false),
            ServiceList("C-Service", false),
        )

        getHeaderTitle(serviceList)
    }

    // For getting first character
    private fun getHeaderTitle(serviceList: List<ServiceList>) {
        serviceList.sortedWith { serviceList1, list_2 ->
            serviceList1.name[0].uppercase().compareTo(list_2.name[0].uppercase())
        }

        var lastHeader = ""
        val size = serviceList.size

        for (i in 0 until size) {
            val service = serviceList[i]
            val header = service.name[0].uppercase()

            if (lastHeader != header) {
                lastHeader = header
                sectionList.add(ServiceList(header, true))
            }

            sectionList.add(service)
        }
    }

    override fun setVariables() {
        super.setVariables()
        binding.listener = this
        adapter = DummyAdapter()
    }

    override fun setUpViews() {
        super.setUpViews()
        adapter?.bindData(sectionList)
        binding.rvServiceList.adapter = adapter
    }

    override fun onClick(view: View?) {
        binding.apply {
            when (view) {
                tvBack -> {
                    this@DialogSelectService.dismiss()
                }
            }
        }
    }

    // Dummy Data Adapter
    private inner class DummyAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


        var mServiceList: List<ServiceList>? = null

        val HEADER_VIEW = 0
        val CONTENT_VIEW = 1
        private var selected_service = -1

        fun bindData(serviceList: List<ServiceList>) {
            this.mServiceList = serviceList
        }

        inner class HeaderHolder(val headerBinding: RawServiceHeaderBinding) :
            RecyclerView.ViewHolder(headerBinding.root)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
            val inflater = LayoutInflater.from(parent.context)
            return HeaderHolder(RawServiceHeaderBinding.inflate(inflater, parent, false))
        }

        override fun getItemViewType(position: Int): Int {
            return HEADER_VIEW
        }

        override fun getItemCount(): Int {
            return mServiceList?.size?:0
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
            val sectionHolder: HeaderHolder = holder as HeaderHolder
            val sectionItem = mServiceList?.get(position)
            sectionHolder.headerBinding.tvHeader.text = sectionItem?.name
            return
        }

    }
}

// Dummy data class for test
data class ServiceList(val name: String, val isSection: Boolean)
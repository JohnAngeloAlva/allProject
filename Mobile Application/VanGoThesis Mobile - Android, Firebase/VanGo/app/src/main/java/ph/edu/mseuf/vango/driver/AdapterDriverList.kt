package ph.edu.vangodriver

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.edu.mseuf.vango.R

class AdapterDriverList (private val driverList: ArrayList<DriverListArray>) : RecyclerView.Adapter<AdapterDriverList.myViewHolder>(){
    private lateinit var mlistener: onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdapterDriverList.myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.operator_driver_item, parent, false)
        return myViewHolder(itemView, mlistener)

    }

    override fun onBindViewHolder(holder: AdapterDriverList.myViewHolder, position: Int) {


        val currentItem = driverList[position]


        holder.destination.text = currentItem.destination
        holder.name.text = currentItem.fullName
        holder.age.text = currentItem.age

    }




    override fun getItemCount(): Int {

        return driverList.size
    }

    inner class myViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val destination : TextView = itemView.findViewById(R.id.destination)
        val name : TextView = itemView.findViewById(R.id.driverName)
        val age : TextView = itemView.findViewById(R.id.age)


        init{
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }

    }

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }
    fun setOnItemClickListener(listener: onItemClickListener){
        mlistener = listener
    }


}
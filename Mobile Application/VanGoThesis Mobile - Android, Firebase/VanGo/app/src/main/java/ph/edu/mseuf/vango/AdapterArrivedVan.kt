package ph.edu.mseuf.vango

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import ph.edu.vangodriver.SettingArrivedVan

class AdapterArrivedVan (private val vanArrivedList: ArrayList<SettingArrivedVan>) : RecyclerView.Adapter<AdapterArrivedVan.myViewHolder>(){
    private lateinit var mlistener: onItemClickListener

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.van_item, parent, false)
        return myViewHolder(itemView, mlistener)

    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {


        val currentItem = vanArrivedList[position]

        holder.plateNumber.text = currentItem.plateNumber
        holder.terminalItem.text = currentItem.terminal
        holder.account.text = currentItem.emailAccount
        holder.destination.text = currentItem.destination
        holder.name.text = currentItem.driverName
        holder.fare.text = currentItem.fareRate
        holder.id.text = currentItem.id

    }

    override fun getItemCount(): Int {

        return vanArrivedList.size
    }

    inner class myViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){

        val plateNumber : TextView = itemView.findViewById(R.id.plateNumber)
        val terminalItem : TextView = itemView.findViewById(R.id.terminalItem)
        val account : TextView = itemView.findViewById(R.id.email)
        val destination : TextView = itemView.findViewById(R.id.destination)
        val name : TextView = itemView.findViewById(R.id.driverName)
        val fare : TextView = itemView.findViewById(R.id.fareRate)
        val id : TextView = itemView.findViewById(R.id.id)


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
package ph.edu.pos_finals

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView

class MyAdapter(private val soldItem: ArrayList<Sold>) : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.sold_item, parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {

        var currentItem = soldItem[position]

        holder.orderNum.text = currentItem.id
        holder.dateP.text = currentItem.date
        holder.nameEmp.text = currentItem.empName
        holder.flavorP.text = currentItem.flavor
        holder.quantityP.text = currentItem.quanty
        holder.sizeP.text = currentItem.size
        holder.totalP.text = currentItem.total

    }

    override fun getItemCount(): Int {
        return soldItem.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var orderNum : TextView = itemView.findViewById(R.id.textViewOrderNum)
        var dateP : TextView = itemView.findViewById(R.id.textViewDateP)
        var nameEmp : TextView = itemView.findViewById(R.id.textViewNameEmp)
        var flavorP : TextView = itemView.findViewById(R.id.textViewFlavorP)
        var quantityP : TextView = itemView.findViewById(R.id.textViewQuantityP)
        var sizeP : TextView = itemView.findViewById(R.id.textViewSizeP)
        var totalP : TextView = itemView.findViewById(R.id.textViewTotalP)


    }
}
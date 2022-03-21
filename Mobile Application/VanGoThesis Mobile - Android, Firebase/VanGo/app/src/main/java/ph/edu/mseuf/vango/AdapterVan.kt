package ph.edu.mseuf.vango

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*
import ph.edu.vangodriver.Route


class AdapterVan(private val vanList: ArrayList<Route>) : RecyclerView.Adapter<AdapterVan.myViewHolder>(){
    private lateinit var mlistener: onItemClickListener
    private lateinit var database: DatabaseReference
    private lateinit var auth: FirebaseAuth
    public lateinit var clickedPosition : String



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): myViewHolder {

        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.van_item, parent, false)
        return myViewHolder(itemView, mlistener)


    }

    override fun onBindViewHolder(holder: myViewHolder, position: Int) {
        database = FirebaseDatabase.getInstance().getReference("Users")

        val currentItem = vanList[position]

        holder.plateNumber.text = currentItem.plateNumber
        holder.terminalItem.text = currentItem.terminal
        holder.account.text = currentItem.emailAccount
        holder.destination.text = currentItem.destination
        holder.name.text = currentItem.driverName
        holder.fare.text = currentItem.fareRate
        holder.id.text = currentItem.id




    }


    override fun getItemCount(): Int {
        return vanList.size
    }

    inner class myViewHolder(itemView : View, listener: onItemClickListener) : RecyclerView.ViewHolder(itemView){


        val plateNumber : TextView = itemView.findViewById(R.id.plateNumber)
        val terminalItem : TextView = itemView.findViewById(R.id.terminalItem)
        val account : TextView = itemView.findViewById(R.id.email)
        val destination :TextView = itemView.findViewById(R.id.destination)
        val name : TextView = itemView.findViewById(R.id.driverName)
        val fare : TextView = itemView.findViewById(R.id.fareRate)
        val id : TextView = itemView.findViewById(R.id.id)
        val card : CardView = itemView.findViewById(R.id.cardView)


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
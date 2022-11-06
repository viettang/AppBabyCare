package com.viel.readbook.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.viel.readbook.ContendActivity
import com.viel.readbook.R
import com.viel.readbook.adapter.BookAdapter
import com.viel.readbook.adapter.BookItemClickListener
import com.viel.readbook.databinding.FragmentHomeBinding
import com.viel.readbook.model.Book

class HomeFragment : Fragment(),BookItemClickListener {

    private lateinit var binnding: FragmentHomeBinding
    private val books = arrayListOf<Book>()
    private lateinit var adapter: BookAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binnding = FragmentHomeBinding.inflate(inflater,container,false)
        initBooks()
        adapter = BookAdapter(books,this)
        binnding.rvListBook.adapter = adapter
        binnding.rvListBook.layoutManager = LinearLayoutManager(
            context, LinearLayoutManager.VERTICAL,false
        )
        return binnding.root
    }

    private fun initBooks() {
        books.add(
            Book(R.drawable.img_ngaothe,"Ngạo Thế Đan Thần","3808","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_cddp,"Thần Đạo Đan Tôn","5000","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_kdth,"Kiếm Đạo Độc Tôn","400","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_dytn,"Độc Y Thần Nữ","700","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_ht55l,"Hôn TRộm 55 Lần","23","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_ey100n,"Ép Yêu 100 Ngày","17","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_nsdds,"Tuyệt Sắc Đan Dược Sư","67","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_cdbc,"Con Đường Bá Chủ","242","13/09/2021")
        )
        books.add(
            Book(R.drawable.img_nvth,"Linh Vũ Thiên Hạ","43","13/09/2021")
        )
    }

    override fun onBookItemClick(position: Int) {
       if (position==1){
           startContendActivity()
       }
    }

    private fun startContendActivity() {
        val contendIntent = Intent(requireContext(),ContendActivity::class.java)
        startActivity(contendIntent)
    }
}
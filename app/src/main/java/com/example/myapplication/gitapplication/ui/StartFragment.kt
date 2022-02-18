package com.example.myapplication.gitapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.gitapplication.R
import com.example.myapplication.gitapplication.databinding.FragmentStartBinding
import com.example.myapplication.gitapplication.ui.adapters.SimpleItemDecoration
import com.example.myapplication.gitapplication.ui.adapters.SwipeAndDrag
import com.example.myapplication.gitapplication.ui.adapters.UserAdapter


class StartFragment : Fragment() {

    private val viewModel: StartViewModel by lazy {
        ViewModelProvider(this).get(StartViewModel::class.java)
    }

    private var _binding: FragmentStartBinding? = null
    private val binding: FragmentStartBinding
        get() = _binding ?: throw RuntimeException("FragmentStartBinding = null")

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStartBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        binding.recyclerView.adapter = UserAdapter(UserAdapter.OnClickListener {
            viewModel.displayPropertyDetails(it)
        })

        viewModel.navigateToSelectedUser.observe(viewLifecycleOwner) {
            if (it != null) {
                this.findNavController()
                    .navigate(StartFragmentDirections.actionStartFragmentToDetailFragment(it))
                viewModel.displayPropertyDetailsComplete()
            }
        }

        binding.recyclerView.addItemDecoration(SimpleItemDecoration(view.context))

        val itemTouchHelper = object : SwipeAndDrag() {

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val position = viewHolder.adapterPosition
                when (direction) {
                    ItemTouchHelper.LEFT -> {
                        viewModel.removeUser(position)
                        binding.recyclerView.adapter?.notifyItemRemoved(position)
                    }
                }
            }
        }
        //configure left swipe
        itemTouchHelper.leftBG = ContextCompat.getColor(view.context, R.color.deletecolor)
        itemTouchHelper.leftLabel = "Delete Item"

        val itemTouchHelperCallBack = ItemTouchHelper(itemTouchHelper)
        itemTouchHelperCallBack.attachToRecyclerView(binding.recyclerView)
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}
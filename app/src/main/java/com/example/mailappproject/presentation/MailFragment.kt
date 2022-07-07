package com.example.mailappproject.presentation

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.mailappproject.R
import com.example.mailappproject.adapter.EmailAdapter
import com.example.mailappproject.databinding.FragmentMailBinding
import com.example.mailappproject.utils.ConvertUtils
import com.example.mailappproject.viewmodel.MainViewModel

class MailFragment : Fragment() {

    private var _binding: FragmentMailBinding? = null
    private val binding get() = _binding!!
    private val mainViewModel: MainViewModel by activityViewModels()
    private val emailAdapter: EmailAdapter by lazy {
        EmailAdapter()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMailBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initLayout()

    }

    private fun initLayout() {

        binding.fragmentMailRecyclerView.run {
            layoutManager = LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false)
            adapter = emailAdapter
        }

        mainViewModel.checkedNavMenuItem.observe(this.viewLifecycleOwner){ id ->
            Log.e(TAG, "initLayout: mainViewModel.checkedNavMenuItem.observe" )
            binding.fragmentMailType.text = ConvertUtils.getStringById(id)
            emailAdapter.submitList(mainViewModel.dummyEmailData.value?.get(id))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "MailFragment"
    }

}
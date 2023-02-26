package com.bignerdranch.android.criminalintent

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import java.util.UUID

private const val TAG = "CrimeFragment"
private const val ARG_CRIME_ID = "crime_id"
private const val DIALOG_DATE = "DialogDate"

class CrimeFragment : Fragment() {
    private lateinit var crime: Crime
    private lateinit var titleField: EditText
    private lateinit var dateButton: Button
    private lateinit var solvedCheckBox: CheckBox
    private val crimeDetailViewModel: CrimeDetailViewModel by lazy {
        ViewModelProvider(this).get(CrimeDetailViewModel::class.java)
    }

    // 当Fragment与Activity发生关联时调用
    override fun onAttach(context: Context) {
        super.onAttach(context)
        Log.d(TAG, "onAttach")
    }

    // 创建Fragment时被回调
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate")
        crime = Crime()

        // 在创建 Fragment 时，会触发 onCreate 方法，在这个生命周期函数里，拿到创建 fragment 时附带的参数，即 CrimeId
        val crimeId: UUID = arguments?.getSerializable(ARG_CRIME_ID) as UUID
        Log.d(TAG, "args bundle crime ID: $crimeId")

        // 根据 crimeId 去数据库查询对应的 Crime
        crimeDetailViewModel.loadCrime(crimeId)
    }

    // 每次创建、绘制该 Fragment 的 View 组件时回调该方法，Fragment将会显示该方法返回的 View 组件。
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d(TAG, "onCreateView")
        val view = inflater.inflate(R.layout.fragment_crime, container, false)
        titleField = view.findViewById(R.id.crime_title) as EditText
        dateButton = view.findViewById(R.id.crime_date) as Button
        solvedCheckBox = view.findViewById(R.id.crime_solved) as CheckBox
//        dateButton.apply {
//            text = crime.date.toString()
//            isEnabled = false
//        }
        return view
    }

    // onCreateView 方法返回 view 对象后立即调用这个方法，给视图对象添加视图数据
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.d(TAG, "onViewCreated")

        // 当 crimeLiveData 里的数据发生变化时，新数据，即新的 Crime 对象被传递至 lambda 表达式
        // Crime 对象会被赋值给 CrimeFragment 的 crime 属性
        // 最后依据 Crime 对象中存储的数据，来更新 UI
        crimeDetailViewModel.crimeLiveData.observe(viewLifecycleOwner) {
            it?.let {
                this.crime = it
                updateUI()
            }
        }
    }

    // 当 Fragment 所在的Activity被启动完成后回调该方法
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        Log.d(TAG, "onActivityCreated")
    }

    // 启动 Fragment 时被回调，此时Fragment可见
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                crime.title = s.toString()
            }

            override fun afterTextChanged(s: Editable?) {
            }
        }
        titleField.addTextChangedListener(titleWatcher)

        solvedCheckBox.apply {
            setOnCheckedChangeListener {_, isChecked ->
                crime.isSolved = isChecked
            }
        }

        dateButton.setOnClickListener {
            DatePickerFragment().apply {
                // show 方法用于展示日期选择对话框，第一个参数是个 FragmentManager 对象
                // 第二个参数是 String 对象，String参数可唯一识别FragmentManager队列中的DialogFragment。
                // show(manager: FragmentManager, tag: String)
                show(this@CrimeFragment.requireFragmentManager(), DIALOG_DATE)
            }
        }

    }

    // 恢复 Fragment 时被回调，获取焦点时回调。
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    // 暂停 Fragment 时被回调，失去焦点时回调。
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause")
    }

    // 停止 Fragment 时被回调，Fragment不可见时回调。
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop")
        crimeDetailViewModel.saveCrime(crime)
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        Log.d(TAG, "onSaveInstanceState")
    }

    // 销毁与Fragment有关的视图，但未与Activity解除绑定。
    override fun onDestroyView() {
        super.onDestroyView()
        Log.d(TAG, "onDestroyView")
    }

    // 销毁 Fragment 时被回调。
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy")
    }

    // 与onAttach相对应，当Fragment与Activity关联被取消时调用。
    override fun onDetach() {
        super.onDetach()
        Log.d(TAG, "onDetach")
    }

    companion object {
        fun newInstance(crimeId: UUID): CrimeFragment {
            val args = Bundle().apply {
                putSerializable(ARG_CRIME_ID, crimeId)
            }
            return CrimeFragment().apply {
                arguments = args
            }
        }
    }

    private fun updateUI() {
        titleField.setText(crime.title)
        dateButton.text = crime.date.toString()
//        solvedCheckBox.isChecked = crime.isSolved
        solvedCheckBox.apply {
            isChecked = crime.isSolved
            jumpDrawablesToCurrentState()
        }
    }

}
package com.bignerdranch.android.criminalintent

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.telecom.Call
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import java.util.Calendar
import java.util.Date
import java.util.GregorianCalendar

private const val ARG_DATE = "date"

class DatePickerFragment: DialogFragment() {

    interface Callbacks {
        fun onDateSelected(date: Date)
    }

    // 在调用 DatePickerFragment 对象的 show 方法时触发，这个方法会返回一个日期选择对话框
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        // 监听器对象，当用户在日期选择对话框中选择某个日期后，会触发监听器对象的 onDateSet 方法
        // 在 onDateSet 方法内部，我们能拿到用户选择的日期，并将这个日期传回给目标Fragment CrimeFragment
        val dateListener = DatePickerDialog.OnDateSetListener {_: DatePicker, year: Int, month: Int, day: Int ->
            val resultDate: Date = GregorianCalendar(year, month, day).time
            // getTargetFragment 方法拿到 DatePickerFragment 的目标 Fragment，即 CrimeFragment
            // CrimeFragment 类实现了 Callbacks 接口，所以可以强制转换成 Callbacks 对象
            // 转换成 Callbacks 对象后，就可以执行 onDateSelected 方法了。
            targetFragment?.let {
                (it as Callbacks).onDateSelected(resultDate)
            }
        }

        //  用于获取参数，参数是在调用 newInstance 时传递的
        val date = arguments?.getSerializable(ARG_DATE) as Date
        val calendar = Calendar.getInstance()
        calendar.time = date
        val initialYear = calendar.get(Calendar.YEAR)
        val initialMonth = calendar.get(Calendar.MONTH)
        val initialDay = calendar.get(Calendar.DAY_OF_MONTH)

        return DatePickerDialog(
            requireContext(),
            // null,
            dateListener,
            initialYear,
            initialMonth,
            initialDay
        )
    }

    companion object {
        // 创建 DatePickerFragment 对象时，接收 date 参数，最终返回一个携带参数的的 DatePickerFragment 对象
        fun newInstance(date: Date): DatePickerFragment {
            val args = Bundle().apply {
                putSerializable(ARG_DATE, date)
            }

            return DatePickerFragment().apply {
                arguments = args
            }
        }
    }
}
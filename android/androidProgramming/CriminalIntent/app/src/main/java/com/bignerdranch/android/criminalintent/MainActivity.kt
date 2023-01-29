package com.bignerdranch.android.criminalintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import java.util.*

private const val TAG = "MainActivity"

class MainActivity : AppCompatActivity(), CrimeListFragment.Callbacks {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val currentFragment = supportFragmentManager.findFragmentById(R.id.fragment_container)
        if (currentFragment == null) {
            val fragment = CrimeListFragment.newInstance()
            // getSupportFragmentManager 方法获取 FragmentManager 类对象，这个对象用于管理 fragment 队列和 fragment 事务回退栈
            // 有了这个对象，我们就能够创建管理 fragment 的事务，开启事务是调用该对象的 beginTransaction 方法
            // beginTransaction 方法会返回一个 FragmentTransaction 对象
            // 通过这个事务对象，我们便能在 Activity 中添加、移除、附加、分离或者替换 fragment 队列中的 fragment
            // add 方法用于在当前 Activity 中添加一个 fragment 对象，其第一个参数是个资源 ID，其作用如下：
            // 1. fragment 添加到 Activity 中的什么位置
            // 2. 将新添加到 fragment 队列中的 fragment 标识设置为资源 ID 的值
            // 3. FragmentManager 对象能通过这个标识找到 fragment 队列中的 fragment，上面的 findFragmentById 就是个在队列中寻找指定 fragment 的例子。
            // add 方法的第二个参数是个 fragment 对象，这好理解，就是要添加到 Activity 中的 fragment 对象。
            // add 方法执行后，fragment 会被添加到 Activity 中，其会被放到 Activity 的 FrameLayout 布局中，其在 fragment 队列中的标识是 R.id.fragment_container
            // 在 fragment 队列中，FragmentManager 能够根据 R.id.fragment_container 这个标识找到这个 fragment。
            // commit 方法用于提交整个事务，至此，fragment 便成功添加至 Activity 中了。
            supportFragmentManager
                .beginTransaction()
                .add(R.id.fragment_container, fragment)
                .commit()
        }
    }

    override fun onCrimeSelected(crimeId: UUID) {
        Log.d(TAG, "MainActivity.onCrimeSelected: $crimeId")
        val fragment = CrimeFragment.newInstance(crimeId)
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .addToBackStack(null)
            .commit()
    }
}
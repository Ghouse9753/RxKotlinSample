package com.example.rxkotlinsample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.rxjava3.core.Observable
import io.reactivex.rxjava3.disposables.CompositeDisposable
import kotlinx.android.synthetic.main.activity_filter.*

class FilterOperatorsActivity : AppCompatActivity() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTitle(R.string.filter_operators)
        setContentView(R.layout.activity_filter)
        setupClickListeners()
    }

    private fun setupClickListeners() {
        btnFilter.setOnClickListener {
            onHandleFilter()
        }
        btnDistinct.setOnClickListener {
            onHandleDistinct()
        }
        btnTake.setOnClickListener {
            onHandleTake()
        }
        btnTakeWhile.setOnClickListener {
            onHandleTakeWhile()
        }
    }

    //Filter- even numbers
    private fun onHandleFilter() {
        tvOutput.text = ""
        val list = listOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
        tvOperationType.text = getString(R.string.filter)
        tvInput.text = list.toString()
        val disposable = Observable.fromIterable(list)
            .filter { t -> t?.rem(2) == 0 }
            .subscribe({ value -> tvOutput.append("$value ") },
                { error -> tvOutput.text = "${error.message}" })
        compositeDisposable.add(disposable)
    }

    //Distinct
    private fun onHandleDistinct() {
        tvOutput.text = ""
        val list = listOf(1, 2, 2, 5, 5, 6, 7, 7, 9, 9)
        tvOperationType.text = getString(R.string.distinct)
        tvInput.text = list.toString()
        val disposable = Observable.fromIterable(list)
            .distinct()
            .subscribe({ value -> tvOutput.append("$value ") },
                { error -> tvOutput.text = "${error.message}" })
        compositeDisposable.add(disposable)
    }

    //Take - 3 items from given list
    private fun onHandleTake() {
        tvOutput.text = ""
        val list = listOf(1, 2, 2, 5, 5, 6, 7, 7, 9, 9)
        tvOperationType.text = getString(R.string.take)
        tvInput.text = list.toString()
        val disposable = Observable.fromIterable(list)
            .take(3)
            .subscribe({ value -> tvOutput.append("$value ") },
                { error -> tvOutput.text = "${error.message}" })
        compositeDisposable.add(disposable)
    }

    //TakeWhile - until odd number occurs
    private fun onHandleTakeWhile() {
        tvOutput.text = ""
        val list = listOf(2, 2, 2, 6, 5, 6, 7, 7, 9, 9)
        tvOperationType.text = getString(R.string.take_while)
        tvInput.text = list.toString()
        val disposable = Observable.fromIterable(list)
            .takeWhile { t -> t?.rem(2) == 0 }
            .subscribe({ value -> tvOutput.append("$value ") },
                { error -> tvOutput.text = "${error.message}" })
        compositeDisposable.add(disposable)
    }

    override fun onDestroy() {
        super.onDestroy()
        compositeDisposable.clear()
    }
}
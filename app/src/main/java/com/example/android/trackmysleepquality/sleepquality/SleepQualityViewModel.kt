/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.trackmysleepquality.sleepquality

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.android.trackmysleepquality.database.SleepDatabaseDao
import com.example.android.trackmysleepquality.database.SleepNight
import kotlinx.coroutines.*

/**
 * ViewModel for SleepQualityFragment.
 *
 * @param sleepNightKey The key of the current night we are working on.
 */
class SleepQualityViewModel(
           private var sleepNightKey:Long = 0L,
           val database: SleepDatabaseDao) : ViewModel() {

    val navigateToSleepTracker = MutableLiveData<Boolean>()


    fun doneNavigating()
    {
        navigateToSleepTracker.value = null
    }


    /**
     * By default, all coroutines started in viewModelScope will launch in [Dispatchers.Main] which is
     * the main thread on Android. This is a sensible default because most coroutines started by
     * a [ViewModel] update the UI after performing some processing.
     */


    /**
     * Sets the sleep quality and updates the database.
     *
     * Then navigates back to the SleepTrackerFragment.
     */

    fun onSetSleepQuality(quality: Int)
    {
        viewModelScope.launch {
            withContext(Dispatchers.IO)
            {
                val tonight = database.get(sleepNightKey)?:return@withContext
                tonight.sleepQuality = quality
                database.update(tonight)
            }
            navigateToSleepTracker.value = true
        }
    }


    /**
     * Variable that tells the fragment whether it should navigate to [SleepTrackerFragment].
     *
     * This is `private` because we don't want to expose the ability to set [MutableLiveData] to
     * the [Fragment]
     */





    /**
     * When true immediately navigate back to the [SleepTrackerFragment]
     */



    /**
     * Cancels all coroutines when the ViewModel is cleared, to cleanup any pending work.
     *
     * onCleared() gets called when the ViewModel is destroyed.
     */




    /**
     * Call this immediately after navigating to [SleepTrackerFragment]
     */




}


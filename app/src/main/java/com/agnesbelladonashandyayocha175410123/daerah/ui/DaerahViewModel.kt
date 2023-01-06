/*
 * Copyright (C) 2021 The Android Open Source Project.
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
package com.agnesbelladonashandyayocha175410123.daerah.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.agnesbelladonashandyayocha175410123.daerah.network.Daerah
import com.agnesbelladonashandyayocha175410123.daerah.network.DaerahApi
import kotlinx.coroutines.launch

enum class DaerahApiStatus { LOADING, ERROR, DONE }

class DaerahViewModel : ViewModel() {
    private val _status = MutableLiveData<DaerahApiStatus>()
    val status: LiveData<DaerahApiStatus> = _status

    private val _daerahs = MutableLiveData<List<Daerah>>()
    val daerahs: LiveData<List<Daerah>> = _daerahs

    private val _daerah = MutableLiveData<Daerah>()
    val daerah: LiveData<Daerah> = _daerah

    fun getDaerahList() {
        _status.value = DaerahApiStatus.LOADING
        viewModelScope.launch {
            try {
                _daerahs.value = DaerahApi.service.getDaerahs()
                _status.value = DaerahApiStatus.DONE
            } catch (e: Exception) {
                _daerahs.value = emptyList()
                _status.value = DaerahApiStatus.ERROR
            }
        }
    }

    fun onDaerahClicked(daerah: Daerah) {
        _daerah.value = daerah
    }
}

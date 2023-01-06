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

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daerah.databinding.ListViewItemBinding
import com.agnesbelladonashandyayocha175410123.daerah.network.Daerah

/**
 * This class implements a [RecyclerView] [ListAdapter] which uses Data Binding to present [List]
 * data, including computing diffs between lists.
 */
class DaerahListAdapter() :
    ListAdapter<Daerah, DaerahListAdapter.DaerahViewHolder>(DiffCallback) {

    class DaerahViewHolder(
        var binding: ListViewItemBinding
        ) : RecyclerView.ViewHolder(binding.root){
        fun bind(daerah: Daerah) {
            binding.daerah = daerah
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Daerah>() {

        override fun areItemsTheSame(oldItem: Daerah, newItem: Daerah): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Daerah, newItem: Daerah): Boolean {
            return oldItem.nama == newItem.nama
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DaerahViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return DaerahViewHolder(
            ListViewItemBinding.inflate(layoutInflater, parent, false)
        )
    }

    override fun onBindViewHolder(holder: DaerahViewHolder, position: Int) {
        val daerah = getItem(position)
        holder.bind(daerah)
    }
}

class DaerahListener(val clickListener: (daerah: Daerah) -> Unit) {
    fun onClick(daerah: Daerah) = clickListener(daerah)
}

/*
 * Copyright 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package csc.livia.weatherapp.data.source.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

/**
 * Data Access Object for the task table.
 */
@Dao
interface WeatherDao {

    /**
     * Select cities
     *
     */
    @Query("SELECT * FROM city WHERE city.favorite=1")
    fun observeFavorite(): Flow<List<LocalCity>>


    @Query("SELECT * FROM city WHERE id = :cityid")
    fun observeById(cityid: String): Flow<LocalCity>


    @Query("SELECT * FROM city WHERE city.favorite=1")
    suspend fun getAll(): List<LocalCity>


    @Query("SELECT * FROM city WHERE id = :cityid")
    suspend fun getById(cityid: String): LocalCity?


    @Upsert
    suspend fun upsert(city: LocalCity)


    @Upsert
    suspend fun upsertAll(city: List<LocalCity>)


    @Query("DELETE FROM city WHERE id = :cityid")
    suspend fun deleteById(cityid: String): Int


    @Query("DELETE FROM city")
    suspend fun deleteAll()


}

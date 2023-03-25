package com.route.data.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.route.data.model.SourcesItem
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NewsDatabaseTest {

    // database
    // Dao
    lateinit var database: NewsDatabase
    lateinit var dao: SourcesDao

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        database = Room.inMemoryDatabaseBuilder(context, NewsDatabase::class.java).build()
        dao = database.getNewsDao()
    }

    @Test // Target Unit  _      Context  _       Expected
    fun saveSources_WithEmptyList_Then_ReturnEmptyList() = runTest {
        //arrange
        val emptySourcesList = emptyList<SourcesItem>()
        //act
        dao.saveSources(emptySourcesList)
        val items = dao.getSources()
        // assert
        assertEquals(emptyList<SourcesItem>(), items)
    }

    @Test
    fun savingSources_With3ItemsList_then_return3ItemsList() = runTest {
        val threeItemsList = listOf<SourcesItem>(
            SourcesItem(id = "1"),
            SourcesItem(id = "2"),
            SourcesItem(id = "3"),
        )
        dao.saveSources(threeItemsList)
        val items = dao.getSources()
        assertTrue(items.isNotEmpty())

        //Sorting
        //SourcesItem("3")
        //SourcesItem("1")
        //SourcesItem("2")
        //Searching
        //Insert items , search for items
    }

    @Test
    fun saveSources_with_category_thenReturnOnlySourcesWithThatCategory() = runTest {
        //Arrange
        val category1 = "sports"
        val category2 = "technology"
        val sourcesItem1 = SourcesItem(id = "1", category = category1)
        val sourcesItem2 = SourcesItem(id = "2", category = category2)

        //Act
        dao.saveSources(listOf(sourcesItem1, sourcesItem2))
        val result = dao.getSourcesByCategory("sports")

        //Assert
        assertEquals(SourcesItem(id = "1", category = "sports").id, result[0].id)
    }


}
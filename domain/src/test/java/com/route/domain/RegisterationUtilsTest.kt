package com.route.domain

import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test

class RegisterationUtilsTest {
    // Create Function name default way
    // Create Function name by description
    /**
     * Function naming :-
     *     1- Target Unit
     *     2- Context -> Params you will send
     *     3- Expected ->
     */
    lateinit var registerationUtils: RegisterationUtils

    //Before starting Unit Tests
    @Before
    fun setUp() {
        registerationUtils = RegisterationUtils()
    }

    @After
    fun clean() {
        //database.close()
    }

    @Test
    fun `validateRegisterData() with blank user name then returns false`() {
        // Triple - A Rule
        // Arrange
        val userName = ""
        val password = "123456"
        val passwordCOnfirmation = "123456"
        //Act
        val result =
            registerationUtils.validateRegisterData(userName, password, passwordCOnfirmation)
        // Assert
        assertFalse(result)
    }

    @Test // Target Unit          // Context                    //Expected
    fun `validateRegisterData() with password less than 6 chars then return false`() {
        // Arrange
        val userName = "Ahmed"
        val password = "123"
        val passwordConfirmation = "123"

        // Act
        val result =
            registerationUtils.validateRegisterData(userName, password, passwordConfirmation)
        // Assert

        // Expected -> Correct Output
        // Actual -> Actual Output
        assertFalse(result)
        assertEquals(false, result)
    }

    @Test
    fun `validateRegisterData() with correct username and correct password and correct password confirmation then return true`() {
        //Arrange
        val userName = "Ahmed"
        val password = "123456"
        val passwordConfirmation = "123456"

        //Act
        val result =
            registerationUtils.validateRegisterData(userName, password, passwordConfirmation)
        //Assert

        //End-to-end testing
        //Automating testing
        assertTrue(result)
//        assertEquals()
    }

}
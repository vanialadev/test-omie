package br.com.vaniala.omie.domain.usecase.login

import br.com.vaniala.omie.domain.model.UserModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.kotlin.atLeastOnce

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 20/03/23.
 *
 */

@ExperimentalCoroutinesApi
class AuthenticateUseCaseTest {

    private lateinit var searchUserByEmailUseCase: SearchUserByEmailUseCase
    private lateinit var addEmailDatastoreUseCase: AddEmailDatastoreUseCase
    private lateinit var addIdlDatastoreUseCase: AddIdlDatastoreUseCase

    @Before
    fun setUp() {
        Dispatchers.setMain(Dispatchers.Unconfined)
    }

    private fun setupUseCase(
        searchUserByEmailUseCase: SearchUserByEmailUseCase = mock(SearchUserByEmailUseCase::class.java),
        addEmailDatastoreUseCase: AddEmailDatastoreUseCase = mock(
            AddEmailDatastoreUseCase::class.java,
        ),
        addIdlDatastoreUseCase: AddIdlDatastoreUseCase = mock(
            AddIdlDatastoreUseCase::class.java,
        ),
    ): AuthenticateUseCase {
        this.searchUserByEmailUseCase = searchUserByEmailUseCase
        this.addEmailDatastoreUseCase = addEmailDatastoreUseCase
        this.addIdlDatastoreUseCase = addIdlDatastoreUseCase
        return AuthenticateUseCase(
            this.searchUserByEmailUseCase,
            this.addEmailDatastoreUseCase,
            this.addIdlDatastoreUseCase,
        )
    }

    @Test
    fun userNotFound() {
        val email = "email"
        val password = "password"

        val usecase = setupUseCase(
            searchUserByEmailUseCase = mock(SearchUserByEmailUseCase::class.java) {
                throw Exception("No user found")
            },
        )
        runTest {
            val result = usecase(email, password)
            verify(searchUserByEmailUseCase, atLeastOnce()).invoke(email)
            assertEquals(AuthenticateUseCase.Result.Failure, result)
        }
    }

    @Test
    fun passwordsDoNotMatch() {
        val email = "email"
        val dbPassword = "user's actual password"
        val methodInputPassword = "password"

        val usecase = setupUseCase(
            searchUserByEmailUseCase = mock(SearchUserByEmailUseCase::class.java) {
                UserModel(0, "name", "email", dbPassword)
            },
        )

        runTest {
            val result = usecase(email, methodInputPassword)
            verify(searchUserByEmailUseCase, atLeastOnce()).invoke(email)
            assertEquals(AuthenticateUseCase.Result.Failure, result)
        }
    }

    @Test
    fun failure() {
        val email = "email"
        val methodInputPassword = "password"

        val usecase = setupUseCase(
            searchUserByEmailUseCase = mock(SearchUserByEmailUseCase::class.java) {
                UserModel(0, "name", "email", methodInputPassword)
            },
        )

        runTest {
            val result = usecase(email, methodInputPassword)
            verify(searchUserByEmailUseCase, atLeastOnce()).invoke(email)
            assertEquals(AuthenticateUseCase.Result.Failure, result)
        }
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }
}

package br.com.vaniala.omie.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import br.com.vaniala.omie.database.entity.UserEntity
import kotlinx.coroutines.flow.Flow

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(user: UserEntity)

    @Query("SELECT * FROM USER WHERE email = :email")
    fun searchByEmail(email: String): Flow<UserEntity?>

    @Query(
        """ 
        SELECT * FROM USER  
        WHERE email = :email  
        AND password = :password""",
    )
    fun authenticate(
        email: String,
        password: String,
    ): Flow<UserEntity?>
}

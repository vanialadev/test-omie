package br.com.vaniala.omie.database.db

import androidx.room.Database
import androidx.room.RoomDatabase
import br.com.vaniala.omie.database.dao.UserDao
import br.com.vaniala.omie.database.entity.UserEntity

/**
 * Created by Vânia Almeida (Github: @vanialadev)
 * on 17/03/23.
 *
 */
@Database(
    version = 1,
    entities = [
        UserEntity::class,
    ],
    exportSchema = true,
)
abstract class OmieDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
}

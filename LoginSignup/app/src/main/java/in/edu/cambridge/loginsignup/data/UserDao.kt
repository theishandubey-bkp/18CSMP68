package `in`.edu.cambridge.loginsignup.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

@Dao
interface UserDao {
    @Query("SELECT * FROM user WHERE username == :username")
    fun getUser(username: String): User

    @Insert
    fun insert(user: User)

}
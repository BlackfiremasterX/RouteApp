import android.location.Location
import kotlinx.coroutines.flow.Flow

interface LocationClient {
    fun getLocationUpdates(): Flow<Location>

    fun getLastLocation(): Flow<Location>
}
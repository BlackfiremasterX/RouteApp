import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.location.LocationManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.android.deepdweller.maprouteapp_nojetpack.utils.extensions.hasLocationPermission
import com.android.deepdweller.maprouteapp_nojetpack.presentation.MainActivity
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices

class LocationUtils(private val context: Context) {
    private var currentLatitude: Double = 0.0
    private var currentLongitude: Double = 0.0


    private lateinit var fusedLocationClient: FusedLocationProviderClient

    private val LOCATION_PERMISSION_REQUEST_CODE = 1001

    init {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
    }

    fun requestLocation(onLocationListener: OnLocationListener) {
        if (ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(context as MainActivity, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), LOCATION_PERMISSION_REQUEST_CODE)
        } else {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    onLocationListener.onLocationAvailable(location.latitude, location.longitude)
                }
            }
        }
    }

    fun isLocationAvailable(context: Context): Boolean {
        val locationManager =
            context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        val gps = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)
        val permission = context.hasLocationPermission()
        return gps && permission
    }

    interface OnLocationListener {
        fun onLocationAvailable(latitude: Double, longitude: Double)
    }




}
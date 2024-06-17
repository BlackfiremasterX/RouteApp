package com.android.deepdweller.maprouteapp_nojetpack.presentation.map

import LocationUtils
import android.content.Context
import android.graphics.Color
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.inputmethod.EditorInfo
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import com.android.deepdweller.maprouteapp_nojetpack.R
import com.android.deepdweller.maprouteapp_nojetpack.databinding.FragmentMapBinding
import com.android.deepdweller.maprouteapp_nojetpack.presentation.MapBehaviour.setMarker
import com.android.deepdweller.maprouteapp_nojetpack.presentation.base.BaseFragment
import com.android.deepdweller.maprouteapp_nojetpack.utils.ImageFactory
import com.android.deepdweller.maprouteapp_nojetpack.utils.viewBinding
import com.yandex.mapkit.Animation
import com.yandex.mapkit.MapKitFactory
import com.yandex.mapkit.RequestPoint
import com.yandex.mapkit.RequestPointType
import com.yandex.mapkit.directions.DirectionsFactory
import com.yandex.mapkit.directions.driving.DrivingOptions
import com.yandex.mapkit.directions.driving.DrivingRoute
import com.yandex.mapkit.directions.driving.DrivingRouter
import com.yandex.mapkit.directions.driving.DrivingRouterType
import com.yandex.mapkit.directions.driving.DrivingSession
import com.yandex.mapkit.directions.driving.VehicleOptions
import com.yandex.mapkit.geometry.Point
import com.yandex.mapkit.map.CameraListener
import com.yandex.mapkit.map.CameraPosition
import com.yandex.mapkit.map.CameraUpdateReason
import com.yandex.mapkit.map.PlacemarkMapObject
import com.yandex.runtime.image.ImageProvider
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import timber.log.Timber
import javax.inject.Inject

class MapFragment : BaseFragment(R.layout.fragment_map) {

    @Inject
    lateinit var imageFactory: ImageFactory
    private lateinit var locationUtils: LocationUtils
    private val binding by viewBinding(FragmentMapBinding::bind)
    private val viewModel: MapViewModel by viewModels()
    private var myLocationPlaceMark: PlacemarkMapObject? = null
    lateinit var  api: String
    private lateinit var mapKitApi: Job

    private val cameraListener = CameraListener { _, cameraPosition, cameraUpdateReason, _ ->
        if (cameraUpdateReason == CameraUpdateReason.GESTURES) {
            if (isMovedToMarker) {
                binding.myLocation.setImageResource(R.drawable.icon_round)
                myLocationPlaceMark?.setMarker(
                    false,
                    imageFactory = imageFactory
                )
            }
            isMovedToMarker = false
        }
    }
    private var isMovedToMarker = false
    private lateinit var drivingRouter: DrivingRouter
    private var routePoints = emptyList<Point>()
        set(value) {
            field = value
        }


    private fun initMapsApi()
    {
        api = viewModel.getApiKey().toString()
        MapKitFactory.setApiKey("$api")
        MapKitFactory.initialize(requireContext())
        binding.mapview.onStart()
    }

    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        initRouteData()
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        mapKitApi = Job()
        val scope = CoroutineScope(Dispatchers.Main + mapKitApi)
        scope.launch {
            viewModel.apiKeyFlow.collect { apiKey ->
                initMapsApi()
                Log.d("Observer", "New apiKey value: $apiKey")
            }
        }
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onDestroyView() {
        MapKitFactory.getInstance().onStop()
        binding.mapview.onStop()
        super.onDestroyView()
        mapKitApi.cancel()
    }


    fun initRouteData() {
        val drivingRouter =
            DirectionsFactory.getInstance().createDrivingRouter(DrivingRouterType.COMBINED)

        val drivingOptions = DrivingOptions().apply {
            routesCount = 1
        }

        val vehicleOptions = VehicleOptions()

        val requestPoints = mutableListOf<RequestPoint>()

        getCurrentLocation { currentPoint ->
            requestPoints.add(
                RequestPoint(
                    currentPoint,
                    RequestPointType.WAYPOINT,
                    null,
                    null
                )
            )
            requestPoints.add(
                RequestPoint(
                    Point(56.833742, 60.635716),
                    RequestPointType.WAYPOINT,
                    null,
                    null
                )
            )
            val drivingSession = drivingRouter.requestRoutes(
                requestPoints,
                drivingOptions,
                vehicleOptions,
                object : DrivingSession.DrivingRouteListener {
                    override fun onDrivingRoutes(drivingRoutes: MutableList<DrivingRoute>) {


                        val drivingRoute = drivingRoutes[0]
                        val mapObjects = binding.mapview.map.mapObjects


                        val line = mapObjects.addPolyline(drivingRoute.geometry)
                        line.setStrokeColor(Color.DKGRAY)


                        val startPlacemark =
                            mapObjects.addPlacemark(drivingRoute.geometry.points[0])
                        startPlacemark.opacity = 0.8f
                        startPlacemark.setIcon(
                            ImageProvider.fromResource(
                                requireContext(),
                                R.drawable.ic_launcher_foreground
                            )
                        )

                        val endPlacemark =
                            mapObjects.addPlacemark(drivingRoute.geometry.points.last())
                        endPlacemark.opacity = 0.8f
                        endPlacemark.setIcon(
                            ImageProvider.fromResource(
                                requireContext(),
                                R.drawable.ic_launcher_foreground
                            )
                        )

                        val routeCenter =
                            drivingRoute.geometry.points[drivingRoute.geometry.points.size / 2]
                        binding.mapview.map.move(
                            CameraPosition(routeCenter, 10.0f, 0.0f, 0.0f),
                            Animation(Animation.Type.SMOOTH, 1f),
                            null
                        )
                    }

                    override fun onDrivingRoutesError(p0: com.yandex.runtime.Error) {

                    }
                }
            )
        }
    }

    private fun getCurrentLocation(callback: (Point) -> Unit) {
        val locationManager =
            requireContext().getSystemService(Context.LOCATION_SERVICE) as LocationManager
        locationUtils = LocationUtils(requireContext())

        locationUtils.requestLocation(object : LocationUtils.OnLocationListener {
            override fun onLocationAvailable(latitude: Double, longitude: Double) {
                val currentLatitude = latitude
                val currentLongitude = longitude
                binding.mapview.map.move(
                    CameraPosition(Point(currentLatitude, currentLongitude), 14.0f, 0.0f, 0.0f)
                )
                addMarkerToCurrentLocation(currentLatitude, currentLongitude)
                Timber.tag("Location").d("Latitude: $latitude, Longitude: $longitude")

                val currentPoint = Point(currentLatitude, currentLongitude)
                callback(currentPoint)
            }
        })
    }

    private fun addMarkerToCurrentLocation(latitude: Double, longitude: Double) {
        val mapObjects = binding.mapview.map.mapObjects
        val placemark = mapObjects.addPlacemark(Point(latitude, longitude))

        placemark.setIcon(
            ImageProvider.fromResource(
                requireContext(),
                R.drawable.my_location_round
            )
        )
    }
}
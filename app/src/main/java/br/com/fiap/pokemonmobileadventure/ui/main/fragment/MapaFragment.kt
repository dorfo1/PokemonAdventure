package br.com.fiap.ui.Main.Fragment
import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Location
import android.os.Build
import android.os.Bundle
import android.os.Looper
import android.support.v4.app.ActivityCompat
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.ui.capturar.CapturaActivity

import br.com.fiap.pokemonmobileadventure.utils.PermissionUtils
import com.google.android.gms.location.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import java.util.*


//TODO Arrumar a permissão

class MapaFragment : Fragment(), OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private var mLocationRequest: LocationRequest? = null
    private val UPDATE_INTERVAL = (10*60*10 * 1000).toLong()
    private val FASTEST_INTERVAL: Long = 1000 * 60
    private val qtPokemonLimitOnMap: Int = 4
    private val POKEMON_CAPTURADO_REQUEST_CODE : Int = 10
    private var latitude = 0.0
    private var longitude = 0.0
    private var markers = ArrayList<Marker>(10)

    //private lateinit var mMarker: Marker
    private lateinit var mMap: GoogleMap
    private lateinit var locationCallback: LocationCallback

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_mapa,container,false)
        var mapFragment = childFragmentManager.findFragmentById(R.id.frame_mapa) as SupportMapFragment?
        mapFragment?.getMapAsync(this)
        PermissionUtils.validarPermissoes(
            listOf(Manifest.permission.ACCESS_FINE_LOCATION),activity,1
        )
        return view
    }


    override fun onStart() {
        super.onStart()
        startLocationUpdates()
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        if (mMap != null) {
            mMap!!.addMarker(MarkerOptions().position(LatLng(latitude, longitude)).title("Treinador").icon(BitmapDescriptorFactory.fromResource(R.drawable.trainer)))
        }
    }

    protected fun startLocationUpdates() {
        mLocationRequest = LocationRequest.create()
        mLocationRequest!!.run {
            setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY)
            setInterval(UPDATE_INTERVAL)
            setFastestInterval(FASTEST_INTERVAL)
        }

        val builder = LocationSettingsRequest.Builder()
        builder.addLocationRequest(mLocationRequest!!)
        val locationSettingsRequest = builder.build()


        val settingsClient = LocationServices.getSettingsClient(requireContext())
        settingsClient!!.checkLocationSettings(locationSettingsRequest)

        registerLocationListener()
    }

    private fun registerLocationListener() {
        locationCallback = object : LocationCallback() {
            override fun onLocationResult(locationResult: LocationResult?) {
                onLocationChanged(locationResult!!.getLastLocation())
            }
        }
        if(Build.VERSION.SDK_INT >= 23 && checkPermission()) {
            LocationServices.getFusedLocationProviderClient(requireContext()).requestLocationUpdates(mLocationRequest, locationCallback, Looper.myLooper())
        }
    }

    private fun onLocationChanged(location: Location) {
        var msg = "Update da localização: " + location.latitude  + " , " +location.longitude
        println(msg)
        val location = LatLng(location.latitude, location.longitude)
        setTrainerOnMap(location)
    }

    private fun setTrainerOnMap(location: LatLng){
        mMap!!.clear()
        mMap!!.addMarker(MarkerOptions().position(location).title("Treinador").icon(BitmapDescriptorFactory.fromResource(R.drawable.trainer)))
        setPokemonsOnMap(location)
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(location, 15f))
    }

    private fun setPokemonsOnMap(location: LatLng) {
        for (i in 0..qtPokemonLimitOnMap) {
            mMap.setOnMarkerClickListener(this)
            var teste = gerarPontosAleatorios(location, 2000)
            var addMarker= mMap!!.addMarker(MarkerOptions().position(teste).title("Pokemon").icon(BitmapDescriptorFactory.fromResource(R.drawable.generic)))
            markers.add(addMarker)
        }
    }

    override fun onMarkerClick(markerClicado: Marker?): Boolean {
        markers.forEachIndexed { index, marker ->
            if (markerClicado?.equals(marker)!!) {
                var intent = Intent(activity, CapturaActivity::class.java)
                activity?.startActivityForResult(intent,POKEMON_CAPTURADO_REQUEST_CODE)
            }
        }
        return true
    }

    private fun checkPermission() : Boolean {
        if (ContextCompat.checkSelfPermission(requireContext() , android.Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            return true;
        } else {
            requestPermissions()
            return false
        }
    }


    override fun onPause() {
        super.onPause()
        stopLocationUpdates()
    }

    private fun stopLocationUpdates() {
        LocationServices.getFusedLocationProviderClient(requireContext()).removeLocationUpdates(locationCallback)
    }

    private fun requestPermissions() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf("Manifest.permission.ACCESS_FINE_LOCATION"),1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for (result in grantResults) {
            if (result == PackageManager.PERMISSION_DENIED) {
                Toast.makeText(context, "Permissão negada", Toast.LENGTH_LONG).show()
            } else {
                startLocationUpdates()
            }
        }
    }


    fun gerarPontosAleatorios(point: LatLng, radius: Int): LatLng {
        val randomPoints = ArrayList<LatLng>()
        val randomDistances = ArrayList<Float>()
        val myLocation = Location("")
        myLocation.latitude = point.latitude
        myLocation.longitude = point.longitude


        for (i in 0..9) {
            val x0 = point.latitude
            val y0 = point.longitude

            val random = Random()

            val radiusInDegrees = (radius / 111000f).toDouble()

            val u = random.nextDouble()
            val v = random.nextDouble()
            val w = radiusInDegrees * Math.sqrt(u)
            val t = 2.0 * Math.PI * v
            val x = w * Math.cos(t)
            val y = w * Math.sin(t)

            val new_x = x / Math.cos(y0)

            val foundLatitude = new_x + x0
            val foundLongitude = y + y0
            val randomLatLng = LatLng(foundLatitude, foundLongitude)
            randomPoints.add(randomLatLng)
            val l1 = Location("")
            l1.latitude = randomLatLng.latitude
            l1.longitude = randomLatLng.longitude
            randomDistances.add(l1.distanceTo(myLocation))
        }

        val indexOfNearestPointToCentre = randomDistances.indexOf(Collections.min(randomDistances))
        return randomPoints.get(indexOfNearestPointToCentre)
    }


}
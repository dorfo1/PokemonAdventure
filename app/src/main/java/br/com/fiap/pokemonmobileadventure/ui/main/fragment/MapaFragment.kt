package br.com.fiap.ui.Main.Fragment

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.content.ContextCompat.getSystemService
import android.support.v4.content.PermissionChecker.checkSelfPermission
import com.google.android.gms.maps.model.LatLng
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import br.com.fiap.pokemonmobileadventure.R
import br.com.fiap.pokemonmobileadventure.utils.PermissionUtils
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.MarkerOptions

import java.util.*


class MapaFragment : Fragment(),OnMapReadyCallback,LocationListener{

    private lateinit var map : GoogleMap
    private lateinit var locationManager: LocationManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        locationManager = activity?.getSystemService(Context.LOCATION_SERVICE) as LocationManager
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_mapa,container,false)
        var mapFragment = childFragmentManager.findFragmentById(R.id.frame_mapa) as SupportMapFragment?
        mapFragment?.getMapAsync(this)

        return view
    }


    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap
        var update = CameraUpdateFactory.newLatLngZoom(LatLng(-23.5505, -46.6333), 10f)
        map.moveCamera(update)
        }


    @SuppressLint("MissingPermission")
    private fun requestLocationUpdates() {
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,0,0f,this)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        for(result in grantResults){
            if(result == PackageManager.PERMISSION_DENIED){
                Toast.makeText(context,"Permissão negada",Toast.LENGTH_LONG).show()
            }else{
                requestLocationUpdates()
            }

        }
    }

    override fun onResume() {
        super.onResume()

        PermissionUtils.validarPermissoes(
            listOf(Manifest.permission.ACCESS_FINE_LOCATION),activity,1
        )
        if(hasPermission(Manifest.permission.ACCESS_FINE_LOCATION)){
            requestLocationUpdates()
        }
    }

    override fun onLocationChanged(location: Location?) {
        map.clear()
        map.addMarker(MarkerOptions().position(LatLng(location?.latitude!!,location.longitude)))
        var update = CameraUpdateFactory.newLatLngZoom(LatLng(location?.latitude!!,location.longitude), 15f)
        map.moveCamera(update)
    }

    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
        Log.i("STATUS", "Provider " + provider + " has now status: " + status)
    }

    override fun onProviderEnabled(provider: String?) {
        Log.i("ENABLE", "Provider " + provider)
    }

    override fun onProviderDisabled(provider: String?) {
        Log.i("DISABLE", "Provider " + provider)
    }

    fun hasPermission(perm: String): Boolean {
        return(PackageManager.PERMISSION_GRANTED==checkSelfPermission(this.requireContext(), perm))
    }

    fun getRandomLocation(point: LatLng, radius: Int): LatLng {

        val randomPoints = ArrayList<LatLng>()
        val randomDistances = ArrayList<Float>()
        val myLocation = Location("")
        myLocation.latitude = point.latitude
        myLocation.longitude = point.longitude

        //This is to generate 10 random points
        for (i in 0..9) {
            val x0 = point.latitude
            val y0 = point.longitude

            val random = Random()

            // Convert radius from meters to degrees
            val radiusInDegrees = (radius / 111000f).toDouble()

            val u = random.nextDouble()
            val v = random.nextDouble()
            val w = radiusInDegrees * Math.sqrt(u)
            val t = 2.0 * Math.PI * v
            val x = w * Math.cos(t)
            val y = w * Math.sin(t)

            // Adjust the x-coordinate for the shrinking of the east-west distances
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
        //Get nearest point to the centre
        val indexOfNearestPointToCentre = randomDistances.indexOf(Collections.min(randomDistances))
        return randomPoints.get(indexOfNearestPointToCentre)
    }
}

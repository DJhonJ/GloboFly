package activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.djhonj.globofly.services.DestinationService
import com.djhonj.globofly.services.ServiceBuilder
import com.djhonj.globofly.R
import helpers.DestinationAdapter
import helpers.SampleData
import kotlinx.android.synthetic.main.activity_destiny_list.*
import models.Comment
import models.Destination
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DestinationListActivity : AppCompatActivity() {

	override fun onCreate(savedInstanceState: Bundle?) {
		super.onCreate(savedInstanceState)
		setContentView(R.layout.activity_destiny_list)

		val toolbar: Toolbar = findViewById(R.id.toolbar)
		setSupportActionBar(toolbar)
		toolbar?.title = title

		fab.setOnClickListener {
			val intent = Intent(this@DestinationListActivity, DestinationCreateActivity::class.java)
			startActivity(intent)
		}
	}

	override fun onResume() {
		super.onResume()

		loadDestinations()
	}

	private fun loadDestinations() {
        // To be replaced by retrofit code
		//destiny_recycler_view.adapter = DestinationAdapter(SampleData.DESTINATIONS)

		//instancia del servicio
		val destinationService: DestinationService = ServiceBuilder.buildService(DestinationService::class.java)

		//objeto para hacer la peticion
		val requestCall: Call<List<Comment>> = destinationService.getDestinationList()

		//hacermos la peticion de forma asyncrona
		requestCall.enqueue(object : Callback<List<Comment>> {
			override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
				if (response.isSuccessful) {
					val destinationList: List<Comment> = response.body()!!
					destiny_recycler_view.adapter = DestinationAdapter(destinationList)
				}
			}

			override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
			}
		})
    }
}

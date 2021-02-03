package `in`.edu.cambridge.parsingxmlandjson

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    private lateinit var parseXMLBtn: Button
    private lateinit var parseJSONBtn: Button

    private lateinit var datatype : TextView
    private lateinit var cityName: TextView
    private lateinit var latitude: TextView
    private lateinit var longitude: TextView
    private lateinit var temprature: TextView
    private lateinit var humidity: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        parseXMLBtn = findViewById(R.id.parse_xml)
        parseXMLBtn.setOnClickListener { parseXML() }
        parseJSONBtn = findViewById(R.id.parse_json)
        parseJSONBtn.setOnClickListener { parseJSON() }

        datatype = findViewById(R.id.data_type)
        cityName = findViewById(R.id.city_name)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        temprature = findViewById(R.id.temprature)
        humidity = findViewById(R.id.humidity)


    }

    fun parseXML(){
        datatype.text = "XML Data"


    }

    fun parseJSON(){
        datatype.text = "JSON Data"


    }
}
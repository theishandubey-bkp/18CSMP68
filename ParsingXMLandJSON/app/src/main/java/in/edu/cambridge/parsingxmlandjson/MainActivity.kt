package `in`.edu.cambridge.parsingxmlandjson

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import org.json.JSONObject
import java.io.IOException
import java.nio.charset.Charset
import javax.xml.parsers.DocumentBuilderFactory

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
        parseXMLBtn.setOnClickListener { parseXML() }

        datatype = findViewById(R.id.data_type)
        cityName = findViewById(R.id.city_name)
        latitude = findViewById(R.id.latitude)
        longitude = findViewById(R.id.longitude)
        temprature = findViewById(R.id.temprature)
        humidity = findViewById(R.id.humidity)


    }

    @SuppressLint("SetTextI18n")
    fun parseXML(){
        datatype.text = "XML Data"
        try {
            val iStream = assets.open("myxml.xml")
            val builderFactory = DocumentBuilderFactory.newInstance()
            var docBuilder = builderFactory.newDocumentBuilder()
            var doc = docBuilder.parse(iStream)
            cityName.text = "City Name : " + doc.getElementsByTagName("City_Name").item(0).getFirstChild().getNodeValue()
            latitude.text = "Latitude : " + doc.getElementsByTagName("Latitude").item(0).getFirstChild().getNodeValue()
            longitude.text = "Longitude : " + doc.getElementsByTagName("Longitude").item(0).getFirstChild().getNodeValue()
            temprature.text = "Temperature : " + doc.getElementsByTagName("Temperature").item(0).getFirstChild().getNodeValue()
            humidity.text = "Humidity : " + doc.getElementsByTagName("Humidity").item(0).getFirstChild().getNodeValue()
        }
        catch (ex: IOException) {

        }
    }

    @SuppressLint("SetTextI18n")
    fun parseJSON(){
        datatype.text = "JSON Data"
        val obj = JSONObject(loadJSONFromAsset())
        cityName.text = "City Name : " + obj.getString("City Name")
        latitude.text = "Latitude : " + obj.getString("Latitude")
        longitude.text = "Longitude : " + obj.getString("Longitude")
        temprature.text = "Temperature : " + obj.getString("Temperature")
        humidity.text = "Humidity : " + obj.getString("Humidity")
    }

    private fun loadJSONFromAsset(): String {
        val json: String?
        try {
            val inputStream = assets.open("myjson.json")
            val size = inputStream.available()
            val buffer = ByteArray(size)
            val charset: Charset = Charsets.UTF_8
            inputStream.read(buffer)
            inputStream.close()
            json = String(buffer, charset)
        }
        catch (ex: IOException) {
            ex.printStackTrace()
            return ""
        }
        return json
    }
}
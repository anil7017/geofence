<!DOCTYPE html>
<html>
   <head>
      <title>Leaflet sample</title>
      <!-- <link rel = "stylesheet" href = "http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.css"/>
      <script src = "http://cdn.leafletjs.com/leaflet-0.7.3/leaflet.js"></script> -->
      <link rel="stylesheet" href="https://unpkg.com/leaflet@1.0.3/dist/leaflet.css" />
      <script src="https://unpkg.com/leaflet@1.0.3/dist/leaflet-src.js"></script>
      <script src="/js/leaflet-realtime.js"></script>
      <script src="/js/index.js"></script>
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
   </head>

   <body>
      <div id = "map" style = "width: 1500px; height: 700px"></div>
      <script>
         // Creating map options
         var mapOptions = {
            center: [18.954090, 87.121814],
            zoom: 5
         }
         
         // Creating a map object
         var map = new L.map('map', mapOptions);
         
         // Creating a Layer object
         var layer = new L.TileLayer('http://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png');
         
         // Adding layer to the map
         map.addLayer(layer);

      // Icon options
         var iconOptions = {
            iconUrl: '/images/41-512.png',
            iconSize: [20, 20]
         }

      // Creating a custom icon
         var customIcon = L.icon(iconOptions);

      // Creating Marker Options
         var markerOptions = {
            title: "MyLocation",
            clickable: true,
            draggable: true,
            icon: customIcon
         }

      // Creating a Marker
         var marker1 = new L.marker([18.954090, 87.121814]);
         var marker2 = new L.marker([17.476432, 88.226982], markerOptions);
         var marker3 = new L.marker([17.978733, 86.248122], markerOptions);
         var marker4 = new L.marker([20.097206, 90.557639], markerOptions);
         var marker5 = new L.marker([20.097206, 88.710703], markerOptions);

         marker1.addTo(map);
         
        // var layerGroup = L.layerGroup([marker1,marker2,marker3,marker4,marker5]);
        // layerGroup.addTo(map);    // Adding layer group to map

         var circleCenter = [18.954090, 87.121814];     // Center of the circle

      // Circle options
         var circleOptions = {
            color: 'green',
            fillColor: '#f03',
            fillOpacity: 0
         }

      // Creating a circle
        var circle = L.circle(circleCenter, 200000, circleOptions);
        circle.addTo(map);     // Adding circle to the map

		// scale
        L.control.scale({position: 'bottomleft'}).addTo(map); 


     // create a red polygon from an array of LatLng points
        //var latlngs = [[37, -109.05],[41, -109.03],[41, -102.05],[37, -102.04]];
        var latlngs = [[14.017223749117512, 83.94433647394182],[14.694132145652691, 85.44506862759592],[13.888211536518165, 86.73925794661046],[12.599598832790107, 86.29870645701887],[12.46768752049401, 84.39917039126159]];
        

        //var polygon = L.polygon(latlngs, {color: 'green'}).addTo(map);

        // zoom the map to the polygon
        //map.fitBounds(polygon.getBounds());
 
        
        
        
        // on click marker
        map.on("dblclick", function(e){
            new L.Marker([e.latlng.lat, e.latlng.lng], markerOptions).addTo(map);
            //alert(e.latlng.lat +" ------"+e.latlng.lng);
            console.log(e.latlng.lat +" ------"+e.latlng.lng);
            
            $.ajax({
                url: "/geofence",
                type: 'POST',
                data:{ lat : e.latlng.lat,lng:e.latlng.lng},
                success: function(res) {
                    //console.log(res);
                    alert(res);
                }
            });
         })             
          
    /*  //var map = L.map('map'),
    realtime = L.realtime({
        url: 'https://wanderdrone.appspot.com/',
        crossOrigin: true,
        type: 'json'
    	}, {
        interval: 3 * 1000
    }).addTo(map);

	realtime.on('update', function() {
   	// map.fitBounds(realtime.getBounds(), {maxZoom: 3});
	});  */
         
         
      </script>
   </body>
   
</html>
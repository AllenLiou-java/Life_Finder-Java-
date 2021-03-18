//載入地圖
const map = L.map('map', { zoomControl: false }).setView([0, 0], 16);
//console.log(map);
// [24.1513098, 120.6618138]

L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a>'
}).addTo(map);


//定位圖標顏色
const violetIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-violet.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

const marker = L.marker([0, 0] , {icon:violetIcon}).addTo(map);

//定位使用者位置
if ('geolocation' in navigator) {
    // 如果定位可以運行，就印出 'geolocation available'
        console.log('geolocation available');
        // 取得使用者位置的經緯度
        navigator.geolocation.getCurrentPosition(position => {
        userLat = position.coords.latitude;
        userLng = position.coords.longitude;
        // 印出使用者位置的經緯度
        console.log(userLat, userLng);
        // 以使用者的經緯度取代 [0, 0]
        map.setView([userLat, userLng], 13);
        // 在使用者所在位置標上 marker
        marker.setLatLng([userLat,userLng]).bindPopup(
            `<h3>你的位置</h3>`)
            .openPopup();
        });
    } else {
    // 如果定位無法運行，就印出 'geolocation not available'
        console.log('geolocation not available');
    }

//新增定位按鈕

let geoBtn = document.getElementById('jsGeoBtn');
geoBtn.addEventListener('click', function(){
    map.setView([userLat, userLng], 13);
    marker.setLatLng([userLat,userLng]).bindPopup(
        `<h3>你的位置</h3>`)
        .openPopup();
},false);








//定義marker顏色
let bike;
// 2.我們取出綠、橘、紅三個顏色來代表口罩數量的不同狀態
const greenIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-green.png',
    // 3.只要更改上面這一段的 green.png 成專案裡提供的顏色如：red.png，就可以更改 marker 的顏色
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

const orangeIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-orange.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});


const redIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-red.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});

const greyIcon = new L.Icon({
    iconUrl: 'https://cdn.rawgit.com/pointhi/leaflet-color-markers/master/img/marker-icon-2x-grey.png',
    shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/0.7.7/images/marker-shadow.png',
    iconSize: [25, 41],
    iconAnchor: [12, 41],
    popupAnchor: [1, -34],
    shadowSize: [41, 41]
});












//利用 AJAX 將腳踏車資料倒入地圖中，並標上 marker

// 1.新增 init 函式，讓網頁載入時可以預設執行 init 裡的函式
function init(){
    getData();
}


//取得資料
let data;

function getData(){
    
    var xmlhttp = new XMLHttpRequest();
    var url = "../json/臺中市公共自行車(iBike)租借站&即時車位資料.json";
    xmlhttp.open("GET", url, true); 
    xmlhttp.send();  
    xmlhttp.onload = function(){
        data = JSON.parse(xmlhttp.responseText); 
            
        // 3.這是我們下一步要用的函式，為地圖上的藥局加上 marker
        addMarker();
    }
}

// 2.別忘了執行 init
init();


//將marker群組套件載入
const markers = new L.MarkerClusterGroup({ disableClusteringAtZoom: 18 }).addTo(map);

function addMarker(){
    for(let i = 0; i<data.length; i++){

    // 1.由於我們已能存取 data 裡的資料，所以我們就按照 API 的使用說明來取用資料
        const pos = data[i].Position;
        const available = data[i].AvailableCNT;
        const emp = data[i].EmpCNT;
        const lat = data[i].Y;
        const lng = data[i].X;

        //新增詳細內容
        const addr = data[i].CAddress;
        const update = data[i].UpdateTime;

    // 2.下判斷式，依據不同的口罩數量，來顯示不同的 marker 顏色
        if(available == 0 && emp == 0){
            bike = greyIcon;
        }else if(available > 0){
            bike = greenIcon;
        }else if(available == 0){
            bike = orangeIcon;
        }else if(emp == 0){
            bike = redIcon;
        }
    // 3.最後，將 marker 標至地圖上
    markers.addLayer(L.marker([lat, lng], {icon: bike}).bindPopup(
        `<div class="popupInfo">
            <p class="popupTitle" data-name="${pos}"><span>${pos}</span></P>
            <hr>
            <p class="popupText"><i class="fas fa-map-marker-alt"></i>&nbsp;${addr}</P>
            <p class="popupText">可借車輛 : ${available}</P>
            <p class="popupText">可停空位 : ${emp}</P>
            <p class="popupText">更新時間 : ${update}</P>    
        </div>
        `
        ));

    }
    map.addLayer(markers);
}

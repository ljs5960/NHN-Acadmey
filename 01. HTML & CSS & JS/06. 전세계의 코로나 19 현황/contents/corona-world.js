// DOM 로드 후 바로 실행
window.addEventListener("DOMContentLoaded", () => {
    getData(2022, 09, 'X');
});

// API 서버 접속
function getData(year, month, areaNm) {
    const promise = new Promise(function(resolve, reject) {
        const xhr = new XMLHttpRequest();
        const url = 'http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19NatInfStateJson'; /*URL*/
        const key = "obeLHYD31Q7NCPkdM1CQJsmT9PVIuJmA4CdEI%2BVsNdAnABJTXOJXk%2BNvZCCNfTSbW8QHgG72W9nHpWM2Ig4B0A%3D%3D";

        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + key; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent('20220901'); /**/
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent('20220930'); /**/

        xhr.open('GET', url + queryParams);
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                console.log(`Status: ${this.status}`);
                resolve(this.responseText);
            }
        };
        xhr.send('');
    }).then(function(xmlText) {
        parsing(xmlText, areaNm);
    }).catch(e => {
        console.log(e);
    }).finally(() => {
        console.log("API Request!");
    });
}

// XML 데이터 파싱
function parsing(xmlText, areaNm) {
    const xmlParser = new DOMParser();
    const xmlDoc = xmlParser.parseFromString(xmlText, "text/xml");
    printData(xmlDoc, areaNm);
}

// 화면 출력
function printData(xmlDoc, areaNm) {
    const nationNmCnt = 190;

    document.querySelector("#tableTBody").innerHTML ="";


    for (i=0; i<nationNmCnt; i++) {
        if (xmlDoc.getElementsByTagName("areaNm")[i].textContent == areaNm) {
            const nationNm = xmlDoc.getElementsByTagName("nationNm")[i].textContent;    // 국가
            const natDefCnt = xmlDoc.getElementsByTagName("natDefCnt")[i].textContent;  // 누적확진자
            const natDeathRate = xmlDoc.getElementsByTagName("natDeathRate")[i].textContent;    // 사망률
            
            // <tbody>에 열 추가
            document.querySelector("#tableTBody").innerHTML +=  (
                `<tr id="${nationNm}Row" onclick="goPage('${nationNm}')">
                    <td id="nationNm">${nationNm}</td>
                    <td>${natDefCnt}</td>
                    <td id="natDeathRate">${natDeathRate}</td>
                </tr>`
            );
        } else if(areaNm == 'X') {
            const nationNm = xmlDoc.getElementsByTagName("nationNm")[i].textContent;    // 국가
            const natDefCnt = xmlDoc.getElementsByTagName("natDefCnt")[i].textContent;  // 누적확진자
            const natDeathRate = xmlDoc.getElementsByTagName("natDeathRate")[i].textContent;    // 사망률
            
            // <tbody>에 열 추가
            document.querySelector("#tableTBody").innerHTML +=  (
                `<tr id="${nationNm}Row" onclick="goPage('${nationNm}')">
                    <td id="nationNm">${nationNm}</td>
                    <td>${natDefCnt}</td>
                    <td id="natDeathRate">${natDeathRate}</td>
                </tr>`
            );
        }
    }
    
    // 구글 차트
    google.charts.load('current', {
        'packages':['geochart'],
    });
    google.charts.setOnLoadCallback(drawRegionsMap);

    function drawRegionsMap() {
        for(let i=0; i<nationNmCnt; i++) {
            const nationNmEn = xmlDoc.getElementsByTagName("nationNmEn")[i].textContent;
            const natDefCnt = Number(xmlDoc.getElementsByTagName("natDefCnt")[i].textContent);

            var data = google.visualization.arrayToDataTable([
                [nationNmEn, natDefCnt],
            ]);
        }

        var options = {};

        var chart = new google.visualization.GeoChart(document.getElementById('regions_div'));

        chart.draw(data, options);
    }
}

function areaPaging(areaNm) {
    getData(2022, 09, areaNm)
}

// 국가별 페이지 이동 (세션 Storage 이용)
function goPage(nationNm) {
    sessionStorage.setItem("nationNm", nationNm);
    window.open(`corona-world_individual.html`);
}


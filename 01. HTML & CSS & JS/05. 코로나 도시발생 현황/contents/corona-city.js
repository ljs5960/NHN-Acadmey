// DOM 로드 후 바로 실행
window.addEventListener("DOMContentLoaded", () => {
    getData(2022, 09);
});

// API 서버 접속
function getData(year, month) {
    const promise = new Promise(function(resolve, reject) {
        const xhr = new XMLHttpRequest();
        const url = 'http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19SidoInfStateJson'; /*URL*/
        const key = "obeLHYD31Q7NCPkdM1CQJsmT9PVIuJmA4CdEI%2BVsNdAnABJTXOJXk%2BNvZCCNfTSbW8QHgG72W9nHpWM2Ig4B0A%3D%3D";

        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + key; /*Service Key*/
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1'); /**/
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10'); /**/
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent("20220901"); /**/
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent("20220930"); /**/

        xhr.open('GET', url + queryParams);
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                console.log(`Status: ${this.status}`);
                resolve(this.responseText);
            }
        };
        xhr.send('');
    }).then(function(xmlText) {
        parsing(xmlText);
    }).catch(e => {
        console.log(e);
    }).finally(() => {
        console.log("API Request!");
    });
}

// XML 데이터 파싱
function parsing(xmlText) {
    const xmlParser = new DOMParser();
    const xmlDoc = xmlParser.parseFromString(xmlText, "text/xml");
    printData(xmlDoc);
}

// 화면 출력
function printData(xmlDoc) {
    const gubunCnt = 19;
    
    for (i=0; i<gubunCnt; i++) {
        const gubun = xmlDoc.getElementsByTagName("gubun")[i].textContent;    // 도시
        const defCnt = xmlDoc.getElementsByTagName("defCnt")[i].textContent;  // 누적확진자
        const incDec = xmlDoc.getElementsByTagName("incDec")[i].textContent;    // 신규확진자

        // <tbody>에 열 추가
        document.querySelector("#tableTBody").innerHTML +=  (
            `<tr id="${gubun}Row" onclick="goPage('${gubun}')">
                <td id="gubun" style="color: blue;">${gubun}</td>
                <td>${defCnt}</td>
                <td id="incDec">${incDec}</td>
            </tr>`
        );
    }
}

// 도시별 페이지 이동 (세션 Storage 이용)
function goPage(gubun) {
    sessionStorage.setItem("gubun", gubun);
    window.open(`corona-city_individual.html`);
}
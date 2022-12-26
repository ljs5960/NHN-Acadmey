const nationNm = sessionStorage.getItem("nationNm");

document.querySelector("#headerTitle").innerHTML = `${nationNm} 코로나19 현황`;

// DOM 로드 후 바로 실행
window.addEventListener("DOMContentLoaded", () => {
    getData(2022, 09);
});

// API 서버 접속
function getData(year, month) {
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
        const xmlParser = new DOMParser();
        const xmlDoc = xmlParser.parseFromString(xmlText, "text/xml");
        const coronaList = xmlDoc.getElementsByTagName("item");

        console.log(coronaList)

        // 화면 출력
        for (let i=0; i<coronaList.length; i++) {
            // 해당 도시에 해당할 때만 실행
            if (xmlDoc.getElementsByTagName("nationNm")[i].textContent == nationNm) {
                const createDt = xmlDoc.getElementsByTagName("createDt")[i].textContent;  // 생성일
                const natDefCnt = xmlDoc.getElementsByTagName("natDefCnt")[i].textContent;  // 누적확진자
                const natDeathRate = xmlDoc.getElementsByTagName("natDeathRate")[i].textContent;    // 신규확진자

                // <tbody>에 열 추가
                document.querySelector("#tableTBody").innerHTML +=  (
                    `<tr>
                        <td>${createDt}</td>
                        <td>${natDefCnt}</td>
                        <td id="natDeathRate">${natDeathRate}</td>
                    </tr>`
                );
            } else {
                continue;
            }
        }
    }).catch(e => {
        console.log(e);
    }).finally(() => {
        console.log("API Request!");
    });
}
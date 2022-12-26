// 첫 화면 금월 정보 표시
let nowYear = new Date().getFullYear(); // 2022
let nowMonth = new Date().getMonth() + 1;   // 10

// 페이지 시작 후 실행
window.addEventListener("DOMContentLoaded", () => {
    this.document.querySelector("#headerDate").innerHTML = `${nowYear}년 ${nowMonth}월`;
    getData(nowYear.toString(), nowMonth.toString().padStart(2, "0"));
});

// 전월 이동버튼
document.querySelector("#previousMonth").addEventListener("click", () =>{
    nowMonth -= 1;

    if (nowMonth == 0) {
        nowYear -= 1;
        nowMonth = 12;
    }

    document.querySelector("#headerDate").innerHTML = `${nowYear}년 ${nowMonth}월`;
    getData(nowYear.toString(), nowMonth.toString().padStart(2, "0"));
});

// 익월 이동버튼
document.querySelector("#nextMonth").addEventListener("click", () =>{
    nowMonth += 1;

    if (nowMonth == 13) {
        nowYear +=1;
        nowMonth = 1;
    }

    document.querySelector("#headerDate").innerHTML = `${nowYear}년 ${nowMonth}월`;
    getData(nowYear.toString(), nowMonth.toString().padStart(2, "0"));
});

// API 서버 접속
function getData(year, month) {
    const monthArray = ["31", "28", "31", "30", "31", "30", "31", "31", "30", "31", "30", "31"];

    const startDay = year + month + "01";
    const endDay = year + month + monthArray[month - 1];

    const promise = new Promise(function(resolve, reject) {
        const xhr = new XMLHttpRequest();
        const url = "http://openapi.data.go.kr/openapi/service/rest/Covid19/getCovid19InfStateJson";
        const key = "obeLHYD31Q7NCPkdM1CQJsmT9PVIuJmA4CdEI%2BVsNdAnABJTXOJXk%2BNvZCCNfTSbW8QHgG72W9nHpWM2Ig4B0A%3D%3D";

        let queryParams = '?' + encodeURIComponent('serviceKey') + '=' + key;
        queryParams += '&' + encodeURIComponent('pageNo') + '=' + encodeURIComponent('1');
        queryParams += '&' + encodeURIComponent('numOfRows') + '=' + encodeURIComponent('10');
        queryParams += '&' + encodeURIComponent('startCreateDt') + '=' + encodeURIComponent(startDay);
        queryParams += '&' + encodeURIComponent('endCreateDt') + '=' + encodeURIComponent(endDay);

        xhr.open('GET', url + queryParams);
        xhr.onreadystatechange = function () {
            if (this.readyState == 4) {
                console.log(`Status: ${this.status}`);
                resolve(this.responseText);
            }
        };

        xhr.send('');
    }).then(function(xmlText) {
        // xml Parsing
        const xmlParser = new DOMParser();
        const xmlDoc = xmlParser.parseFromString(xmlText, "text/xml");
        const coronaList = xmlDoc.getElementsByTagName("item");

        // 미래 달 이동 불가
        if ((year >= new Date().getFullYear()) && (month >= new Date().getMonth() + 1)) {
            document.querySelector("#nextMonth").setAttribute("disabled", true);
        } else {
            document.querySelector("#nextMonth").removeAttribute("disabled");
        }

        // 화면 초기화
        document.querySelector("#tableTBody").innerHTML = "";

        // 화면 출력
        for (let i=0; i<coronaList.length; i++) {
            const createDt = xmlDoc.getElementsByTagName("createDt")[i].textContent;    // 기준일
            const deathCnt = xmlDoc.getElementsByTagName("deathCnt")[i].textContent;    // 사망자수
            const decideCnt = xmlDoc.getElementsByTagName("decideCnt")[i].textContent;  // 확진자수
            // const updateDt = xmlDoc.getElementsByTagName("updateDt")[i].textContent;    // 갱신일

            // <tbody>에 열 추가
            document.querySelector("#tableTBody").innerHTML +=  (
                `<tr>
                    <td>${createDt}</td>
                    <td>${deathCnt}</td>
                    <td>${decideCnt}</td>
                </tr>`
            );
        }

        // Google Chart
        google.charts.load('current', {'packages':['line']});
        google.charts.setOnLoadCallback(drawChart);

        function drawChart() {
            var data = new google.visualization.DataTable();
            data.addColumn('datetime', 'Day');
            data.addColumn('number', '확진자수(누적)');
            data.addColumn('number', '사망자수(누적) ');

            for (let i=0; i<coronaList.length; i++) {
                const createDt =new Date(xmlDoc.getElementsByTagName("createDt")[i].textContent);    // 기준일
                const deathCnt = Number(xmlDoc.getElementsByTagName("deathCnt")[i].textContent);    // 사망자수
                const decideCnt = Number(xmlDoc.getElementsByTagName("decideCnt")[i].textContent);  // 확진자수
                                
                data.addRows([
                    [createDt, deathCnt, decideCnt]
                ]);
            }

            var options = {
                width: "100%",
                height: 500
            };

            var chart = new google.charts.Line(document.getElementById('chart_div'));

            chart.draw(data, google.charts.Line.convertOptions(options));
        }
    }).catch(e => {
        console.log(e);
    }).finally(() => {
        console.log("API Request!");
    });
}
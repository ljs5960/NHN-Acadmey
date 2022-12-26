// Header
const date = new Date();
let calendarYear = date.getFullYear();
let calendarMonth = date.getMonth() + 1;
let calendarDate = date.getDate();
let calendarDay = date.getDay();    // 당월 시작 요일

window.addEventListener("DOMContentLoaded", printYearMonth(calendarYear, calendarMonth));

// 해당 년, 월 출력
function printYearMonth(tempYear, tempMonth) {
    document.querySelector("#year").innerHTML = `${tempYear}년`;
    document.querySelector("#month").innerHTML = `${tempMonth}월`;
}

// 전월 이동 버튼
document.querySelector("#previousMonth").addEventListener("click", function() {
    calendarMonth = calendarMonth - 1;

    if (calendarMonth < 1) {
        calendarMonth = 12;
        calendarYear = calendarYear - 1;
    }

    printYearMonth(calendarYear, calendarMonth);
    printList(calendarYear, calendarMonth);
});

// 익월 이동 버튼
document.querySelector("#nextMonth").addEventListener("click", function() {
    calendarMonth = calendarMonth + 1;

    if (calendarMonth > 12) {
        calendarMonth = 1;
        calendarYear = calendarYear + 1;
    }

    printYearMonth(calendarYear, calendarMonth);
    printList(calendarYear, calendarMonth);
});

// Calendar
// 윤년계산 구현 필요!!!
const monthArray = [31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31];
const monSunArray = ["일", "월", "화", "수", "목", "금", "토"];

function printList(tempYear, tempMonth) {
    let tempDay = new Date(tempYear, tempMonth - 1).getDay();

    document.querySelector(".calendarList").innerHTML = ""

    for (let i=0; i<monthArray[tempMonth - 1]; i++) {
        document.querySelector(".calendarList").innerHTML +=
        `<div class="calendar" id= "${tempYear}${tempMonth}${i+1}">
            <div id="calendarDayDiv">
                <div>${i+1}</div>
                <div>${monSunArray[(tempDay + i) % 7]}</div>
            </div>
            <div id="calendarInputDiv">
                <input id="toDoInput" type="text">
                <span>
                    <button id="toDoBtn" type="button"><i class="fa-solid fa-floppy-disk"></i></button>
                    <button id="deleteAllBtn" type="button"><i class="fa-solid fa-trash"></i></button>
                </span>
            </div>
            <div id="calendarListDiv" style="overflow-y: scroll; width: 20rem">
                <ul id="toDoList">
                </ul>
            </div>
        </div>`;
    }
}

window.addEventListener("DOMContentLoaded", printList(calendarYear, calendarMonth));
window.addEventListener("DOMContentLoaded", printToDo());

// ToDo 등록
document.querySelector("#toDoBtn").addEventListener("click", function() {
    const inputValue = document.querySelector("#toDoInput");
    const inputDay = 1;

    // 내용이 없을 경우
    if (!inputValue.value) {
        alert("내용을 입력해주세요.");
        inputValue.focus();
        return false;
    }

    saveToDo(inputValue.value);
    alert("등록 완료");
    printToDo();
    inputValue.value = " ";
});

// LocalStorage 저장
// yyyyMMdd (key) : ["ToDo1", "ToDo2", ...] (value)
function saveToDo(value) {
    const temp = JSON.parse(localStorage.getItem("20220929"));

    if (temp == null) {
        localStorage.setItem("20220929", JSON.stringify(value));
    } else {
        let valueArray = [temp];
        valueArray.push(value);
    
        localStorage.setItem("20220929", JSON.stringify(valueArray));
    }
}

// ToDo 출력
function printToDo() {
    const li = document.createElement("li");
    const value = JSON.parse(localStorage.getItem("20220929"));

    if (value != null) {
        for (let i=0; i<value.length; i++) {
            if (value[i] != null) {
                li.innerHTML = `${value[i]}<span id="deleteBtn">❌</span>`;
                document.querySelector("#toDoList").appendChild(li);
                document.querySelector("#deleteBtn").addEventListener("click", deleteToDo);
            }
        }
    }
}

// ToDo 단일삭제
function deleteToDo(event) {
    if (confirm("삭제하겠습니까?")) {
        const li = event.target.parentElement

        li.remove();
        localStorage.removeItem("20220929");
        printToDo();

        alert("삭제 완료");
        // document.querySelector("#toDoList").removeChild(li);
    }
}

// ToDo 일괄삭제
document.querySelector("#deleteAllBtn").addEventListener("click", function() {
    if (confirm("일괄 삭제하겠습니까?")) {
        const value = JSON.parse(localStorage.getItem("20220929"));

        if (value != null) {
            localStorage.clear("20220929");
            document.querySelector("#toDoList").innerHTML = "";
        }

        alert("일괄 삭제 완료")
    }
});

console.log(calendarYear, calendarMonth, calendarDate);

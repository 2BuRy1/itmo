document.getElementById("ySelection").addEventListener("input", function (e) {
    let value = e.target.value;

    if (!/^-?\d*\.?\d{0,10}$/.test(value)) {
        e.target.value = value.slice(0, -1);
    }
});


canvas.addEventListener('click', function (event) {
    let rHTML = document.querySelector('input[type="radio"]:checked') // Получаем выбранное значение R

    if (!rHTML || !checkR(parseFloat(rHTML.value))) {
        badMessage("Выбреите радиус!");
        return;
    }

    const rValue = parseFloat(rHTML.value);  // Получаем значение радиуса R

    const rect = canvas.getBoundingClientRect();
    const xClick = event.clientX - rect.left;
    const yClick = event.clientY - rect.top;

    const xValue = (xClick - Xcanvas / 2) * rValue / 100;  // 100 — масштаб по X
    const yValue = -(yClick - Ycanvas / 2) * rValue / 100; // 100 — масштаб по Y
    if (checkValue(yValue) && checkX(xValue)) {
        sendResponse(xValue.toFixed(5), yValue.toFixed(5), rValue);
       // drawDot(xValue, yValue, rValue);
    } else {
        badMessage("Данные невалидны!!")
    }


});

let xHTML;

document.querySelectorAll(".xButtons").forEach(element => {
    element.addEventListener("click", function (e) {
        xHTML = e.target.value;
    });
});


async function submit() {

    let yHTML = document.getElementById("ySelection");
    let rHTML = document.querySelector('input[type="radio"]:checked')


    if (isNaN(parseFloat(yHTML.value)) || !checkValue(parseFloat(yHTML.value)) || !rHTML || !checkR(parseFloat(rHTML.value)) || !checkX(parseFloat(xHTML))) {
        badMessage("Данные невалидны!!")
        return;
    }


    const xValue = parseFloat(xHTML);
    const rValue = parseInt(rHTML.value);
    console.log(xValue)

    //drawDot(xValue, yHTML.value, rValue);
    sendResponse(xValue, yHTML.value, rValue);
}


function checkValue(value) {

    if (-3 > value || value > 3) {
        return false;
    }
    return true;

}

function checkR(value) {

    let array = [1, 2, 3, 4, 5]
    for (let i = 0; i < array.length; i++) {
        if (array[i] === parseFloat(value)) {
            return true;
        }

    }
    return false;
}

function checkX(value) {

    return (value > -2 && value < 2);


}

function sendResponse(x, y, r) {

    const requestContent = {
        method: "get",
        headers: {
            "Accept": "application/json",
        },
    }

    const url = '/api';


    fetch(`${url}?x=${x}&y=${y}&r=${r}`, requestContent).then(response => response.json())
        .then(data => appendData(data)).catch(err => console.error(err));


}


function appendData(item) {
    let body = document.getElementById("table_body");
    let thead = document.getElementById("table-header");
    let RequestStatus = document.querySelector("status")
    RequestStatus.innerHTML = '';

    drawDot(item.x, item.y, item.r, item.status);

    const row = document.createElement("tr");

    const Xcell = document.createElement("td");
    Xcell.textContent = item.x;
    row.appendChild(Xcell);

    const Ycell = document.createElement("td");
    Ycell.textContent = item.y;
    row.appendChild(Ycell);

    const Rcell = document.createElement("td");
    Rcell.textContent = item.r;
    row.appendChild(Rcell);

    const status = document.createElement("td");

    item.status === true ? status.textContent = "Попадание" : status.textContent = "Промах";
    row.appendChild(status);

    body.prepend(row);
    thead.classList.add('visible');

    let statusText = document.createElement("h2");
    if (item.status) {
        statusText.textContent = "Попадание";
        RequestStatus.style.color = "green";
    } else {
        statusText.textContent = "Промах"
        RequestStatus.style.color = "red";
    }
    RequestStatus.classList.add('visible');
    RequestStatus.appendChild(statusText);


}


function drawDot(xValue, yValue, rValue, status) {
    const canvas = document.getElementById('canvas');
    const ctx = canvas.getContext('2d');

    let plotX = 2 * xValue / rValue * 50;

    let plotY = -2 * yValue / rValue * 50;
    ctx.beginPath();
    ctx.translate(canvas.width / 2, canvas.height / 2);
    ctx.arc(plotX, plotY, 5, 0, 2 * Math.PI);
    ctx.fillStyle = status ? 'green' : 'red';
    ctx.fill();
    ctx.resetTransform();
    ctx.closePath();

}

function badMessage(message) {
    let RequestStatus = document.querySelector("status");

    // Очистим старое сообщение, если оно уже есть
    RequestStatus.innerHTML = '';

    if (!RequestStatus.querySelector("h2")) {
        let statusText = document.createElement("h2");
        statusText.textContent = message
        RequestStatus.style.color = "red";
        RequestStatus.classList.add('visible');
        RequestStatus.appendChild(statusText);
    }
}
document.querySelector(".ySelection").addEventListener("input", function (e) {
    let value = e.target.value;

    if (!/^-?\d*\.?\d{0,10}$/.test(value)) {
        e.target.value = value.slice(0, -1);
    }
});

canvas.addEventListener('click', function (event) {
    let rHTML = document.querySelector('.rSelection');
    let selectedValue = rHTML.value;
    if (!selectedValue || !checkR(parseFloat(selectedValue))) {
        badMessage("Выберите радиус!");
        return;
    }

    const rValue = parseFloat(selectedValue);
    const rect = canvas.getBoundingClientRect();
    const xClick = event.clientX - rect.left;
    const yClick = event.clientY - rect.top;

    const canvasCenterX = canvas.width / 2;
    const canvasCenterY = canvas.height / 2;

    const scale = canvasCenterX / 5;

    const xValue = (xClick - canvasCenterX) / scale;
    const yValue = -(yClick - canvasCenterY) / scale;

    document.querySelector('.ySelection').value = yValue.toFixed(2);
    document.querySelector('.xSelection input').value = xValue.toFixed(2);

    if (checkY(yValue) && checkX(xValue)) {
        document.querySelector('.submitButton').click();

        setTimeout(() => {
            const tableRows = document.querySelectorAll("#table tbody tr");
            const lastRow = tableRows[0];
            const cell = lastRow.querySelectorAll("td")[3];
            const status = cell.textContent.trim() === "Попадание";
            drawPoint(xValue, yValue, rValue, status);
        }, 315);
    } else {
        badMessage("Данные невалидны!");
    }
});


function processButtons() {
    const xValue = parseFloat(document.querySelector('.xSelection input').value);
    const yValue = parseFloat(document.querySelector('.ySelection').value);
    const rValue = parseFloat(document.querySelector('.rSelection').value);

    if (checkX(xValue) && checkY(yValue)) {
        setTimeout(() => {
            const tableRows = document.querySelectorAll("#table tbody tr");
            const lastRow = tableRows[0];
            const cell = lastRow.querySelectorAll("td")[3];
            const status = cell.textContent.trim() === "Попадание";
            drawPoint(xValue, yValue, rValue, status);
        }, 320);
    } else {
        badMessage("Данные невалидны");
    }
}

    function checkY(value) {
        return (-5 <= value && value <= 3);
    }

    function checkR(value) {
        return [1, 2, 3, 4, 5].includes(parseFloat(value));
    }

    function checkX(value) {
        return (value > -5 && value < 5);
    }


    function drawPoint(xValue, yValue, rValue, status) {
        const canvas = document.getElementById('pointsCanvas');
        const ctx = canvas.getContext('2d');

        const plotX = xValue * 40;
        const plotY = -yValue * 40;

        ctx.beginPath();

        ctx.translate(canvas.width / 2, canvas.height / 2);
        ctx.arc(plotX, plotY, 5, 0, 2 * Math.PI);
        ctx.fillStyle = status ? 'green' : 'red';
        ctx.fill();
        ctx.resetTransform();
        ctx.closePath();
    }

    function badMessage(message) {
        let RequestStatus = document.querySelector(".status");
        RequestStatus.innerHTML = '';

        if (!RequestStatus.querySelector("h2")) {
            let statusText = document.createElement("h2");
            statusText.textContent = message;
            RequestStatus.style.color = "red";
            RequestStatus.classList.add('visible');
            RequestStatus.appendChild(statusText);
        }
    }





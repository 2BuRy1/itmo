document.getElementById("ySelection")?.addEventListener("input", function (e) {
    const target = e.target as HTMLInputElement;
    let value = target.value;

    if (!/^-?\d*\.?\d{0,10}$/.test(value)) {
        target.value = value.slice(0, -1);
    }
});

const checkboxes = document.querySelectorAll(".checkboxes") as NodeListOf<HTMLInputElement>;

checkboxes.forEach(function (checkbox) {
    checkbox.addEventListener('change', function (event) {
        checkboxes.forEach(function (cb) {
            if (cb !== event.target) {
                cb.checked = false;
            }
        });
    });
});

async function submit() {
    const xHTML = document.getElementById("xSelection") as HTMLInputElement;
    const yHTML = document.getElementById("ySelection") as HTMLInputElement;
    const rHTML = Array.from(checkboxes).find(i => i.checked);

    if (isNaN(parseFloat(yHTML.value)) || !checkValue(parseFloat(yHTML.value)) || !rHTML || !checkR(parseFloat(rHTML.value)) || !checkX(parseFloat(xHTML.value))) {
        return;
    }

    const xValue = parseInt(xHTML.value);
    const rValue = parseInt(rHTML.value);

    drawDot(xValue, parseFloat(yHTML.value), rValue);

    const requestContent = {
        method: "post",
        headers: {
            "content-type": "application/json",
        },
        body: JSON.stringify({
            x: xValue,
            y: yHTML.value,
            r: rValue
        })
    };

    const url = '/api/';

    try {
        const response = await fetch(url, requestContent);
        const data = await response.json();
        appendData(data);
    } catch (err) {
        console.error(err);
    }
}

function checkValue(value: number): boolean {
    return value >= -3 && value <= 5;
}

function checkR(value: number): boolean {
    const validValues = [1, 2, 3, 4, 5];
    return validValues.includes(value);
}

function checkX(value: number): boolean {
    const validValues = [-3, -2, -1, 0, 1, 2, 3, 4];
    return validValues.includes(value);
}

function appendData(item: { x: number; y: number; r: number; status: boolean; time: string }): void {
    const body = document.getElementById("table_body") as HTMLTableSectionElement;
    const thead = document.getElementById("table-header") as HTMLTableSectionElement;
    const RequestStatus = document.querySelector("status") as HTMLElement;
    RequestStatus.innerHTML = '';

    const row = document.createElement("tr");

    const Xcell = document.createElement("td");
    Xcell.textContent = item.x.toString();
    row.appendChild(Xcell);

    const Ycell = document.createElement("td");
    Ycell.textContent = item.y.toString();
    row.appendChild(Ycell);

    const Rcell = document.createElement("td");
    Rcell.textContent = item.r.toString();
    row.appendChild(Rcell);

    const status = document.createElement("td");
    status.textContent = item.status ? "Попадание" : "Промах";
    row.appendChild(status);

    const CurrentTime = document.createElement("td");
    CurrentTime.textContent = new Date().toLocaleTimeString();
    row.appendChild(CurrentTime);

    const SpentTime = document.createElement("td");
    SpentTime.textContent = item.time;
    row.appendChild(SpentTime);

    body.prepend(row);
    thead.classList.add('visible');

    const statusText = document.createElement("h2");
    if (item.status) {
        statusText.textContent = "Попадание";
        RequestStatus.style.color = "green";
    } else {
        statusText.textContent = "Промах";
        RequestStatus.style.color = "red";
    }
    RequestStatus.classList.add('visible');
    RequestStatus.appendChild(statusText);
}

function drawDot(xValue: number, yValue: number, rValue: number): void {
    const canvas = document.getElementById('canvas') as HTMLCanvasElement;
    const ctx = canvas.getContext('2d')!;

    const plotX = 2 * xValue / rValue * 50;
    const plotY = -2 * yValue / rValue * 50;

    ctx.beginPath();
    ctx.translate(canvas.width / 2, canvas.height / 2);
    ctx.arc(plotX, plotY, 5, 0, 2 * Math.PI);
    ctx.fillStyle = 'red';
    ctx.fill();
    ctx.resetTransform();
    ctx.closePath();
}

function badMessage(): void {
    const RequestStatus = document.querySelector("status") as HTMLElement;
    const statusText = document.createElement("h2");
    RequestStatus.classList.add('visible');
    RequestStatus.appendChild(statusText);
}


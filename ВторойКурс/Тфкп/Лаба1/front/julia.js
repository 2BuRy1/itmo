const juliacanv = document.getElementById('juliaCanvas');
const ctx1 = juliacanv.getContext('2d');
const widthJulia = juliacanv.width;
const heightJulia = juliacanv.height;

const xMinJulia = -1.5, xMaxJulia = 1.5;
const yMinJulia = -1.5, yMaxJulia = 1.5;

const xStepJulia = (xMaxJulia - xMinJulia) / (widthJulia - 1);
const yStepJulia = (yMaxJulia - yMinJulia) / (heightJulia - 1);

let c = { x:-0.5251993, y: 0.5251993 };
let maxIterJulia = 700;

function julia(x, y) {
    let iter = 0;
    while (x * x + y * y <= 4 && iter < maxIterJulia) {
        let temp = x * x - y * y + c.x;
        y = 2 * x * y + c.y;
        x = temp;
        iter++;
    }
    return iter;
}


function getJuliaColor(iter) {
    if (iter === maxIterJulia) {
        return 'black';
    } else {
        const hue = Math.floor(540 * iter / maxIterJulia);
        const saturation = 100;  // 100% насыщенность
        const lightness = iter < maxIterJulia ? 55 : 0;

        return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
    }
}

function drawJulia() {
    ctx1.clearRect(0, 0, widthJulia, heightJulia);

    for (let ix = 0; ix < widthJulia; ix++) {
        for (let iy = 0; iy < heightJulia; iy++) {
            const x = xMinJulia + ix * xStepJulia;
            const y = yMinJulia + iy * yStepJulia;
            const iter = julia(x, y);

            ctx1.fillStyle = getJuliaColor(iter);
            ctx1.fillRect(ix, iy, 1, 1);
        }
    }
}

function drawAxesJulia() {
    ctx1.strokeStyle = '#7f7f7f';
    ctx1.lineWidth = 2;

    ctx1.beginPath();
    ctx1.moveTo(0, heightJulia / 2);
    ctx1.lineTo(widthJulia, heightJulia / 2);
    ctx1.stroke();

    ctx1.beginPath();
    ctx1.moveTo(widthJulia / 2, 0);
    ctx1.lineTo(widthJulia / 2, heightJulia);
    ctx1.stroke();
}

function updateAndDraw() {
    drawJulia();
    drawAxesJulia();
}

updateAndDraw();


document.getElementById('cXSlider').addEventListener('input', (e) => {
    c.x = parseFloat(e.target.value);
    setTimeout(() => {
    updateAndDraw();
    }, 100)
});

document.getElementById('cYSlider').addEventListener('input', (e) => {
    c.y = parseFloat(e.target.value);
    setTimeout(() => {
        updateAndDraw();
    }, 100)
});

document.getElementById('iterSlider').addEventListener('input', (e) => {
    maxIterJulia = parseInt(e.target.value);
    setTimeout(() => {
        updateAndDraw();
    }, 100)
});
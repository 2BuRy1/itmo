const canvas = document.getElementById('mandelbrotCanvas');
const ctx = canvas.getContext('2d');
const width = canvas.width;
const height = canvas.height;

let xMin = -2.5, xMax = 1;
let yMin = -1.5, yMax = 1.5;
let maxIter = 100;
let zoom = 1;

const fractalCenterX = -0.743643887037151;
const fractalCenterY = 0.13182590420533;

function updateSteps() {
    xStep = (xMax - xMin) / (width - 1);
    yStep = (yMax - yMin) / (height - 1);
}
updateSteps();

function mandelbrot(x, y) {
    let zx = x;
    let zy = y;
    let iter = 0;
    while (zx * zx + zy * zy <= 4 && iter < maxIter) {
        const temp = zx * zx - zy * zy + x;
        zy = 2 * zx * zy + y;
        zx = temp;
        iter++;
    }
    return iter;
}

function drawAxes() {
    ctx.strokeStyle = '#7f7f7f';
    ctx.lineWidth = 2;

    ctx.beginPath();
    ctx.moveTo(0, height / 2);
    ctx.lineTo(width, height / 2);
    ctx.stroke();

    ctx.beginPath();
    ctx.moveTo(width / 2, 0);
    ctx.lineTo(width / 2, height);
    ctx.stroke();

    ctx.fillStyle = 'black';
    ctx.font = '16px Arial';
}

function getColor(iter) {
    if (iter === maxIter) {
        return 'black';
    } else {
        const hue = Math.floor(360 * iter / maxIter);
        const saturation = 100;
        const lightness = iter < maxIter ? 55 : 0;

        return `hsl(${hue}, ${saturation}%, ${lightness}%)`;
    }
}

function drawMandelbrot() {
    for (let ix = 0; ix < width; ix++) {
        for (let iy = 0; iy < height; iy++) {
            const x = xMin + ix * xStep;
            const y = yMin + iy * yStep;
            const iter = mandelbrot(x, y);

            ctx.fillStyle = getColor(iter);
            ctx.fillRect(ix, iy, 1, 1);
        }
    }
}

function updateAndDrawMand() {
    updateSteps();
    ctx.clearRect(0, 0, width, height);
    drawMandelbrot();
    drawAxes();
}

document.getElementById('iter4Mandelbrot').addEventListener('input', (e) => {
    maxIter = parseInt(e.target.value);
    updateAndDrawMand();
});

document.getElementById('zoom4Mandelbrot').addEventListener('input', (e) => {
    zoom = parseInt(e.target.value);

    const zoomFactor = 1 / zoom;

    xMin = fractalCenterX - (2.5 * zoomFactor);
    xMax = fractalCenterX + (2.5 * zoomFactor);
    yMin = fractalCenterY - (1.5 * zoomFactor);
    yMax = fractalCenterY + (1.5 * zoomFactor);

    updateAndDrawMand();
});

updateAndDrawMand();